package fadet.P3_GptApi.domain.forTrans;

import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ForTransTest {

    @Autowired
    ApiKey apiKey;

    @Test
    void KtoEdao생성() {
        //given
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto("안녕하세요");

        //when
        ForTrans newDao = new ForTrans(dto);

        //then
        assertThat(newDao.getSentence()).isEqualTo("안녕하세요");
    }

    @Test
    void incoding테스트_타입1() {
        //given
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto("안녕하세요.");
        ForTrans newDao = dto.toDao(dto.getSentence());
        String expectedMessage = "{\"message\":{\"result\":{\"srcLangType\":\"ko\",\"tarLangType\":\"en\",\"translatedText\":\"Hello.\",\"engineType\":\"PRETRANS\",\"pivot\":null,\"dict\":null,\"tarDict\":null,\"modelVer\":\"Unknown\"},\"@type\":\"response\",\"@service\":\"naverservice.nmt.proxy\",\"@version\":\"1.0.0\"}}";


        //when
        String body = newDao.incoding(apiKey);

        //then
        assertThat(body).isEqualTo(expectedMessage);
    }

    @Test
    void incoding테스트_타입2() {
        //given
        ForTransEtoKRequestDto dto = new ForTransEtoKRequestDto("Hello.");
        ForTrans newDao = dto.toDao(dto.getSentence());
        String expectedMessage = "{\"message\":{\"result\":{\"srcLangType\":\"en\",\"tarLangType\":\"ko\",\"translatedText\":\"안녕하세요.\",\"engineType\":\"PRETRANS\",\"pivot\":null,\"dict\":null,\"tarDict\":null,\"modelVer\":\"Unknown\"},\"@type\":\"response\",\"@service\":\"naverservice.nmt.proxy\",\"@version\":\"1.0.0\"}}";

        //when
        String body = newDao.incoding(apiKey);

        //then
        assertThat(body).isEqualTo(expectedMessage);
    }
}