package fadet.P3_GptApi.web.dto.requestDto;

import fadet.P3_GptApi.domain.forTrans.ForTrans;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ForTransKtoERequestDtoTest {
    @Test
    void Question도메인화() {
        //given
        ForTransKtoERequestDto dto = new ForTransKtoERequestDto("안녕하세요.");
        //when
        //then
        assertThat(dto.toDomain()).isInstanceOf(ForTrans.class);
    }

}