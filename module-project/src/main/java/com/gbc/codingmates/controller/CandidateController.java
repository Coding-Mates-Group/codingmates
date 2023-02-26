package com.gbc.codingmates.controller;

import com.gbc.codingmates.annotation.JwtMemberInfo;
import com.gbc.codingmates.domain.candidate.Candidate;
import com.gbc.codingmates.dto.CandidateDto;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/candidate")
@Validated
@RequiredArgsConstructor
public class CandidateController {
    private final CandidateService candidateService;

    //save candidate's application
    @PostMapping("")
    public ResponseEntity saveCandidate(@RequestBody @Valid final CandidateDto candidateDto,
                                              BindingResult bindingResult){
        ResponseEntity<BindingResult> checkViaBindingResult = checkViaBindingResult(bindingResult);
        if (checkViaBindingResult != null) return checkViaBindingResult;
        candidateService.saveCandidate(candidateDto);
        return ResponseEntity.ok(candidateDto.getId());
    }

    //check via binding result
    private ResponseEntity<BindingResult> checkViaBindingResult(BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.badRequest().body(bindingResult);
        }
        return null;
    }

    //list all candidates
    @GetMapping("")
    public ResponseEntity<List<Candidate>> listAll(){
        return ResponseEntity.ok(candidateService.listAll());
    }

    //project leader accepts candidate's application
    @PostMapping("/{id}")
    public ResponseEntity<Long> accept(@PathVariable final Long id, @JwtMemberInfo @Valid final MemberDto memberDto,
                                       @Valid final CandidateDto candidateDto, BindingResult bindingResult) throws Exception{
        candidateService.accept(id, memberDto, candidateDto);
        return ResponseEntity.ok(id);
    }

    //reject candidate's application
    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable final Long id, @JwtMemberInfo @Valid final MemberDto memberDto,
                                       @Valid final CandidateDto candidateDto, BindingResult bindingResult) throws Exception{
        candidateService.reject(id, memberDto, candidateDto);
        return ResponseEntity.ok(id);
    }
}
