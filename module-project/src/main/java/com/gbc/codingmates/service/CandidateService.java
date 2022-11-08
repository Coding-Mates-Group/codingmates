package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.candidate.Candidate;
import com.gbc.codingmates.domain.candidate.CandidateRepository;
import com.gbc.codingmates.dto.CandidateDto;
import com.gbc.codingmates.dto.member.MemberDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CandidateService {
    private final CandidateRepository candidateRepository;

    //save candidate's application
    @Transactional
    public Long saveCandidate(final CandidateDto candidateDto){
        Candidate candidate = Candidate.toEntity(candidateDto);
        candidateRepository.save(candidate);
        return candidate.getId();
    }

    //list all
    public List<Candidate> listAll(){
        return candidateRepository.findAll();
    }

    //accept candidate's application
    @Transactional
    public Long accept(final Long id, final MemberDto memberDto, final CandidateDto candidateDto) throws Exception{
        Candidate candidate = findCandidateById(id, new IllegalArgumentException());
        checkPermission(memberDto,candidate);
        candidateRepository.save(candidate);
        return id;
    }

    //reject candidate's application
    @Transactional
    public Long reject(final Long id, final MemberDto memberDto, final CandidateDto candidateDto) throws Exception{
        Candidate candidate = findCandidateById(id, new IllegalArgumentException());
        checkPermission(memberDto,candidate);
        candidateRepository.deleteById(id);
        return id;
    }

    //check if he/she is the team leader that can accept/reject candidate's applications
    private void checkPermission(MemberDto memberDto, Candidate candidate) {
        Long ownerId = candidate.getProject_can().getMember_id();
        if(ownerId != memberDto.getMemberId()){
            throw new IllegalArgumentException("you are not authorised");
        }
    }

    //find candidate by id
    private Candidate findCandidateById(Long id, IllegalArgumentException no_such_candidate) {
        Candidate candidate = candidateRepository.findById(id).orElseThrow(
                ()-> no_such_candidate);
        return candidate;
    }
}
