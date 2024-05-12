package com.ionciolac.adapter.inputs.rest.customizedexception

import com.ionciolac.common.exception.ObjectAlreadyExistException
import com.ionciolac.common.exception.ObjectNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

import static java.time.Instant.now
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_FOUND

@ControllerAdvice
@RestController
class CustomizedExceptionAdapter extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectAlreadyExistException.class)
    ResponseEntity<ExceptionResponse> handleObjectAlreadyExistException(ObjectAlreadyExistException ex) {
        return new ResponseEntity<>(ExceptionResponse.builder().date(now()).message(ex.getMessage()).build(), BAD_REQUEST)
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    ResponseEntity<ExceptionResponse> handleObjectNotFoundException(ObjectNotFoundException ex) {
        return new ResponseEntity<>(ExceptionResponse.builder().date(now()).message(ex.getMessage()).build(), NOT_FOUND);
    }
}
