package bookmyconsultation.appointmentservice.exception;

import bookmyconsultation.appointmentservice.dto.ErrorDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class MyExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("ERR_INVALID_INPUT");
        errorDTO.setErrorMessage("Invalid input. Parameter name: ");
        return new ResponseEntity(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PendingPaymentException.class)
    public final ResponseEntity<ErrorDTO> handleInvalidBooking(PendingPaymentException e, WebRequest res){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("ERR_PAYMENT_PENDING");
        errorDTO.setErrorMessage("Prescription cannot be issued since the payment status is pending");
        return new ResponseEntity(errorDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResouceNotFound.class)
    public final ResponseEntity<ErrorDTO> handleInvalidBooking(ResouceNotFound e, WebRequest res){
        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setErrorCode("ERR_RESOURCE_NOT_FOUND");
        errorDTO.setErrorMessage("Requested  resource is not available");
        return new ResponseEntity(errorDTO, HttpStatus.NOT_FOUND);
    }

}