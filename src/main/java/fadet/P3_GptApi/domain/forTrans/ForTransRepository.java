package fadet.P3_GptApi.domain.forTrans;

import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;

public interface ForTransRepository {

    public void clear();
    public ForTrans saveKtoE(ForTransKtoERequestDto KtoEDto);
    public ForTrans saveEtoK(ForTransEtoKRequestDto EtoKDto);
    public ForTrans findLastOne();
}
