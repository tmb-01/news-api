package uz.pdp.appnewssite.exception.exceptionPayload;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Data
public class ResourceNotFoundDto {
    private final String resourceName;
    private final String resourceField;
    private final Object object;
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
