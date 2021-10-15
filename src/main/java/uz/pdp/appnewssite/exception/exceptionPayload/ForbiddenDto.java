package uz.pdp.appnewssite.exception.exceptionPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
public class ForbiddenDto {
    private final String message;
    private final String type;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamps;
}
