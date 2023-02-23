package fadet.P3_GptApi.service;

import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.domain.forTrans.ForTransRepositoryImpl;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PapagoServiceTest {

    @Autowired
    ForTransRepositoryImpl repository;
    @Autowired PapagoService papagoService;

    @AfterEach
    void clear(){
        repository.clear();
    }

    @Test
    void KoToEn문장저장(){
        //given
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto("안녕하세요.");

        //when
        String savedContent = papagoService.saveKtoE(dto);

        //then
        assertThat(savedContent).isEqualTo("안녕하세요.");
    }

    @Test
    void KoToEn번역요청(){
        //given
        repository.saveKtoE(new ForTransKtoERequestDto("안녕하세요."));
        String expectedMessage = "{\"message\":{\"result\":{\"srcLangType\":\"ko\",\"tarLangType\":\"en\",\"translatedText\":\"Hello.\",\"engineType\":\"PRETRANS\",\"pivot\":null,\"dict\":null,\"tarDict\":null,\"modelVer\":\"Unknown\"},\"@type\":\"response\",\"@service\":\"naverservice.nmt.proxy\",\"@version\":\"1.0.0\"}}";

        //when
        String transedContent = papagoService.transKtoE();

        //then
        assertThat(transedContent).isEqualTo(expectedMessage);
    }

}