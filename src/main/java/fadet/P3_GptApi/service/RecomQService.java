package fadet.P3_GptApi.service;
import fadet.P3_GptApi.domain.recomQ.RecomQRepository;
import fadet.P3_GptApi.web.dto.RequestRQ2Dto;
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

    @Transactional
    public void setSmallCateArr(RequestRQ2Dto dto){
        recomQRepository.saveCategory(dto.getCategory());
        
    }
    
    @Transactional
    public String[] getRecomQ2(){
        return recomQRepository.getSList();
    }

}
