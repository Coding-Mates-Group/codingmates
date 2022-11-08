package com.gbc.codingmates;

import com.gbc.codingmates.annotation.JwtMemberInfo;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.utils.S3FileService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RequestMapping("/s3")
@RestController
public class S3Controller {
    private final S3FileService s3FileService;

    @ApiOperation(value = "upload file to S3")
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@ApiParam(value = "upload 1 image file to S3", required = true)
                                                 @RequestParam("image") MultipartFile multipartFile,
                                             @JwtMemberInfo MemberDto memberDto)
            throws IOException{
        return ResponseEntity.ok(s3FileService.upload(multipartFile, memberDto));
    }

    @ApiOperation(value = "delete uploaded file from S3")
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFile(@ApiParam(value = "delete uploaded image file from S3") @RequestParam String fileName){
        //fileName to be modified to under member username
        s3FileService.deleteFile(fileName);
        return ResponseEntity.ok(null);
    }
}
