package com.bkg.coursemanager.controller;
/**
 * @Description Class接口相关Controller
 * @author bao
 * @version v1.2
 * @date 2018/12/21
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.bkg.coursemanager.service.ImportExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;

import com.bkg.coursemanager.dao.ClassDao;
import org.springframework.web.multipart.MultipartFile;

@RestController
@EnableAutoConfiguration
@RequestMapping("/class")
public class ClassController {

	@Autowired
	private ClassDao classDao;

	@Autowired
	private ImportExcelService importExcelService;
	
	@RequestMapping(value = "/{classId}", method=RequestMethod.DELETE)
    public Map<String, Object> deleteClassByClassId(@PathVariable int classId) {
		classDao.deleteClassByClassId(classId);
		Map<String, Object> status=new HashMap<>();
		status.put("status", "success");
		return status;
    }


    /*在指定ID的班级传入学生名单
    * 导入Excel相关*/
	@RequestMapping(value = "/{classId}", method=RequestMethod.PUT)
	public boolean addStudentListIntoClass(@PathVariable int classId, @RequestParam("file") MultipartFile file) {

		//通过classId在klass表中查找courseId
		int courseId = classDao.getCourseIdByClassId(classId);

        boolean result = false;

        String fileName = file.getOriginalFilename();
        try {
            result = importExcelService.batchImport(fileName, file, classId, courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

        //下为导入本地Excel测试代码，勿删

        /*
        boolean testResult = false;
        File file = new File("E:\\2019大三上\\OOAD\\CourseManage\\software_217\\J2EE.xlsx");

        FileInputStream input = null;
        try {
            input = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        MultipartFile multipartFile = null;
        try {
            multipartFile = new MockMultipartFile("file", file.getName(), "text/plain", input);
        } catch (IOException e) {
            e.printStackTrace();
        }


        String fileName = multipartFile.getOriginalFilename();
        try {
            testResult = testService.batchImport(fileName, multipartFile, classId, courseId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  testResult;
        */
	}
}
