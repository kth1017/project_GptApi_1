package fadet.P3_GptApi;

import fadet.P3_GptApi.domain.forTrans.ForTrans;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Getter
@Setter
public class TestRepository {
    private static List<String> store = new ArrayList<>();

    public void save(String s) {
        store.add(s);
    }

    public String get(){
        return store.get(0);
    }
}
