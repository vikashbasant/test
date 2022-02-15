package decimal.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private String status;
    private String statusCode;
    private String message;
    private Object response;
    private Object errorResponse;


    public ResponseDTO(String status, String message, Object errorResponse){
        this.status = status;
        this.message = message;
        this.errorResponse = errorResponse;
    }

    public ResponseDTO(String status, String response){
        this.status = status;
        this.response = response;
    }

    public ResponseDTO(String status, String statusCode, String message, Object errorResponse){
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.response = errorResponse;
    }


}
