package com.hibernate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public class ObjNotFoundException extends Exception {

        private static final long serialVersionUID = -3128681006635769411L;


        public ObjNotFoundException() {
            super();
        }

        public ObjNotFoundException(String message) {
            super(message);
        }

        public ObjNotFoundException(Throwable cause) {
            super(cause);
        }

        public ObjNotFoundException(String message, Throwable cause) {
            super(message, cause);
        }

        public ObjNotFoundException(String message, Throwable cause, boolean enableSuppression,
                                              boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }

    }

