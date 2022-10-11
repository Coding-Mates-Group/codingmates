package com.gbc.codingmates.controller.auth;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.gbc.codingmates.api.oAuth.AuthService;
import com.gbc.codingmates.domain.member.OAuthType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
@Api(tags = "AUTH API")
public class AuthController {

    private final AuthService authService;

    @ApiImplicitParam(
        name = "code",
        value = "code provided by OAuth Provider",
        required = true,
        dataType = "String",
        defaultValue = "none"
    )
    @Operation(summary = "Auth By Google", description = "if code is valid, it will be return 'JWT TOKEN' or 'Redirect To register page", responses = {
        @ApiResponse(
            responseCode = "200",
            description = "인증 성공",
            content = @Content(schema = @Schema(description = "jwt token"))
        ),
        @ApiResponse(
            responseCode = "301",
            description = "인증 성공, 회원가입 필요",
            content = @Content(schema = @Schema(description = "/members?token={}"))
        )
    })
    @GetMapping("/oauth2/code/google")
    public ResponseEntity googleAuth(@RequestParam("code") String code) {
        return authService.authorizationByAuthCode(code, OAuthType.GOOGLE);
    }

    @Deprecated
    @GetMapping("/oauth2/google")
    private ResponseEntity googleLoginPage() {
        return authService.getLoginURI(OAuthType.GOOGLE);
    }

    @Deprecated
    @GetMapping("/oauth2/github")
    private ResponseEntity githubLoginPage() {
        return authService.getLoginURI(OAuthType.GITHUB);
    }

    @ApiImplicitParam(
        name = "code",
        value = "code provided by OAuth Provider",
        required = true,
        dataType = "String",
        defaultValue = "none"
    )
    @Operation(summary = "Auth By Github", description = "if code is valid, it will be return 'JWT TOKEN' or 'Redirect To register page", responses = {
        @ApiResponse(
            responseCode = "200",
            description = "인증 성공",
            content = @Content(schema = @Schema(description = "jwt token"))
        ),
        @ApiResponse(
            responseCode = "301",
            description = "인증 성공, 회원가입 필요",
            content = @Content(schema = @Schema(description = "/members?token={}"))
        )
    })
    @ResponseBody
    @GetMapping("/oauth2/code/github")
    public ResponseEntity githubAuth(@RequestParam("code") String code) {
        return authService.authorizationByAuthCode(code, OAuthType.GITHUB);
    }

    @Deprecated
    @GetMapping("/oauth2/facebook")
    public ResponseEntity facebookLoginPage() {
        return authService.getLoginURI(OAuthType.FACEBOOK);
    }

    @ApiImplicitParam(
        name = "code",
        value = "code provided by OAuth Provider",
        required = true,
        dataType = "String",
        defaultValue = "none"
    )
    @Operation(summary = "Auth By Facebook", description = "if code is valid, it will be return 'JWT TOKEN' or 'Redirect To register page", responses = {
        @ApiResponse(
            responseCode = "200",
            description = "인증 성공",
            content = @Content(schema = @Schema(description = "jwt token"))
        ),
        @ApiResponse(
            responseCode = "301",
            description = "인증 성공, 회원가입 필요",
            content = @Content(schema = @Schema(description = "/members?token={}"))
        )
    })
    @ResponseBody
    @GetMapping(value = "/oauth2/code/facebook")
    public ResponseEntity facebookAuth(@RequestParam("code") String code) {
        return authService.authorizationByAuthCode(code, OAuthType.FACEBOOK);
    }


}
