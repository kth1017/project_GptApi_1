package fadet.P3_GptApi.domain.forAsk;

import fadet.P3_GptApi.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Repository
public class AskGptRepositoryImpl implements AskGptRepository {

    private static Map<Long, AskGpt> store = new ConcurrentHashMap<>();
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
        AskGpt askGpt = new AskGpt(question, apiKey);
        askGpt.setId(sequence++);
        store.put(askGpt.getId(), askGpt);
        return askGpt.getAnswer();
    }
}
