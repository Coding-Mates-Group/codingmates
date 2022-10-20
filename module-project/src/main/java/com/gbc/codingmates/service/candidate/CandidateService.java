package com.gbc.codingmates.service.candidate;

import com.gbc.codingmates.domain.candidate.Candidate;
import com.gbc.codingmates.domain.candidate.CandidateRepository;
import com.gbc.codingmates.dto.candidate.CandidateDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CandidateService {
    private final CandidateRepository candidateRepository;
    private final ModelMapper modelMapper;

    //save candidate's application
    @Transactional
    public ResponseEntity<Long> saveCandidate(final CandidateDto candidateDto){
        Candidate candidate = modelMapper.map(candidateDto, Candidate.class);
        candidateRepository.save(modelMapper.map(candidateDto, Candidate.class));
        return ResponseEntity.ok(candidate.getId());
    }

    //list all
    public ResponseEntity<List<Candidate>> listAll(){
        return ResponseEntity.ok(candidateRepository.findAll());
    }

    //update/edit candidate's application
    @Transactional
    public ResponseEntity<Long> edit(final CandidateDto candidateDto){
        Candidate candidate = candidateRepository.findById(candidateDto.getId());
    }





}
