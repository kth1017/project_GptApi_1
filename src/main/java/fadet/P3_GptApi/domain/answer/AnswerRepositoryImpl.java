package fadet.P3_GptApi.domain.answer;

import java.util.ArrayList;
import java.util.List;

public class AnswerRepositoryImpl {

    private static List<Answer> store = new ArrayList<>();

    public Answer save(Answer newOne){
        store.add(newOne);
        return newOne;
    }
}
