package qust.travelassistant.exception;

public class PasswordNotSameException extends TravelAssistantException {

    public PasswordNotSameException(String message) {
        super(message);
    }

    public PasswordNotSameException(String message, Throwable cause) {
        super(message, cause);
    }
}
