package learn.pakinglot.models;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter 
public class Operator extends BaseModel {
    String name;
    String employeeId;
    Gate gate;
}
