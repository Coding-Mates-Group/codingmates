package com.gbc.codingmates.service.member;

import com.gbc.codingmates.domain.member.Member;
import com.gbc.codingmates.domain.member.MemberRepository;
import com.gbc.codingmates.event.SaveFileToS3Event;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfileService {

    private final ApplicationEventPublisher eventPublisher;
    private final MemberRepository memberRepository;

    @Transactional
    public ResponseEntity saveProfile(MultipartFile profile, Long memberId, Long jwtMemberId) {
        if (memberId != jwtMemberId) {
            throw new IllegalArgumentException("invalid access for update profile");
        }

        Member member = memberRepository.findById(memberId)
            .orElseThrow(
                () -> new IllegalArgumentException(String.format("member not exist %s", memberId))
            );

        SaveFileToS3Event saveFileToS3Event = SaveFileToS3Event.of(profile,
            String.valueOf(memberId));
        eventPublisher.publishEvent(saveFileToS3Event);

        member.mapMemberProfileImagePath(saveFileToS3Event.getFileURL());

        return ResponseEntity.ok().build();
    }

}
