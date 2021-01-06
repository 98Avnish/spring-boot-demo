package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
@Slf4j
public class Utils {

    public void logBindingError(BindingResult bindingResult) {
        bindingResult.getFieldErrors().forEach(fieldError ->
                log.error("Validation Error :{} {}",
                        fieldError.getField(),
                        fieldError.getDefaultMessage()));
    }
}
