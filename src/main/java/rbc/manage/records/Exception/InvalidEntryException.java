package rbc.manage.records.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidEntryException extends RuntimeException {
    public InvalidEntryException(String exception) {
        super(exception);
    }
}
