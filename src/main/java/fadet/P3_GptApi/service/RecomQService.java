package fadet.P3_GptApi.service;
import fadet.P3_GptApi.domain.recomQ.RecomQRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecomQService {

    private final RecomQRepository recomQRepository;


    @Transactional
    public String[] getRecomQ() {
        return recomQRepository.getList();
    }

}
