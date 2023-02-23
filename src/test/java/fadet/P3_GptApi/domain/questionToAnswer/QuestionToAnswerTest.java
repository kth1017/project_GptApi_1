package fadet.P3_GptApi.domain.questionToAnswer;

import fadet.P3_GptApi.ApiKey;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionToAnswerTest {

    @Autowired
    ApiKey apiKey;

    @Test
    void 인코딩성공후필드값세팅() {
        //given
        //when
        QuestionToAnswer newOne = new QuestionToAnswer("what is java?", apiKey);
        //then
        assertThat(newOne.getQuestion()).isEqualTo("what is java?");
        assertThat(newOne.getAnswer()).contains("Java");

    }
}