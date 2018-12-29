package com.bkg.coursemanager.dao;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bkg.coursemanager.entity.Attendance;
import com.bkg.coursemanager.mapper.AttendanceMapper;

/*
 * @author JQQ
 * @date 2018/12/21
 */
@Component
public class AttendanceDao {
	@Autowired
	AttendanceMapper attendanceMapper;
	
	/*
	 * 根据展示Id更新展示信息,此接口可用于更新teamOrder,reportUrl+reportName,pptUrl+pptName
	 */
	public Boolean updateReportByAttendanceId(BigInteger attendanceId,Attendance attendance) {
		Integer res=attendanceMapper.updateReportByAttendanceId(attendanceId, attendance);
		if(res>0) {
			return true;
		}
		else {
			//抛出AttendanceNotFoundException
			return false;
		}
	}
	public Boolean updatePPTByAttendanceId(BigInteger attendanceId,Attendance attendance) {
		Integer res=attendanceMapper.updatePPTByAttendanceId(attendanceId, attendance);
		if(res>0) {
			return true;
		}
		else {
			//抛出AttendanceNotFoundException
			return false;
		}
	}
	public Boolean updateTeamOrderByAttendanceId(BigInteger attendanceId,Attendance attendance) {
		Integer res=attendanceMapper.updateTeamOrderByAttendanceId(attendanceId, attendance);
		if(res>0) {
			return true;
		}
		else {
			//抛出AttendanceNotFoundException
			return false;
		}
	}
	
	/*
	 * 根据展示Id删除展示
	 */
	public void deleteAttendanceByAttendanceId(BigInteger attendanceId) {
		attendanceMapper.deleteAttendanceByAttendanceId(attendanceId);
	}
	
	/*
	 * 根据展示Id查询展示信息,此接口也可用于多处查询
	 */
	public Attendance selectAttendanceByAttendanceId(BigInteger attendanceId){
		Attendance attendance=attendanceMapper.selectAttendanceByAttendanceId(attendanceId);
		return attendance;
	}
	
	/*
	 * 根据seminarId和classId查询服务器上所有文件的压缩包：查一个方法，将批量文件打包
	 * 这里查出来有用处的只有url+name
	 *此方法未解决
	 *如果需要用seminarId和classId 查到attendance表，则现在ClassSeminar.xml通过seminarId和classId查到attendanceId
	 *再将attendanceId传到selectAttendanceByAttendanceId方法
	 */
	public List<Attendance> listAttendance(BigInteger seminarId,BigInteger classId){
		List<Attendance> attendances=attendanceMapper.listAttendance(seminarId, classId);
		
		return attendances;
	}
	
	/*
	 * 报名展示,实际上就是创建一条attendance记录
	 */
	public BigInteger insertAttendance(Attendance attendance) {
		Integer res=attendanceMapper.insertAttendance(attendance);
		if (res>0) {
			return BigInteger.valueOf(attendance.getId());
		}
		else {
			return new BigInteger("-1");
		}
	}
	
	public Attendance selectAttendanceByTeamIdAndClassSeminarId(int teamId,int classSeminarId)
	{
		return attendanceMapper.selectAttendanceByTeamIdAndClassSeminarId(teamId, classSeminarId);
	}
	
	public List<Attendance> selectAttendanceByClassSeminarId(int classSeminarId)
	{
		return attendanceMapper.selectAttendanceByClassSeminarId(classSeminarId);
	}
	
	
}
