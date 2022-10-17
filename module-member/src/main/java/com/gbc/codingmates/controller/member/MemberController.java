package com.gbc.codingmates.controller.member;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

import com.gbc.codingmates.annotation.JwtMemberInfo;
import com.gbc.codingmates.dto.MemberAliasCheck;
import com.gbc.codingmates.dto.form.MemberJoinDto;
import com.gbc.codingmates.dto.member.MemberDto;
import com.gbc.codingmates.service.member.MemberService;
import com.gbc.codingmates.service.member.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
@Api(tags = "MEMBER CRUD API")
public class MemberController {

    private final MemberService memberService;
    private final ProfileService profileService;


    @ApiOperation(value = "register member", notes = "register member")
    @PostMapping(value = "", consumes = {APPLICATION_JSON_VALUE})
    public ResponseEntity register(@RequestBody @Validated MemberJoinDto request,
        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().toString());
        }
        return memberService.join(request);
    }

    @ApiOperation(value = "get information of member", notes = "get")
    @ApiImplicitParams(
        {
            @ApiImplicitParam(
                name = "id",
                value = "target Member Id",
                required = true,
                dataType = "Long",
                defaultValue = "none"
            ),
            @ApiImplicitParam(
                name = "scope",
                value = "userInfo scope  profile, project",
                required = false,
                dataType = "List<String>",
                defaultValue = "profile"
            )
        }
    )
    @GetMapping("{id}")
    public ResponseEntity findMemberById(@PathVariable("id") Long memberId,
        @RequestParam(defaultValue = "profile") List<String> scope) {
        return memberService.getMemberInfo(memberId);
    }


    @ApiOperation(value = "save member's profile")
    @ApiImplicitParams(
        {
            @ApiImplicitParam(
                name = "id",
                value = "target Member Id",
                required = true,
                dataType = "Long",
                defaultValue = "none"
            )
        }
    )
    @PostMapping(value = "{id}/profiles", consumes = {MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity saveProfileImage(@PathVariable Long id,
        @RequestPart MultipartFile profile,
        @ApiIgnore @JwtMemberInfo MemberDto memberDto) {
        return profileService.saveProfile(profile, id, memberDto.getMemberId());
    }

    @PostMapping(value = "/alias/check")
    public ResponseEntity checkAliasDuplicated(@RequestBody MemberAliasCheck memberAliasCheck) {
        return memberService.checkAlias(memberAliasCheck);
    }
}
