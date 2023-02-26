package fadet.P3_GptApi.web.dto.requestDto;

import fadet.P3_GptApi.domain.forTrans.ForTrans;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class ForTransKtoERequestDto {

    @NotBlank(message = "빈 값이나 공백만 존재해서는 안됩니다.")
    @Size(max = 100, message = "이 항목은 100글자까지만 허용합니다.")
    private String sentence;

    public ForTransKtoERequestDto(String sentence) {
        this.sentence = sentence;
    }

    public ForTrans toDomain(){
        return new ForTrans(sentence, 1);
    }
}
