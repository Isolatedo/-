package com.entity;

import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * contact
 * @author 
 */
@Data
public class Contact implements Serializable {
    private Integer cnumber;

    @Length(min = 2,max = 10)
    private String cname;
    @Length(min = 0,max = 30)
    private String caddress;
    @Length(min = 6,max = 12)
    private String cphone;

    private static final long serialVersionUID = 1L;
}