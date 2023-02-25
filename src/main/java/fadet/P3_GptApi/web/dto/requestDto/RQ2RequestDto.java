package fadet.P3_GptApi.web.dto.requestDto;

import fadet.customAnnotation.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class RQ2RequestDto {
    @Category
    private String category;

    public RQ2RequestDto(String category) {
        this.category = category;
    }


    
}
