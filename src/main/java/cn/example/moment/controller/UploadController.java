package cn.example.moment.controller;

import cn.example.moment.annotation.CurrentUser;
import cn.example.moment.annotation.LoginRequired;
import cn.example.moment.api.BaseResponse;
import cn.example.moment.pojo.StorageEntity;
import cn.example.moment.pojo.UserEntity;
import cn.example.moment.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
public class UploadController {
    private String localStorage = "/Users/mac/IdeaProjects/moment-server/moment-server/LocalStorage/";
    private String domainName = "http://39.107.77.70:8888/files/";
    @Autowired
    private StorageService storageService;

    @LoginRequired
    @PostMapping("/uploadFile")
    public BaseResponse<StorageEntity> uploadSingleFile(@CurrentUser UserEntity currentUser, @RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile == null || multipartFile.isEmpty()) {
            return new BaseResponse<>(-200, "请勿上传空文件！");
        }
        String originalFilename = multipartFile.getOriginalFilename();
        try {
            File file = new File(localStorage, originalFilename);
            multipartFile.transferTo(file);
            StorageEntity storageEntity = new StorageEntity();
            storageEntity.setFile_name(originalFilename);
            storageEntity.setFile_path(file.getAbsolutePath());
            storageEntity.setOperator_id(currentUser.getId());
            storageEntity.setFile_url(domainName + originalFilename);
            storageService.addStorageFile(storageEntity);
            return new BaseResponse<>(storageEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaseResponse<>(-200, "文件上传失败！");
    }

    @LoginRequired
    @PostMapping("/uploadFiles")
    public BaseResponse<List<StorageEntity>> uploadFiles(@CurrentUser UserEntity currentUser,@RequestParam("files")MultipartFile[] multipartFiles) {
        if (multipartFiles == null || multipartFiles.length == 0) {
            return new BaseResponse<>(-200, "请勿上传空文件！");
        }
        List<StorageEntity> storageEntities = Arrays.stream(multipartFiles).map(multipartFile -> {
            String originalFilename = multipartFile.getOriginalFilename();
            try {
                File file = new File(localStorage, originalFilename);
                multipartFile.transferTo(file);
                StorageEntity storageEntity = new StorageEntity();
                storageEntity.setFile_name(originalFilename);
                storageEntity.setFile_path(file.getAbsolutePath());
                storageEntity.setOperator_id(currentUser.getId());
                storageEntity.setFile_url(domainName + originalFilename);
                storageService.addStorageFile(storageEntity);
                return storageEntity;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return new BaseResponse<>(storageEntities);
    }
}
