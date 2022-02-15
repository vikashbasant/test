package decimal.test.controller;

import decimal.test.dto.ResponseDTO;
import decimal.test.execption.GenralException;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class ExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity runtimeException(RuntimeException ex){
        String message = ex.getMessage();

        ResponseDTO responseDTO = new ResponseDTO("TEST_500", message);
        return new ResponseEntity(responseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = GenralException.class)
    public ResponseEntity genralException(GenralException gExcp){
        String message = gExcp.getMessage();
        String code = gExcp.getStatusCode();
        Object errorMessage = gExcp.getErrorMessages();

        ResponseDTO responseDTO = new ResponseDTO("FAILURE", code, message, errorMessage);
        return new ResponseEntity(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
