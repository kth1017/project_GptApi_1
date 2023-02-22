package fadet.P3_GptApi.domain.recomQ;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;



@ExtendWith(SpringExtension.class)
class RecomQTest {
    @Test
    void 질문저장(){
        //given
        RecomQ recomQ = new RecomQ();
        //when
        String[] largeCate = recomQ.getLData();

        //then
        Assertions.assertThat(largeCate[0]).isEqualTo("java");
    }

}