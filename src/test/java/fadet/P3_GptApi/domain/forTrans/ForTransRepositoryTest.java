package fadet.P3_GptApi.domain.forTrans;

import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ForTransRepositoryTest  {

    @Autowired
    ForTransRepository repository;

    @AfterEach
    void clear(){
        repository.clear();
    }

    @Test
    void 번역할문장저장_1_KtoE(){
        //given
        //when
        repository.saveKtoE(new ForTransKtoERequestDto("안녕하세요."));
        //then
        assertThat(repository.findLastOne().getSentence()).isEqualTo("안녕하세요.");
        System.out.println(repository.findLastOne().getId());
    }
    @Test
    void 번역할문장저장_2_EtoK(){
        //given
        //when
        repository.saveEtoK(new ForTransEtoKRequestDto("Hello."));
        //then
        assertThat(repository.findLastOne().getSentence()).isEqualTo("Hello.");
        System.out.println(repository.findLastOne().getId());
    }

    @Test
    void 저장된문장불러오기(){
        //given
        repository.saveEtoK(new ForTransEtoKRequestDto("Hello."));
        //when
        ForTrans lastOne = repository.findLastOne();
        //then
        assertThat(lastOne.getSentence()).isEqualTo("Hello.");
        System.out.println(repository.findLastOne().getId());
    }
}