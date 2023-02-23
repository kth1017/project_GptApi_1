package fadet.P3_GptApi.domain.forTrans;

import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Repository
public class ForTransRepositoryImpl implements ForTransRepository{

    private static Map<Long, ForTrans> store = new ConcurrentHashMap<>();
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
    public ForTrans saveKtoE(ForTransKtoERequestDto KtoEDto){
        ForTrans newOne = KtoEDto.toDomain();
        newOne.setId(sequence++);
        store.put(newOne.getId(), newOne);
        return newOne;
    }

    @Override
    public ForTrans saveEtoK(ForTransEtoKRequestDto EtoKDto) {
        ForTrans newOne = EtoKDto.toDomain();
        newOne.setId(sequence++);
        store.put(newOne.getId(), newOne);
        return newOne;
    }

    @Override
    public ForTrans findLastOne() {
        Long size = (long)store.size();
        return store.get(size);
    }

    @Override
    public String getTranslatedText() {
        Long size = (long)store.size();
        return store.get(size).incoding(apiKey);
    }

}
