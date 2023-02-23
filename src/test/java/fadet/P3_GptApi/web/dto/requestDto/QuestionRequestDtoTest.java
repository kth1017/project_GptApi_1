package fadet.P3_GptApi.web.dto.requestDto;

import fadet.P3_GptApi.domain.entity.question.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuestionRequestDtoTest {
    @Test
    void toEntity성공() {
        //given
        QuestionRequestDto dto = new QuestionRequestDto("안녕하세요.");
        //when
        //then
        Assertions.assertThat(dto.toEntity()).isInstanceOf(Question.class);
    }
}