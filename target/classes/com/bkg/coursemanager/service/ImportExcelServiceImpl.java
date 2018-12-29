package com.bkg.coursemanager.service;

import com.bkg.coursemanager.common.MyException;
import com.bkg.coursemanager.entity.Account;
import com.bkg.coursemanager.entity.User;
import com.bkg.coursemanager.mapper.ImportExcelMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 用于EXCEL导入的接口实现
 * @author Weilun Zhang
 * @version v2.0
 * @date 2018/12/22
 */

@Service
@Transactional(readOnly = true)
public class ImportExcelServiceImpl implements ImportExcelService {

    @Autowired
    private ImportExcelMapper userMapper;


    @Transactional(readOnly = false,rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file, int classId, int courseId) throws Exception {

        boolean notNull = false;
        List<User> studentList = new ArrayList<User>();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            throw new MyException("上传文件格式不正确");
        }
        boolean isExcel2003 = true;
        if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
            isExcel2003 = false;
        }
        InputStream is = file.getInputStream();
        Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
            wb = new XSSFWorkbook(is);
        }
        Sheet sheet = wb.getSheetAt(0);
        if(sheet!=null){
            notNull = true;
        }
        User student;
        for (int r = 2; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            if (row == null){
                continue;
            }

            student = new User();

            //账号account(学生学号)
            String account = row.getCell(0).getStringCellValue();

            if(account == null || account.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,学号account未填写)");
            }

            //姓名name
            String name = row.getCell(1).getStringCellValue();

            if(name == null || name.isEmpty()){
                throw new MyException("导入失败(第"+(r+1)+"行,姓名name未填写)");
            }


            student.setAccount(account);
            student.setName(name);

            studentList.add(student);
        }

        /*在这里的for循环中进行判断：决定执行什么数据库操作*/
        for (User studentRecord : studentList)
        {
            //用唯一标识account来作为是否存在重复记录的判断
            String account = studentRecord.getAccount();

            //执行selectByID方法，查询该记录的account
            int cnt = userMapper.selectByAccount(account);
            //不存在则执行插入操作
            if (cnt == 0)
            {

                userMapper.addStudent(studentRecord);
                System.out.println(" 插入 "+studentRecord);

                //通过学号查询得到学生的ID
                int studentId = userMapper.getStudentId(account);
                //将班级ID、学生ID、课程ID插入klass_student表
                userMapper.addClassStudent(classId, studentId, courseId);

            }
            //已存在则直接取得ID，后存入klass_student关系表
            else {
                //通过学号查询得到学生的ID
                int studentId = userMapper.getStudentId(account);
                //将班级ID、学生ID、课程ID插入klass_student表
                userMapper.addClassStudent(classId, studentId, courseId);
                System.out.println(" 更新 "+studentRecord);
            }
        }

        return notNull;
    }
}
