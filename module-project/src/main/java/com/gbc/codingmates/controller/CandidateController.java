package com.gbc.codingmates.controller;

import com.gbc.codingmates.annotation.JwtMemberInfo;
import com.gbc.codingmates.domain.candidate.Candidate;
import com.gbc.codingmates.dto.candidate.CandidateDto;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.service.candidate.CandidateService;
import com.gbc.codingmates.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidate")
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    //save candidate's application
    public ResponseEntity saveCandidate(@RequestBody @Valid final CandidateDto candidateDto,
                                              BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return candidateService.saveCandidate(candidateDto);
    }

    //list all candidates
    public ResponseEntity<List<Candidate>> listAll(){
        return candidateService.listAll();
    }

    //reject candidate's application
    public ResponseEntity<Long> delete(@JwtMemberInfo final MemberDto memberDto,
                                       final CandidateDto candidateDto) throws AccessDeniedException{
        return candidateService.reject(memberDto, candidateDto);
    }

    public ResponseEntity<Long> accept(@JwtMemberInfo final MemberDto memberDto,
                                       final CandidateDto candidateDto) throws AccessDeniedException{
        return candidateService.accept(memberDto, candidateDto);
    }
}
