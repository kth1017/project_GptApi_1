package fadet.P3_GptApi.domain.questionToAnswer;

import fadet.P3_GptApi.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class QuestionToAnswerRepositoryImpl implements QuestionToAnswerRepository{

    private final ApiKey apiKey;
    @Override
    public String getAnswer(String question) {
        return new QuestionToAnswer(question, apiKey).getAnswer();
    }
}
