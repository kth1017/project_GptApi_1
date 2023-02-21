package fadet.P3_GptApi.domain.recomQ;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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