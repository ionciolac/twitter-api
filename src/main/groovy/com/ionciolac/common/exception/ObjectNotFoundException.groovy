package com.ionciolac.common.exception

class ObjectNotFoundException extends RuntimeException {

    ObjectNotFoundException() {
    }

    ObjectNotFoundException(String message) {
        super(message)
    }
}
