package uz.pdp.appnewssite.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import uz.pdp.appnewssite.exception.exceptionPayload.ForbiddenDto;
import uz.pdp.appnewssite.exception.exceptionPayload.ResourceNotFoundDto;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {ForbiddenException.class})
    public ResponseEntity<Object> handleForbiddenException(ForbiddenException forbiddenException) {
        ForbiddenDto forbiddenDto = new ForbiddenDto(
                forbiddenException.getMessage(),
                forbiddenException.getType(),
                HttpStatus.METHOD_NOT_ALLOWED,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(forbiddenDto, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) {

        ResourceNotFoundDto resourceNotFoundDto = new ResourceNotFoundDto(
                resourceNotFoundException.getResourceName(),
                resourceNotFoundException.getResourceField(),
                resourceNotFoundException.getObject(),
                resourceNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );

        return new ResponseEntity<>(resourceNotFoundDto, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
