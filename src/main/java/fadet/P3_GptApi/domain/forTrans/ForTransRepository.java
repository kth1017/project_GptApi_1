package fadet.P3_GptApi.domain.forTrans;

import fadet.P3_GptApi.web.dto.requestDto.ForTransEtoKRequestDto;
import fadet.P3_GptApi.web.dto.requestDto.ForTransKtoERequestDto;

public interface ForTransRepository {

    public void clear();
    public ForTrans saveKtoE(ForTrans newOne);
    public ForTrans saveEtoK(ForTrans newOne);
    public ForTrans findLastOne();
    public String getTranslatedText();
}
