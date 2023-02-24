package fadet.P3_GptApi.domain.question;

import fadet.P3_GptApi.domain.entity.question.Question;
import fadet.P3_GptApi.domain.entity.question.QuestionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class QuestionRepositoryTest {
    @Autowired
    QuestionRepository repository;

    @Test
    void jqpl테스트_마지막저장된질문불러오기() {
        //h2 db를 사용해서 list를 불러오므로 변경 필요
        //given
        repository.save(new Question("질문1"));
        repository.save(new Question("질문2"));

        //when

        //then
        assertThat(repository.findLastOne().get(0).getQuestionContent()).isEqualTo("질문2");

    }
}
