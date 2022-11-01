package com.gbc.codingmates.controller;

import com.gbc.codingmates.dto.RecruitmentDto;
import com.gbc.codingmates.service.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    //create and save recruitment details
    public ResponseEntity<RecruitmentDto> createRecruitment(@RequestParam final int count,
                                                            @RequestParam final String type,
                                                            @RequestParam final String status){
        return ResponseEntity.ok(recruitmentService.createRecruitment(count, type, status));
    }
}
