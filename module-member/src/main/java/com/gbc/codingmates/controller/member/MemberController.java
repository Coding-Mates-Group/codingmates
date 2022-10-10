package com.gbc.codingmates.controller.member;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import com.gbc.codingmates.dto.form.MemberJoinDto;
import com.gbc.codingmates.service.member.MemberService;
import com.gbc.codingmates.service.member.ProfileService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {

    private final MemberService memberService;
    private final ProfileService profileService;


    @PostMapping(value = "", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity register(@RequestBody @Validated MemberJoinDto request,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().toString());
        }
        return memberService.join(request);
    }

    @GetMapping("{id}")
    public ResponseEntity findMemberById(@PathVariable("id") Long memberID,
        @RequestParam(defaultValue = "profile") List<String> scope) {
        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "{id}/profiles", consumes = {APPLICATION_JSON_VALUE,
        MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity saveProfileImage(@PathVariable Long id,
        @RequestPart MultipartFile profile) {
        return profileService.saveProfile(profile, id, id);
    }
}
