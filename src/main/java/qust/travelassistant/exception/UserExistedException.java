package qust.travelassistant.exception;

public class UserExistedException extends TravelAssistantException {

    public UserExistedException(String message) {
        super(message);
    }

    public UserExistedException(String message, Throwable cause) {
        super(message, cause);
    }
}
