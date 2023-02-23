package fadet.P3_GptApi.domain.questionToAnswer;

import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.domain.forTrans.ForTrans;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Repository
public class QuestionToAnswerRepositoryImpl implements QuestionToAnswerRepository{

    private static Map<Long, QuestionToAnswer> store = new ConcurrentHashMap<>();
    private static long sequence = 1L;

    private final ApiKey apiKey;

    @Override
    public void clear() {
        if(!store.isEmpty()){
            store.clear();
            sequence = 1L;
        }
    }

    @Override
    public String getAnswer(String question) {
        QuestionToAnswer questionToAnswer = new QuestionToAnswer(question, apiKey);
        questionToAnswer.setId(sequence++);
        store.put(questionToAnswer.getId(), questionToAnswer);
        return questionToAnswer.getAnswer();
    }
}
