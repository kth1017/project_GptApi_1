package fadet.P3_GptApi.domain.forTrans;

import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ForTransRepositoryImpl implements ForTransRepository{

    private static Map<Long, ForTrans> store = new HashMap<>();
    private static long sequence = 1L;

    @Override
    public void clear() {
        if(!store.isEmpty()){
            store.clear();
            sequence = 1L;
        }
    }

    @Override
    public ForTrans saveKtoE(ForTransKtoERequestDto KtoEDto){
        ForTrans newOne = new ForTrans(KtoEDto);
        newOne.setId(sequence++);
        store.put(newOne.getId(), newOne);
        return newOne;
    }

    @Override
    public ForTrans saveEtoK(ForTransEtoKRequestDto EtoKDto) {
        ForTrans newOne = new ForTrans(EtoKDto);
        newOne.setId(sequence++);
        store.put(newOne.getId(), newOne);
        return newOne;
    }

    @Override
    public ForTrans findLastOne() {
        Long size = (long)store.size();
        return store.get(size);
    }

}
