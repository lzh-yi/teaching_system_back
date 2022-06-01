package com.example.demo.service.impl;

import com.example.demo.service.FileUploadService;
import com.example.demo.vo.FileUploadVo;
import com.example.demo.vo.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {
  @Override
  public Result uploadFile(MultipartFile file) throws IOException {
    // 控制上传的文件格式
//    String fileType = file.getContentType();
//    String name = file.getOriginalFilename();
//    String[] fileTypeList = {"text/plain", "application/msword", "application/pdf", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"};
//    String[] picSuffixList = {".pdf", ".doc", ".docx"};
//    // 获取后缀名
//    String[] temp = name.split("\\.");
//
//    String filePic = temp[temp.length - 1];
//    if (!Arrays.asList(picSuffixList).contains(filePic)) return Result.fail(300, "请上传正确的文件格式");
//    if (!Arrays.asList(fileTypeList).contains(fileType)) return Result.fail(300, "请上传正确的文件格式");

    String fileName = file.getOriginalFilename();
    String uuuid = UUID.randomUUID().toString();

    String paths = "D:/teaching_platform/upload_file";
    File f = new File(paths + uuuid + fileName);
    file.transferTo(f);

    FileUploadVo fileUploadVo = new FileUploadVo();
    fileUploadVo.setFileName(fileName);
    fileUploadVo.setFilePath(paths + uuuid + fileName);

    return Result.success(fileUploadVo, null);

  }

}
