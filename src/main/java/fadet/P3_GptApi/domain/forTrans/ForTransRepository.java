package fadet.P3_GptApi.domain.forTrans;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ForTransRepository {

    private static Map<Long, ForTrans> store = new HashMap<>();
    private static long sequence = 1L;

    public ForTrans save(ForTrans newOne){
        newOne.setId(sequence++);
        store.put(newOne.getId(), newOne);
        return newOne;
    }

    public ForTrans findLastOne() {
        Long size = (long)store.size();
        return store.get(size);
    }

}
