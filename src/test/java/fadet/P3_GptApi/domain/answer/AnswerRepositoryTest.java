package fadet.P3_GptApi.domain.answer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class AnswerRepositoryTest {

    @Autowired
    AnswerRepository answerRepository;

    @Test
    void 질문저장(){
        //given
        Answer newOne = new Answer("답변1");

        //when
        Answer savedOne = answerRepository.save(newOne);

        //then
        Assertions.assertThat(savedOne.getAnswerContent()).isEqualTo("답변1");
    }
}