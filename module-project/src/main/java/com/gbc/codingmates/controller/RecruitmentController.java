package com.gbc.codingmates.controller;

import com.gbc.codingmates.domain.recruitment.RecruitmentRepository;
import com.gbc.codingmates.dto.Recruitment.RecruitmentDto;
import com.gbc.codingmates.service.recruitment.RecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recruitment")
public class RecruitmentController {
    private final RecruitmentService recruitmentService;

    //create and save recruitment details
    public ResponseEntity<RecruitmentDto> createRecruitment(@RequestParam final int count,
                                                            @RequestParam final String type,
                                                            @RequestParam final String status){
        return recruitmentService.createRecruitment(count, type, status);
    }
}
