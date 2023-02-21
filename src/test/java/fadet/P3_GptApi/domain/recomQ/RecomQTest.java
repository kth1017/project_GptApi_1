package fadet.P3_GptApi.domain.recomQ;

import fadet.P3_GptApi.domain.answer.Answer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class RecomQTest {
    @Test
    void 질문저장(){
        //given
        RecomQ recomQ = new RecomQ();
        //when
        List<String> largeCate = recomQ.getLargeCategory();

        //then
        Assertions.assertThat(largeCate.get(0)).isEqualTo("java");
    }

}