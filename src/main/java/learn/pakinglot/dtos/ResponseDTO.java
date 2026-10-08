package learn.pakinglot.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@AllArgsConstructor 
public class ResponseDTO {
    String message;
    ResponseType responseType;
}
