package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDto {
    @NotBlank(message = "don't leave title empty")
    private String title;

    @NotBlank(message = "don't leave description empty")
    private String description;

    @NotBlank(message = "don't leave url empty")
    private String url;
}
