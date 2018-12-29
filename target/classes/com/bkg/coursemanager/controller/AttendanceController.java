package com.bkg.coursemanager.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bkg.coursemanager.dao.AttendanceDao;
import com.bkg.coursemanager.entity.Attendance;
import com.bkg.coursemanager.service.AttendanceService;


/*
 * @author JQQ
 * @date 2018/12/19
 */
@RestController
@RequestMapping("/attendance")
public class AttendanceController {
	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private AttendanceDao attendanceDao;
	
	/*
	 * 修改报名展示（顺序）
	 */
	@PutMapping("/{attendanceId}")
	public void updateTeamOrder(@RequestBody String teamOrder,@PathVariable ("attendanceId") String attendanceId) {
		Attendance attendance=new Attendance();
		attendance.setTeamOrder(Integer.valueOf(teamOrder));
		attendanceService.updateTeamOrderByAttendanceId(new BigInteger(attendanceId), attendance);
	}
	/*
	 * 取消报名展示
	 */
	@DeleteMapping("/{attendanceId}")
	public Map<String, Object> deleteAttendance(@PathVariable ("attendanceId") String attendanceId) {
		attendanceService.deleteAttendanceByAttendanceId(new BigInteger(attendanceId));
		Map<String,Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
	}
	/*
	 * 上传讨论课报告
	 */
	@PostMapping(value="/{attendanceId}/report", consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
	public void uploadReport(@RequestParam ("file") MultipartFile file,@PathVariable ("attendanceId") String attendanceId) {
		attendanceService.uploadReport(new BigInteger(attendanceId), file);	
	}
	/*
	 * 重传讨论课报告
	 */
	@PutMapping(value="/{attendanceId}/report",consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
	public void reUploadReport(@RequestParam ("file") MultipartFile file,@PathVariable ("attendanceId") String attendanceId) {
		attendanceService.uploadReport(new BigInteger(attendanceId), file);	
		//加一个清除原有报告的功能，不然浪费存储空间
	}
	/*
	 * 下载讨论课报告
	 */
	@GetMapping("/{attendanceId}/report")
	public void downloadReport(@PathVariable ("attendanceId") String attendanceId,HttpServletResponse response) {
		attendanceService.downloadReport(new BigInteger(attendanceId),response);
	}
	/*
	 * 上传讨论课ppt
	 */
	@PostMapping(value="/{attendanceId}/powerpoint",consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
	public void uploadPPT(@RequestParam ("file") MultipartFile file,@PathVariable ("attendanceId") String attendanceId) {
		attendanceService.uploadPPT(new BigInteger(attendanceId), file);	
	}
	/*
	 * 重传讨论课ppt
	 */
	@PutMapping(value="/{attendanceId}/powerpoint",consumes = "multipart/form-data", produces = MediaType.APPLICATION_JSON_VALUE)
	public void reUploadPPT(@RequestParam ("file") MultipartFile file,@PathVariable ("attendanceId") String attendanceId) {
		attendanceService.uploadPPT(new BigInteger(attendanceId), file);	
		//重传ppt,先把服务器上的旧文件删了
	}
	/*
	 * 下载讨论课PPT
	 */
	@GetMapping("/{attendanceId}/powerpoint")
	public void downloadPPT(@PathVariable ("attendanceId") String attendanceId,HttpServletResponse response) {
		attendanceService.downloadPPT(new BigInteger(attendanceId), response);
	}
	/*以下内容在seminarController*/
	/*
	 * 下载此次讨论课的所有报告
	 */

	@GetMapping("/{seminarId}/class/{classId}/report")
	public void downloadAllReport(@PathVariable ("seminarId") String seminarId,@PathVariable ("classId") String classId,HttpServletResponse response) {
		attendanceService.downloadAllReport(new BigInteger(seminarId),new BigInteger(classId), response);
	}
	/*
	 * 下载此次讨论课的所有ppt
	 */
	@GetMapping("/{seminarId}/class/{classId}/ppt")
	public void downloadAllPPT(@PathVariable ("seminarId") String seminarId,@PathVariable ("classId") String classId,HttpServletResponse response) {
		attendanceService.downloadAllPPT(new BigInteger(seminarId),new BigInteger(classId), response);
	}
	/*
	* 获得讨论课展示关于展示小组的相关东西
	*/
	@GetMapping("/{classSeminarId}/attendance")
	public List<Attendance> getAttendanceByClassSeminarId(@PathVariable ("classSeminarId") int classSeminarId){
		return attendanceDao.selectAttendanceByClassSeminarId(classSeminarId);
	}
	/*
	 * 报名展示		
	 */
	@PostMapping("/{seminarId}/class/{classId}/attendance")
	public BigInteger signupPresentation(@RequestBody Map<String, Object> teamAndOrderMap,@PathVariable ("seminarId") String seminarId,@PathVariable("classId") String classId) {

		return attendanceService.insertAttendance(teamAndOrderMap,seminarId,classId);
		
	}
	
	/*
	 * 获取某队伍在相应讨论课的报名情况	
	 */
	@GetMapping("/{teamId}/team/{classSeminarId}/classSeminar")
	public Map<String, Object> selectAttendanceByTeamIdAndClassSeminarId(@PathVariable ("teamId") Integer teamId,@PathVariable("classSeminarId") Integer classSeminarId) {
		Map<String,Object> result=new HashMap<>();
		Attendance attendance=attendanceDao.selectAttendanceByTeamIdAndClassSeminarId(teamId, classSeminarId);
		if(attendance==null)result.put("status", "noSign");
		else result.put("status", "sign");
		result.put("attendance",attendance);
		return result;	
	}
	
}
