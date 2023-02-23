package fadet.P3_GptApi.domain.questionToAnswer;

import fadet.P3_GptApi.ApiKey;

public interface QuestionToAnswerRepository {

    public void clear();
    public String getAnswer(String question);

}
