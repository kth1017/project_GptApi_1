package fadet.P3_GptApi.service;

import fadet.P3_GptApi.domain.entity.question.QuestionRepository;
import fadet.P3_GptApi.web.dto.requestDto.QuestionRequestDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;


@Transactional
@SpringBootTest
class GptServiceTest {
    @Autowired
    GptService service;
    @Autowired
    QuestionRepository repository;

    @Test
    void 답변_성공() {
        //given
        QuestionRequestDto dto = new QuestionRequestDto("what is java?");
        //when
        String answerContent = service.getAnswerContent(dto);
        //then
        assertThat(answerContent).contains("Java is a");
    }

    @Test
    void 답변_실패() {
        //given
        QuestionRequestDto dto = new QuestionRequestDto("what is java?");
        //when
        String answerContent = service.getAnswerContent(dto);
        //then
        assertThat(answerContent).contains("Java is a");
    }






}