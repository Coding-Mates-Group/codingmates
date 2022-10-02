package com.gbc.codingmates.service.member;

import com.gbc.codingmates.util.FileHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final FileHandler fileHandler;

    public ResponseEntity saveProfile(MultipartFile profile, Long memberId, Long jwtMemberId) {
        if (memberId != jwtMemberId) {
            throw new IllegalArgumentException("invalid access for update profile");
        }
        fileHandler.saveProfileImage(profile, memberId);
        return ResponseEntity.ok().build();
    }

}
