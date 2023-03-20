package academy.mindswap.rentacar.aspects;


import academy.mindswap.rentacar.exception.IdDoesNotExist;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Component
@ControllerAdvice
public class DivisionExceptionalHandler {
    private static final Logger logger = LoggerFactory.getLogger(DivisionExceptionalHandler.class);

    @ExceptionHandler(value = {IdDoesNotExist.class})
    public ResponseEntity<String> handleIdDoesNotExist(Exception exception) {
        logger.error("Known Exception: " + exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleGenericException(Exception exception){
        logger.error("Unknown Exception: " + exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred.");
    }




    }



