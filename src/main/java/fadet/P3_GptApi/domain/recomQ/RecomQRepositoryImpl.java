package fadet.P3_GptApi.domain.recomQ;

import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Getter
@Repository
public class RecomQRepositoryImpl implements RecomQRepository{

    private static Map<Long, String> categoryStore = new ConcurrentHashMap<>();
    private static long sequence = 1L;

    @Override
    public void clear() {
        if(!categoryStore.isEmpty()){
            categoryStore.clear();
            sequence = 1L;
        }
    }
    @Override
    public void saveCategory(String c){
        categoryStore.put(sequence++, c);
    }
    @Override
    public String getSavedCategory() {
        Long size = (long)categoryStore.size();
        return categoryStore.get(size);
    }
    @Override
    public String[] getList(){
        RecomQ recomQ = new RecomQ();
        return recomQ.getLData();

    }
    @Override
    public String[] getSList(){
        Long size = (long)categoryStore.size();
        RecomQ recomQ = new RecomQ(categoryStore.get(size));
        return recomQ.getSList();
    }

}
