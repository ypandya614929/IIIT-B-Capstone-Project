package bookmyconsultation.userservice.exception;

import bookmyconsultation.userservice.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

// custom exception handler for POST and GET requests
// for create user and retrieve user
@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("ERR_INVALID_INPUT");
        errorDTO.setErrorMessage("Invalid input. Parameter name: ");
        List<String> errorFields = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            if (!errorFields.contains(error.getDefaultMessage())){
                errorFields.add(error.getDefaultMessage());
            }
        }
        errorDTO.setErrorFields(errorFields);
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ErrorDTO> handleInvalidBooking(UserNotFoundException e, WebRequest res){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("ERR_RESOURCE_NOT_FOUND");
        errorDTO.setErrorMessage("Requested  resource is not available");
        errorDTO.setErrorFields(null);
        return new ResponseEntity(errorDTO, HttpStatus.NOT_FOUND);
    }

}