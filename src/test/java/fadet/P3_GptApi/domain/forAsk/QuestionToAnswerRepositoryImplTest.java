package fadet.P3_GptApi.domain.forAsk;

import fadet.P3_GptApi.domain.entity.question.Question;
import fadet.P3_GptApi.domain.entity.question.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AskGptRepositoryTest {
    @Autowired
    AskGptRepository QtoARepository;

    @Autowired
    QuestionRepository QRepository;

    @Test
    void 질문과답변등록후답변give() {
        //given
        QRepository.save(new Question("what is java?"));
        QRepository.save(new Question("what is js?"));
        //when
        String answer = QtoARepository.getAnswer(QRepository.findLastOne().get(0).getQuestionContent());
        //then
        assertThat(answer).contains("JavaScript");
    }
}