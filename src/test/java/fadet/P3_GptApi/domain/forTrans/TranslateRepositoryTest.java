package fadet.P3_GptApi.domain.forTrans;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class TranslateRepositoryTest {

    @Autowired
    TranslateRepository repository;

    @AfterEach
    void clear(){
        repository.clear();
    }

    @Test
    void 번역할문장저장_1_KtoE(){
        //given
        //when
        repository.saveKtoE(new Translate("안녕하세요.", 1));
        //then
        assertThat(repository.findLastOne().getSentence()).isEqualTo("안녕하세요.");
    }
    @Test
    void 번역할문장저장_2_EtoK(){
        //given
        //when
        repository.saveEtoK(new Translate("Hello.", 2));
        //then
        assertThat(repository.findLastOne().getSentence()).isEqualTo("Hello.");
    }

    @Test
    void 저장된문장불러오기_1_KtoE(){
        //given
        repository.saveKtoE(new Translate("안녕하세요.", 1));
        //when
        Translate lastOne = repository.findLastOne();
        //then
        assertThat(lastOne.getSentence()).isEqualTo("안녕하세요.");
    }

    @Test
    void 저장된문장불러오기_2_EtoK(){
        //given
        repository.saveEtoK(new Translate("Hello.", 2));
        //when
        Translate lastOne = repository.findLastOne();
        //then
        assertThat(lastOne.getSentence()).isEqualTo("Hello.");
    }

    @Test
    void 번역된문장불러오기_1_KtoE(){
        //given
        repository.saveKtoE(new Translate("안녕하세요.", 1));
        //when
        String text = repository.getTranslatedText();
        //then
        assertThat(text).contains("Hello");
    }

    @Test
    void 번역된문장불러오기(){
        //given
        repository.saveEtoK(new Translate("Hello.", 2));
        //when
        String text = repository.getTranslatedText();
        //then
        assertThat(text).contains("안녕하세요");
    }
}