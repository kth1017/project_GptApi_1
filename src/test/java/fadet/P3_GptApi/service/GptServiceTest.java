package fadet.P3_GptApi.service;

import fadet.P3_GptApi.domain.entity.question.QuestionRepository;
import fadet.P3_GptApi.web.dto.requestDto.QuestionRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest
class GptServiceTest {
    @Autowired
    GptService service;
    @Autowired
    QuestionRepository repository;

    @Test
    void 질문저장() {
        //given
        QuestionRequestDto dto = new QuestionRequestDto("what is java?");
        //when
        service.saveQuestion(dto);
        //then
        // findBy는 null방지를 위해 optional이용
        assertThat(repository.findLastOne()).isEqualTo("what is java?");
    }

    @Test
    void 답변() {
        //given
        QuestionRequestDto dto = new QuestionRequestDto("what is java?");
        service.saveQuestion(dto);
        //when
        String answerContent = service.getAnswerContent();
        //then
        // findBy는 null방지를 위해 optional이용
        assertThat(answerContent).contains("Java is a");
    }



}