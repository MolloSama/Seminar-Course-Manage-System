package com.bkg.coursemanager.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bkg.coursemanager.dao.AttendanceDao;
import com.bkg.coursemanager.dao.ClassSeminarDao;
import com.bkg.coursemanager.entity.Attendance;
import com.bkg.coursemanager.entity.Class;
import com.bkg.coursemanager.entity.ClassSeminar;
import com.bkg.coursemanager.entity.Seminar;
import com.bkg.coursemanager.entity.Team;

/*
 * @author JQQ
 * @date 2018/12/20
 */
@Service
public class AttendanceService {
	@Autowired
	private AttendanceDao attendanceDao;
	@Autowired
	private ClassSeminarDao classSeminarDao;

	// .mkdir只会在当前文件夹新建一个文件夹，不会连续建多级文件夹，因此这里/upload文件夹必须预先存在
	public static String reportDir = "D:/report";// linux不用指定那个盘，直接指定文件夹路径
	public static String pptDir = "D:/ppt";
	public static String zipDir = "D:/zip";

	public Boolean uploadReport(BigInteger attendanceId, MultipartFile file) {
		// 这里，如果linux上预先建立了uploadDirectory这个文件夹，则此语句可以删除（发布前先在服务器建/report和/ppt）
		new File(reportDir).mkdir();

		Attendance attendance = attendanceDao.selectAttendanceByAttendanceId(attendanceId);
		ClassSeminar classSeminar = attendance.getClassSeminar();
		String dir = reportDir + "/" + String.valueOf(classSeminar.getId());
		new File(dir).mkdir();

		// String testDir=reportDir+"/2";
		// new File(testDir).mkdir();

		if (file.isEmpty()) {
			// 此处思考如何让前端知晓传了空文件
		}
		// 解决edge浏览器返回文件名包含文件路径的问题，得到真正只包含文件名的fileName
		String fileName = "";
		if (file.getOriginalFilename() != null) {
			fileName = FilenameUtils.getName(file.getOriginalFilename());
		}

		Path fileNameAndPath = Paths.get(dir, fileName);

		try {
			Files.write(fileNameAndPath, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		Attendance attendance2 = new Attendance();
		attendance2.setReportName(fileName);
		attendance2.setReportUrl(fileNameAndPath.toString());
		return attendanceDao.updateReportByAttendanceId(attendanceId, attendance2);
	}

	public void uploadPPT(BigInteger attendanceId, MultipartFile file) {
		// 这里，如果linux上预先建立了uploadDirectory这个文件夹，则此语句可以删除（发布前先在服务器建/report和/ppt）
		new File(pptDir).mkdir();

		Attendance attendance = attendanceDao.selectAttendanceByAttendanceId(attendanceId);
		ClassSeminar classSeminar = attendance.getClassSeminar();
		String dir = pptDir + "/" + String.valueOf(classSeminar.getId());
		//test
		//String dir = pptDir + "/2";
		new File(dir).mkdir();

		if (file.isEmpty()) {
			// 此处思考如何让前端知晓传了空文件
		}
		// 解决edge浏览器返回文件名包含文件路径的问题，得到真正只包含文件名的fileName
		String fileName = "";
		if (file.getOriginalFilename() != null) {
			fileName = FilenameUtils.getName(file.getOriginalFilename());
		}

		Path fileNameAndPath;
		fileNameAndPath = Paths.get(dir, fileName);
		try {
			Files.write(fileNameAndPath, file.getBytes());
			Attendance attendance2 = new Attendance();
			attendance2.setPptName(fileName);
			attendance2.setPptUrl(fileNameAndPath.toString());
			attendanceDao.updatePPTByAttendanceId(attendanceId, attendance2);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void downloadReport(BigInteger attendanceId, HttpServletResponse response) {

		Attendance attendance = attendanceDao.selectAttendanceByAttendanceId(attendanceId);
		String fileNameAndPath = attendance.getReportUrl();
		String fileName = attendance.getReportName();

		// 激活浏览器的下载功能，并设置支持中文的编码
		// .replaceAll("//+", "%20"):避免文件名中的空格变加号,,好像不起作用啊。。（待解决）
		try {
			response.addHeader("Content-Disposition",
					"attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replaceAll("//+", "%20"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(fileNameAndPath));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void downloadPPT(BigInteger attendanceId, HttpServletResponse response) {

		Attendance attendance = attendanceDao.selectAttendanceByAttendanceId(attendanceId);
		String fileNameAndPath = attendance.getPptUrl();
		String fileName = attendance.getPptName();

		// 激活浏览器的下载功能，并设置支持中文的编码
		try {
			response.addHeader("Content-Disposition",
					"attachment; filename=" + URLEncoder.encode(fileName, "UTF-8").replace("//+", "20%"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(fileNameAndPath));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 打包下载：先将同一文件夹下的文件打包，然后按传输文件的方法传输给客户端
	/**
	 * 将存放在sourceFilePath目录下的源文件，打包成fileName名称的zip文件，并存放到zipFilePath路径下
	 * 
	 * @param sourceFilePath
	 *            :待压缩的文件路径
	 * @param zipFilePath
	 *            :压缩后存放路径
	 * @param fileName
	 *            :压缩后文件的名称
	 * @return
	 */
	public void downloadAllReport(BigInteger seminarId, BigInteger classId, HttpServletResponse response) {
		// 这里，如果linux上预先建立了zipDir这个文件夹，则此语句可以删除
		new File(zipDir).mkdir();
		ClassSeminar classSeminar = classSeminarDao.selectClassSemianrByForeignKey(seminarId, classId);
		//@test,因为还不能连表查询，暂时没有seminarMapper
//		ClassSeminar classSeminar =new ClassSeminar();
//		classSeminar.setId(2);
		// sourceFilePath :待压缩的文件路径
		String sourceFilePath = reportDir + "/" + String.valueOf(classSeminar.getId());
		// zipFilePath :压缩后存放路径
		String zipFilePath = zipDir + "/" + String.valueOf(classSeminar.getId());
		new File(zipFilePath).mkdir();
		String zipFileName = String.valueOf(classSeminar.getId());

		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		if (sourceFile.exists() == false) {
			System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
		} else {
			try {
				File zipFile = new File(zipFilePath + "/" + zipFileName + ".zip");
				if (zipFile.exists()) {// 因为每次下载时文件夹内的东西可能不一样，所以删除旧的zip文件
					zipFile.delete();
				}
				File[] sourceFiles = sourceFile.listFiles();
				if (null == sourceFiles || sourceFiles.length < 1) {
					System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
				} else {
					fos = new FileOutputStream(zipFile);
					zos = new ZipOutputStream(new BufferedOutputStream(fos));
					byte[] bufs = new byte[1024 * 10];
					for (int i = 0; i < sourceFiles.length; i++) {
						// 创建ZIP实体，并添加进压缩包
						ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
						zos.putNextEntry(zipEntry);
						// 读取待压缩的文件并写进压缩包里
						fis = new FileInputStream(sourceFiles[i]);
						bis = new BufferedInputStream(fis, 1024 * 10);
						int read = 0;
						while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
							zos.write(bufs, 0, read);
						}
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				// 关闭流
				try {
					if (null != bis)
						bis.close();
					if (null != zos)
						zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}

		String fileNameAndPathString=zipFilePath+"/"+zipFileName + ".zip";
		// 激活浏览器的下载功能，并设置支持中文的编码
		try {
			response.addHeader("Content-Disposition",
					"attachment; filename=" + URLEncoder.encode(zipFileName + ".zip", "UTF-8").replace("//+", "20%"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		byte[] buff = new byte[1024];
		BufferedInputStream bis2 = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis2 = new BufferedInputStream(new FileInputStream(fileNameAndPathString));
			int i = bis2.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis2.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis2 != null) {
				try {
					bis2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	// 打包下载：先将同一文件夹下的文件打包，然后按传输文件的方法传输给客户端
	public void downloadAllPPT(BigInteger seminarId, BigInteger classId, HttpServletResponse response) {
		// 这里，如果linux上预先建立了zipDir这个文件夹，则此语句可以删除
		new File(zipDir).mkdir();
		ClassSeminar classSeminar = classSeminarDao.selectClassSemianrByForeignKey(seminarId, classId);
		// sourceFilePath :待压缩的文件路径
		String sourceFilePath = reportDir + "/" + String.valueOf(classSeminar.getId());
		// zipFilePath :压缩后存放路径
		String zipFilePath = zipDir + "/" + String.valueOf(classSeminar.getId());
		new File(zipFilePath).mkdir();
		String zipFileName = String.valueOf(classSeminar.getId());

		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;

		if (sourceFile.exists() == false) {
			System.out.println("待压缩的文件目录：" + sourceFilePath + "不存在.");
		} else {
			try {
				File zipFile = new File(zipFilePath + "/" + zipFileName + ".zip");
				if (zipFile.exists()) {// 因为每次下载时文件夹内的东西可能不一样，所以删除旧的zip文件
					zipFile.delete();
				}
				File[] sourceFiles = sourceFile.listFiles();
				if (null == sourceFiles || sourceFiles.length < 1) {
					System.out.println("待压缩的文件目录：" + sourceFilePath + "里面不存在文件，无需压缩.");
				} else {
					fos = new FileOutputStream(zipFile);
					zos = new ZipOutputStream(new BufferedOutputStream(fos));
					byte[] bufs = new byte[1024 * 10];
					for (int i = 0; i < sourceFiles.length; i++) {
						// 创建ZIP实体，并添加进压缩包
						ZipEntry zipEntry = new ZipEntry(sourceFiles[i].getName());
						zos.putNextEntry(zipEntry);
						// 读取待压缩的文件并写进压缩包里
						fis = new FileInputStream(sourceFiles[i]);
						bis = new BufferedInputStream(fis, 1024 * 10);
						int read = 0;
						while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
							zos.write(bufs, 0, read);
						}
					}
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				// 关闭流
				try {
					if (null != bis)
						bis.close();
					if (null != zos)
						zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}

		String fileNameAndPath=zipFilePath+"/"+zipFileName + ".zip";
		// 激活浏览器的下载功能，并设置支持中文的编码
		try {
			response.addHeader("Content-Disposition",
					"attachment; filename=" + URLEncoder.encode(zipFileName + ".zip", "UTF-8").replace("//+", "20%"));
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		byte[] buff = new byte[1024];
		BufferedInputStream bis2 = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis2 = new BufferedInputStream(new FileInputStream(fileNameAndPath));
			int i = bis2.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis2.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bis2 != null) {
				try {
					bis2.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public Boolean updateTeamOrderByAttendanceId(BigInteger attendanceId, Attendance attendance) {
		Boolean res = attendanceDao.updateTeamOrderByAttendanceId(attendanceId, attendance);
		return res;
	}

	/*
	 * 根据展示Id删除展示
	 */
	public void deleteAttendanceByAttendanceId(BigInteger attendanceId) {
		attendanceDao.deleteAttendanceByAttendanceId(attendanceId);
	}

	/*
	 * 根据展示Id查询展示信息,此接口也可用于多处查询
	 */
	public Attendance selectAttendanceByAttendanceId(BigInteger attendanceId) {
		Attendance attendance = attendanceDao.selectAttendanceByAttendanceId(attendanceId);
		return attendance;
	}

	/*
	 * 根据seminarId和classId查询服务器上所有文件的压缩包：查一个方法，将批量文件打包 这里查出来有用处的只有url+name 此方法未解决
	 * 如果需要用seminarId和classId
	 * 查到attendance表，则现在ClassSeminar.xml通过seminarId和classId查到attendanceId
	 * 再将attendanceId传到selectAttendanceByAttendanceId方法
	 */
	public List<Attendance> listAttendance(BigInteger seminarId, BigInteger classId) {
		List<Attendance> attendances = attendanceDao.listAttendance(seminarId, classId);

		return attendances;
	}

	/*
	 * 报名展示,实际上就是创建一条attendance记录
	 */
	public BigInteger insertAttendance(Map<String, Object> attendanceMap, String seminarId, String classId) {
		Seminar seminar = new Seminar();
		seminar.setId(Integer.valueOf(seminarId));

		Class class1 = new Class();
		class1.setId(Integer.valueOf(classId));

		Attendance attendance = new Attendance();

		ClassSeminar classSeminar = classSeminarDao.selectClassSemianrByForeignKey(new BigInteger(seminarId),
				new BigInteger(classId));

		attendance.setClassSeminar(classSeminar);
		Team team = new Team();
		team.setId(Integer.valueOf(attendanceMap.get("teamId").toString()));
		attendance.setClassSeminar(classSeminar);
		attendance.setTeam(team);
		attendance.setTeamOrder(((int) attendanceMap.get("teamOrder")));
		attendanceDao.insertAttendance(attendance);
		return BigInteger.valueOf(attendance.getId());
	}

}
