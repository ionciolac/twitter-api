package com.ionciolac.common.exception

class ObjectAlreadyExistException extends RuntimeException {

    ObjectAlreadyExistException() {
    }

    ObjectAlreadyExistException(String message) {
        super(message)
    }
}
