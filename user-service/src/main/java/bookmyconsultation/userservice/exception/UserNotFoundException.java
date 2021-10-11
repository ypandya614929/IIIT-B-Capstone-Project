package bookmyconsultation.userservice.exception;

// custom exception class to handle/throw
// retrieve user api error
public class UserNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserNotFoundException(){
        super();
    }

}
