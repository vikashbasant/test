package decimal.test.execption;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GenralException extends Exception{
    private String statusCode;
    private String message;
    private transient Object errorMessages;

    public GenralException(String errorCode, String message, String errorMessages){
        this.statusCode = errorCode;
        this.message = message;
        this.errorMessages = errorMessages;
    }


    public GenralException(String errorCode, String message){
        this.statusCode = errorCode;
        this.message = message;
    }





}
