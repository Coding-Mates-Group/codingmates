package com.gbc.codingmates.dto.form;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Schema(description = "DTO for member register")
public class MemberJoinDto {

    @NotEmpty(message = "user alias must not empty")
    @Length(min = 4, max = 20, message = "user alias length must be 4 ~ 20 words")
    @Pattern(regexp = "^[a-z0-9]*", message = "user alias can be made by numbers and lower spelling")
    @Schema(description = "member alias for register", required = true, pattern = "^[a-z0-9]*", maxLength = 20, minLength = 4)
    private String userAlias;

    @NotEmpty(message = "token must not empty")
    @Schema(description = "token provided by OAuth provider", required = true)
    private String token;

    @Schema(description = "member's skill ids", required = false)
    private List<Long> skillIds = new ArrayList<>();
}
