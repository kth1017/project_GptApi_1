package fadet.P3_GptApi.web.dto.responseDto;

import fadet.P3_GptApi.domain.entity.answer.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class AnswerResponseDto {
    private String answerContent;

    public AnswerResponseDto(Answer entity) {
        this.answerContent = entity.getAnswerContent();
    }


}
