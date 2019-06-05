package qust.travelassistant.exception;

public class WrongPasswordException extends TravelAssistantException {

    public WrongPasswordException(String message) {
        super(message);
    }

    public WrongPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
