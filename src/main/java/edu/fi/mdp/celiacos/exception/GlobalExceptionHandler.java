package edu.fi.mdp.celiacos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AlimentoNotFoundException.class)
    public ResponseEntity<?> handleAlimentoNotFoundException() {
        return ResponseEntity.badRequest().body("El alimento solicitado no existe");
    }
    @ExceptionHandler(EmailNotAvailableException.class)
    public ResponseEntity<?> handleEmailNotAvailableException() {
        return ResponseEntity.badRequest().body("El email ya se encuentra en uso");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> handleUnauthorizedException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email y contraseña invalidos");
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<?> handleIOException(IOException e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
