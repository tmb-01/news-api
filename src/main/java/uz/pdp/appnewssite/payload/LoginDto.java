package uz.pdp.appnewssite.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginDto {

    @NotNull(message = "don't leave username empty")
    private String username;

    @NotNull(message = "don't leave password empty")
    private String password;

}
