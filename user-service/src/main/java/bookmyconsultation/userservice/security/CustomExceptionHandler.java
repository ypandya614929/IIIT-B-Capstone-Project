package bookmyconsultation.userservice.security;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({AccessDeniedException.class})
    public final ResponseEntity<TokenErrorDTO> handleAccessDeniedException(AccessDeniedException exception, WebRequest request) {
        ServletWebRequest servletRequest = ((ServletWebRequest)request);
        TokenErrorDTO tokenErrorDTO = new TokenErrorDTO();
        tokenErrorDTO.setTimestamp(OffsetDateTime.now().toString());
        tokenErrorDTO.setStatus("403");
        tokenErrorDTO.setError(HttpStatus.FORBIDDEN.getReasonPhrase());
        tokenErrorDTO.setPath(servletRequest.getRequest().getRequestURI().toString());
        return new ResponseEntity(tokenErrorDTO, HttpStatus.FORBIDDEN);
    }

}
