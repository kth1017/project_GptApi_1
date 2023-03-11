package fadet.P3_GptApi.web.dto.requestDto;

import fadet.P3_GptApi.domain.forTrans.Translate;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class TranslateEtoKRequestDto {
    @NotBlank(message = "빈 값이나 공백만 존재해서는 안됩니다.")
    @Size(max = 30000, message = "이 항목은 30,000글자까지만 허용합니다.")
    private String sentence;

    public TranslateEtoKRequestDto(String sentence) {
        this.sentence = sentence;
    }

    public Translate toDomain(){
        return new Translate(sentence, 2);
    }

}
