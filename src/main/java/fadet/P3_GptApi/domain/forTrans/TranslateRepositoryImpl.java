package fadet.P3_GptApi.domain.forTrans;

import fadet.P3_GptApi.ApiKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Repository
public class TranslateRepositoryImpl implements TranslateRepository {

    private static Map<Long, Translate> store = new ConcurrentHashMap<>();
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
    public Translate saveKtoE(Translate newOne){
        newOne.setId(sequence++);
        store.put(newOne.getId(), newOne);
        return newOne;
    }

    @Override
    public Translate saveEtoK(Translate newOne) {
        newOne.setId(sequence++);
        store.put(newOne.getId(), newOne);
        return newOne;
    }

    @Override
    public Translate findLastOne() {
        Long size = (long)store.size();
        return store.get(size);
    }

    @Override
    public String getTranslatedText() {
        Long size = (long)store.size();
        return store.get(size).incoding(apiKey);
    }

}
