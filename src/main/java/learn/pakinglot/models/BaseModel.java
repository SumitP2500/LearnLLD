package learn.pakinglot.models;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * BaseModel
 */
@Getter 
@Setter 
public class BaseModel {
    private Long id;
    private Date createdAt;
    private Date updatedAt;
}
