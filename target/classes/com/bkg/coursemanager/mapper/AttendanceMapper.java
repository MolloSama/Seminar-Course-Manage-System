package com.bkg.coursemanager.mapper;

import java.math.BigInteger;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.bkg.coursemanager.entity.Attendance;

/*
 * @author JQQ
 * @date 2018/12/21
 */
@Mapper
@Component
public interface AttendanceMapper {
	/*
	 * 根据展示Id更新展示信息,此接口可用于更新teamOrder,reportUrl+reportName,pptUrl+pptName
	 */
	
	Integer updateReportByAttendanceId(@Param("attendanceId") BigInteger attendanceId,@Param("attendance") Attendance attendance);
	
	Integer updatePPTByAttendanceId(@Param("attendanceId") BigInteger attendanceId,@Param("attendance") Attendance attendance);
	
	Integer updateTeamOrderByAttendanceId(@Param("attendanceId") BigInteger attendanceId,@Param("attendance") Attendance attendance);
	
	/*
	 * 根据展示Id删除展示
	 */
	void deleteAttendanceByAttendanceId(@Param("attendanceId") BigInteger attendanceId);
	
	/*
	 * 根据展示Id查询展示信息,此接口也可用于多处查询
	 */
	Attendance selectAttendanceByAttendanceId(@Param("attendanceId") BigInteger attendanceId);
	
	/*
	 * 根据seminarId和classId查询服务器上所有文件的压缩包：查一个方法，将批量文件打包
	 * 这里查出来有用处的只有url+name
	 *此方法未解决
	 *如果需要用seminarId和classId 查到attendance表，则现在ClassSeminar.xml通过seminarId和classId查到attendanceId
	 *再将attendanceId传到selectAttendanceByAttendanceId方法
	 */
	List<Attendance> listAttendance(@Param("seminarId") BigInteger seminarId,@Param("classId") BigInteger classId);
	
	/*
	 * 报名展示,实际上就是创建一条attendance记录
	 */
	Integer insertAttendance(@Param("attendance") Attendance attendance);
	
	Attendance selectAttendanceByTeamIdAndClassSeminarId(@Param("teamId") int teamId,@Param("classSeminarId") int classSeminarId);
	
	List<Attendance> selectAttendanceByClassSeminarId(@Param("classSeminarId") int classSeminarId);

}
