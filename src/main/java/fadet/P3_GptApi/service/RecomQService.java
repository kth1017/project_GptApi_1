package fadet.P3_GptApi.service;
import fadet.P3_GptApi.domain.recomQ.RecomQRepository;
import fadet.P3_GptApi.domain.recomQ.RecomQRepositoryImpl;
import fadet.P3_GptApi.web.dto.RequestRQ2Dto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecomQService {

    private final RecomQRepository recomQRepository;

    // 기능 추가시 변경되어 임시로 dto를 사용하지 않고 제작
    public String[] getRecomQ() {
        return recomQRepository.getList();
    }

    public void setSmallCateArr(RequestRQ2Dto dto){
        recomQRepository.saveCategory(dto.getCategory());
        
    }

    public String[] getRecomQ2(){
        return recomQRepository.getSList();
    }

}
