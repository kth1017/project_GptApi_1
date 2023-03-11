package fadet.P3_GptApi.domain.forTrans;

import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.web.dto.requestDto.TranslateEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.TranslateKtoERequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TranslateTest {

    @Autowired
    ApiKey apiKey;

    @Test
    void KtoEdao생성() {
        //given
        TranslateKtoERequestDto dto = new TranslateKtoERequestDto("안녕하세요");

        //when
        Translate newDao = dto.toDomain();

        //then
        assertThat(newDao.getSentence()).isEqualTo("안녕하세요");
    }

    @Test
    void incoding테스트_타입1() {
        //given
        TranslateKtoERequestDto dto = new TranslateKtoERequestDto("안녕하세요.");
        Translate newDomain = dto.toDomain();

        //when
        String body = newDomain.incoding(apiKey);

        //then
        assertThat(body).contains("Hello.");
    }

    @Test
    void incoding테스트_타입2() {
        //given
        TranslateEtoKRequestDto dto = new TranslateEtoKRequestDto("Hello.");
        Translate newDomain = dto.toDomain();

        //when
        String body = newDomain.incoding(apiKey);

        //then
        assertThat(body).contains("안녕하세요.");
    }
}