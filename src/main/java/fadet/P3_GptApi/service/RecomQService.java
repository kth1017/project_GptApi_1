package fadet.P3_GptApi.service;
import fadet.P3_GptApi.domain.recomQ.RecomQRepository;
import fadet.P3_GptApi.web.dto.requestDto.RQ2RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RecomQService {

    private final RecomQRepository recomQRepository;

    // 기능 추가시 변경되어 임시로 dto를 사용하지 않고 작성
    public String[] getRecomQ() {
        return recomQRepository.getList();
    }

    public void setSmallCateArr(RQ2RequestDto dto){
        recomQRepository.saveCategory(dto.getCategory());
        
    }
    // 기능 추가시 변경되어 임시로 dto를 사용하지 않고 작성
    public String[] getRecomQ2(){
        return recomQRepository.getSList();
    }

}
