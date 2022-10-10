package com.gbc.codingmates.dto.form;

import static org.assertj.core.api.Assertions.assertThat;

import com.gbc.codingmates.dto.form.MemberJoinDto;
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
    @CsvSource(value = {"aa:asdf:false", "Aaaa:asdf:false", "A aa:asdf:false", "a!aa:asdf:false",
        "aaaa:asdf:true"}, delimiter = ':')
    public void memberJoinDTOValidateTest(String userAlias, String token, boolean isOk) {
        //given
        MemberJoinDto memberJoinDto = new MemberJoinDto(userAlias, token, null);

        //when
        Set<ConstraintViolation<MemberJoinDto>> validatedResult = validator.validate(memberJoinDto);

        //then
        assertThat(validatedResult.size() == 0).isEqualTo(isOk);
    }
}