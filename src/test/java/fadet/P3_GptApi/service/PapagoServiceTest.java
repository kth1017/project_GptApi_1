package fadet.P3_GptApi.service;

import fadet.P3_GptApi.domain.forTrans.TranslateRepositoryImpl;
import fadet.P3_GptApi.web.dto.requestDto.TranslateKtoERequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PapagoServiceTest {

    @Autowired
    TranslateRepositoryImpl repository;
    @Autowired PapagoService papagoService;

    @AfterEach
    void clear(){
        repository.clear();
    }
    @Test
    void KoToEn번역요청(){
        //given
        TranslateKtoERequestDto dto = new TranslateKtoERequestDto("안녕하세요.");

        //when
        String transedContent = papagoService.transKtoE(dto);

        //then
        assertThat(transedContent).contains("Hello.");
    }

}