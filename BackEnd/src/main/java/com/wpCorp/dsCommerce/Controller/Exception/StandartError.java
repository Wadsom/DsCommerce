package com.wpCorp.dsCommerce.Controller.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StandartError {
    private Instant timeStamp;
    private String path;
    private Integer status;
    private String message;
    private String err;


}
