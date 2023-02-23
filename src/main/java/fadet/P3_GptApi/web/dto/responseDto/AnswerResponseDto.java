package fadet.P3_GptApi.web.dto.responseDto;
import fadet.P3_GptApi.domain.entity.answer.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AnswerResponseDto {
    String AnswerContent;

    public AnswerResponseDto(Answer answer) {
        this.AnswerContent = answer.getAnswerContent();
    }

}
