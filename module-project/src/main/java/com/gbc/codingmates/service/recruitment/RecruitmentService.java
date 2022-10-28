package com.gbc.codingmates.service.recruitment;

import com.gbc.codingmates.domain.candidate.CandidateRepository;
import com.gbc.codingmates.domain.recruitment.Recruitment;
import com.gbc.codingmates.domain.recruitment.RecruitmentRepository;
import com.gbc.codingmates.dto.Recruitment.RecruitmentDto;
import com.gbc.codingmates.service.candidate.CandidateService;
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
    public ResponseEntity<RecruitmentDto> createRecruitment(final int count, final String type, final String status){
        RecruitmentDto recruitmentDto  = Recruitment.from(Recruitment.builder()
                .recruitmentCount(count)
                .recruitmentType(type)
                .recruitmentStatus(status)
                .build());
        Recruitment recruitment = Recruitment.toEntity(recruitmentDto);
        recruitmentRepository.save(recruitment);
        return ResponseEntity.ok(recruitmentDto);
    }

    //edit recruitment details


}
