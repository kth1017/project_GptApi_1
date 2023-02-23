package fadet.P3_GptApi.service;
import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.domain.forTrans.ForTrans;
import fadet.P3_GptApi.domain.forTrans.ForTransRepository;
import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import fadet.P3_GptApi.web.dto.responseDto.ForTransKtoEResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PapagoService {

    private final ApiKey apiKey;
    private final ForTransRepository repository;


    public String saveKtoE(ForTransKtoERequestDto dto) {
        repository.saveKtoE(dto);
        return repository.findLastOne().getSentence();
    }

    public String transKtoE() {
        ForTrans lastOne = repository.findLastOne();
        return new ForTransKtoEResponseDto(lastOne.incoding(apiKey)).getTranslatedText();
    }



    public String transEntoKo(ForTransEtoKRequestDto dto) {
        ForTrans forTransDao = dto.toDao(dto.getSentence());
        return forTransDao.incoding(apiKey);
    }


}
