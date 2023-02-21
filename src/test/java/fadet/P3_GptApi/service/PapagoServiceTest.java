package fadet.P3_GptApi.service;

import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.domain.forTrans.ForTrans;
import fadet.P3_GptApi.domain.forTrans.ForTransRepository;
import fadet.P3_GptApi.web.dto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.ForTransKtoERequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PapagoServiceTest {

    @Autowired ApiKey apiKey;
    @Autowired
    ForTransRepository repository;
    @Autowired PapagoService papagoService;

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
        ForTrans savedOne = repository.save(new ForTrans("안녕하세요.", 1));
        String expectedMessage = "{\"message\":{\"result\":{\"srcLangType\":\"ko\",\"tarLangType\":\"en\",\"translatedText\":\"Hello.\",\"engineType\":\"PRETRANS\",\"pivot\":null,\"dict\":null,\"tarDict\":null,\"modelVer\":\"Unknown\"},\"@type\":\"response\",\"@service\":\"naverservice.nmt.proxy\",\"@version\":\"1.0.0\"}}";


        //when
        String transedContent = papagoService.transKtoE();

        //then
        assertThat(transedContent).isEqualTo(expectedMessage);


    }

    @Test
    void EnToKo번역요청(){
        //given
        ForTransEtoKRequestDto dto = new ForTransEtoKRequestDto("Hello.");
        String expectedMessage = "{\"message\":{\"result\":{\"srcLangType\":\"en\",\"tarLangType\":\"ko\",\"translatedText\":\"안녕하세요.\",\"engineType\":\"PRETRANS\",\"pivot\":null,\"dict\":null,\"tarDict\":null,\"modelVer\":\"Unknown\"},\"@type\":\"response\",\"@service\":\"naverservice.nmt.proxy\",\"@version\":\"1.0.0\"}}";


        //when
        String transedContent = papagoService.transEntoKo(dto);

        //then
        assertThat(transedContent).isEqualTo(expectedMessage);


    }



}