package com.bkg.coursemanager.controller;

import com.bkg.coursemanager.service.ImportExcelService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * @Description EXCEL导入用Controller
 * @author Weilun Zhang
 * @version v1.0
 * @date 2018/12/04
 */

@RestController
@RequestMapping("/exceltesting")
public class ImportExcel {

    @Autowired
    private ImportExcelService testService;

    @RequestMapping(value="/import")
    public boolean addAccount() {
        boolean a = false;

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
            a = testService.batchImport(fileName, multipartFile,30, 330);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }

/*    @PostMapping("/import")
    public boolean addUser(@RequestParam("file") MultipartFile file) {
        boolean a = false;
        String fileName = file.getOriginalFilename();
        try {
            a = testService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }*/



}
