package fadet.P3_GptApi.service;
import fadet.P3_GptApi.domain.recomQ.RecomQRepository;
import fadet.P3_GptApi.web.dto.requestDto.RQ2RequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
// 기능 추가시 변경되어 임시로 dto를 사용하지 않고 작성
@Service
@RequiredArgsConstructor
@Transactional
public class RecomQService {

    private final RecomQRepository recomQRepository;

    
    public String[] getRecomQ() {
        return recomQRepository.getList();
    }

    public String[] getRecomQ2(RQ2RequestDto dto){
        recomQRepository.saveCategory(dto.getCategory());
        return recomQRepository.getSList();
    }

}
