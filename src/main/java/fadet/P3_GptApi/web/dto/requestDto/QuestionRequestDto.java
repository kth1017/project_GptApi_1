package fadet.P3_GptApi.web.dto.requestDto;

import fadet.P3_GptApi.domain.entity.question.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuestionRequestDto {
    String questionContent;

    public QuestionRequestDto(String questionContent) {
        this.questionContent = questionContent;
    }

    public Question toEntity(){
        return new Question(questionContent);
    }
}
