package com.rcloud.common.file.controller;

import static com.rcloud.framework.common.http.ResponseMessage.error;
import static com.rcloud.framework.common.http.ResponseMessage.ok;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rcloud.common.file.service.FileService;
import com.rcloud.framework.common.http.ResponseMessage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * <Description> 文件服务对外提供的REST接口
 * 
 * @author wanghao
 * @CreateDate 2018年3月26日 上午10:09:47
 * @since V1.0
 * @see com.rcloud.common.file.controller
 */
@Slf4j
@RestController
@RequestMapping("/base/file")
@Api(value = "文件管理, 主要功能包含文件上传，文件下载以及文件预览等")
public class FileController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ApiOperation(value = "单文件上传", notes = "单个文件上传")
    public ResponseMessage<?> upload(@RequestParam("file") MultipartFile file, HttpServletRequest request,
        HttpServletResponse response) {
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");

            return ok(fileService.upload(file));
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return error(e);
        }
    }

    @RequestMapping(value = "/upload/batch", method = RequestMethod.POST)
    @ApiOperation(value = "多文件上传", notes = "文件批量上传")
    public ResponseMessage<?> batchupload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request,
        HttpServletResponse response) throws Exception {
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Access-Control-Allow-Origin", "*");

            return ok(fileService.upload(files));
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            return error(e);
        }
    }
}
