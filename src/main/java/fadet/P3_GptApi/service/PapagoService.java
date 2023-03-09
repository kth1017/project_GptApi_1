package fadet.P3_GptApi.service;
import fadet.P3_GptApi.ApiKey;
import fadet.P3_GptApi.domain.forTrans.ForTrans;
import fadet.P3_GptApi.domain.forTrans.ForTransRepository;
import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PapagoService {
    private final ForTransRepository repository;


    public String transKtoE(ForTransKtoERequestDto dto) {
        ForTrans newOneKE = dto.toDomain();
        repository.saveKtoE(newOneKE);
        return repository.getTranslatedText();
    }

    public String transEtoK(ForTransEtoKRequestDto dto) {
        ForTrans newOneEK = dto.toDomain();
        repository.saveEtoK(newOneEK);
        return repository.getTranslatedText();
    }
}
