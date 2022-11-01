package com.gbc.codingmates.service;

import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.domain.recruitment.RecruitmentRepository;
import com.gbc.codingmates.dto.RecruitmentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class RecruitmentService {
    private final RecruitmentRepository recruitmentRepository;
    private final CandidateService candidateService;

//    set initial recruitment details
    public RecruitmentDto createRecruitment(final int count, final String type, final String status){
        RecruitmentDto recruitmentDto  = Recruitment.from(Recruitment.builder()
                .recruitmentCount(count)
                .recruitmentType(type)
                .recruitmentStatus(status)
                .build());
        Recruitment recruitment = Recruitment.toEntity(recruitmentDto);
        recruitmentRepository.save(recruitment);
        return recruitmentDto;
    }

    //edit recruitment details of count and type
    public RecruitmentDto editRecruitmentCountAndType(final Long id, final int count, final String type, final String status){
        RecruitmentDto recruitmentDto  = Recruitment.from(Recruitment.builder()
                .recruitmentCount(count)
                .recruitmentType(type)
                .recruitmentStatus(status)
                .build());
        Recruitment recruitment = Recruitment.toEntity(recruitmentDto);
        recruitmentRepository.save(recruitment);
        return recruitmentDto;
    }

    private Recruitment findRecruitmentById(Long id) {
        Recruitment recruitment = recruitmentRepository.findById(id).get();
        return recruitment;
    }
}
