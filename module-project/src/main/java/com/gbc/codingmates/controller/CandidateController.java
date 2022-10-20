package com.gbc.codingmates.controller;

import com.gbc.codingmates.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final ProjectService projectService;


}
