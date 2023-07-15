package com.learning.commonlbs.validator;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class OrderValidator {
    public static void validateNotNUll(final Object object, final Object... missingAttribute) throws Exception {
        if(object == null){
            log.error(Arrays.toString(missingAttribute) + " :- Not Found.");
            // TODO : Throw validation Exception here instead of general exception
//            throw new ValidationException(be)
            throw new Exception();
        }
    }
}
