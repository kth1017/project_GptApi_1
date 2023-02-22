package fadet.P3_GptApi.domain.recomQ;


import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class RecomQRepositoryTest {
    @Autowired RecomQRepository repository;

    @Test
    void 추천질문배열반환(){
        //given
        //when
        String[] list = repository.getList();
        //then
        assertThat(list[0]).isEqualTo("java");
    }

}