package qust.travelassistant.exception;

public class UserDonotExistException extends TravelAssistantException {

    public UserDonotExistException(String message) {
        super(message);
    }

    public UserDonotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
