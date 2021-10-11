package bookmyconsultation.doctorservice.exception;

// custom exception class to handle/throw
// retrieve user api error
public class DoctorNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DoctorNotFoundException(){
        super();
    }

}