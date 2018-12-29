package com.bkg.coursemanager.service;

import org.springframework.web.multipart.MultipartFile;


/**
 * @Description 用于EXCEL导入的Service层接口
 * @author Weilun Zhang
 * @version v2.0
 * @date 2018/12/23
 */

public interface ImportExcelService {

    boolean batchImport(String fileName, MultipartFile file, int classId, int courseId) throws Exception;

}
