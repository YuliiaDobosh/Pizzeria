package lpnu.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
    @ExceptionHandler(value = ServiceException.class )
    public ResponseEntity<Object> handleServiceException(final ServiceException ex, final WebRequest request) {
        return ResponseEntity.status(HttpStatus.valueOf(ex.getCode()))
                .body(new ServiceExceptionDTO(ex));
    }

    @ExceptionHandler(value = IllegalArgumentException.class )
    public ResponseEntity<Object> handleServiceException(final IllegalArgumentException ex, final WebRequest request) {
        return ResponseEntity.status(HttpStatus.valueOf(HttpStatus.BAD_REQUEST.value()))
                .body(new ServiceExceptionDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), null));
    }



    @ExceptionHandler(value = MethodArgumentNotValidException.class )
    public ResponseEntity<Object> handleServiceException(final MethodArgumentNotValidException ex) {

        final String messages = ex.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .reduce((s1, s2) -> s1 + "; " + s2)
                .orElse("We have an issue with creating error message");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ServiceExceptionDTO(HttpStatus.BAD_REQUEST.value(), messages, null));
    }
}