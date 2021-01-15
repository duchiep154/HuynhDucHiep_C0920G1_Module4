package com.codegym.validation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ReturnCodeWrapper {
    @NotNull
    @Size(min = 5, max = 5, message = "Code must have 5 digits")
    private String returnCode;
}
