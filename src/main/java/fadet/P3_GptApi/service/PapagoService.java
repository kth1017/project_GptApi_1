package fadet.P3_GptApi.service;
import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.domain.forTrans.ForTrans;
import fadet.P3_GptApi.domain.forTrans.ForTransRepository;
import fadet.P3_GptApi.web.dto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.ForTransKtoERequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PapagoService {

    private final ApiKey apiKey;
    private final ForTransRepository repository;


    @Transactional
    public String saveKtoE(ForTransKtoERequestDto dto) {
        ForTrans forTransDao = dto.toDao(dto.getSentence());
        repository.save(forTransDao);
        return repository.findLastOne().getSentence();
    }

    @Transactional
    public String transKtoE() {
        ForTrans lastOne = repository.findLastOne();
        return lastOne.incoding(apiKey);
    }



    @Transactional
    public String transEntoKo(ForTransEtoKRequestDto dto) {
        ForTrans forTransDao = dto.toDao(dto.getSentence());
        return forTransDao.incoding(apiKey);
    }


}
