package com.gbc.codingmates.dto.form;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class MemberJoinDtoTest {

    ValidatorFactory validatorFactory;
    Validator validator;

    @BeforeEach
    public void init() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    public void exit() {
        validatorFactory.close();
    }

    @ParameterizedTest
    @CsvSource(value = {"thd@naver.com:aa:asdf:false", "thd@naver.com:Aaaa:asdf:false", "thd@naver.com:A aa:asdf:false", "thd@naver.com:a!aa:asdf:false",
        "thd@naver.com:aaaa:asdf:true"}, delimiter = ':')
    public void memberJoinDTOValidateTest(String email, String userAlias, String token, boolean isOk) {
        //given
        MemberJoinDto memberJoinDto = new MemberJoinDto(email, userAlias, token, null);

        //when
        Set<ConstraintViolation<MemberJoinDto>> validatedResult = validator.validate(memberJoinDto);

        //then
        assertThat(validatedResult.size() == 0).isEqualTo(isOk);
    }
}