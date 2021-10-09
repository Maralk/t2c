package com.proof.t2c.config.exceptions;

import com.proof.t2c.domain.exceptions.T2cException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

@ControllerAdvice
public class HttpExceptionHandler extends ExceptionHandlerExceptionResolver {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionEntity> handleException(Exception exception) {
        if (exception.getClass() == java.util.concurrent.CompletionException.class) {
            exception = (Exception) exception.getCause();
        }
        if (exception.getClass() == HttpMediaTypeNotSupportedException.class) {
            return this.handleHttpMediaTypeNotSupportedException((HttpMediaTypeNotSupportedException) exception);
        }
        if (exception.getClass() == HttpMessageNotReadableException.class) {
            return this.handleHttpMessageNotReadableException((HttpMessageNotReadableException) exception);
        }
        if (exception.getClass() == java.sql.SQLException.class) {
            return this.handleSqlException((java.sql.SQLException) exception);
        }
        if (exception.getClass() == org.springframework.dao.DataIntegrityViolationException.class) {
            return this.handleConstraintViolationException((org.springframework.dao.DataIntegrityViolationException) exception);
        }
        if (exception.getClass() == org.springframework.orm.jpa.JpaSystemException.class) {
            return this.handleJpaSystemException((org.springframework.orm.jpa.JpaSystemException) exception);
        }
        if (exception.getClass() == org.apache.catalina.connector.ClientAbortException.class) {
            return this.handleClientAbortException((org.apache.catalina.connector.ClientAbortException) exception);
        }
        if (exception.getClass() == T2cException.class) {
            return this.handleCoreT2cException((T2cException) exception);
        }
        if (exception.getClass() == org.springframework.web.context.request.async.AsyncRequestTimeoutException.class) {
            return this.handleAsyncRequestTimeoutException((org.springframework.web.context.request.async.AsyncRequestTimeoutException) exception);
        }
        this.printException(exception);
        ExceptionEntity exceptionEntity = new ExceptionEntity("Se ha producido un error no esperado. Si persiste contacte con el administrador.");
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final ResponseEntity<ExceptionEntity> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException httpMediaTypeNotSupportedException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity("Los parámetros recibidos no pertenecen a un formato admitido.");
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final ResponseEntity<ExceptionEntity> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity("Los parámetros recibidos no son correctos.");
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final ResponseEntity<ExceptionEntity> handleConstraintViolationException(org.springframework.dao.DataIntegrityViolationException constraintViolationException) {
        String sqlMessage = constraintViolationException.getCause().getCause().getMessage();
        Boolean isDuplicatedValue = sqlMessage.contains("Duplicate entry");
        if (isDuplicatedValue) {
            String duplicateValue = sqlMessage.split("Duplicate entry '")[1].split("'")[0];
            ExceptionEntity exceptionEntity = new ExceptionEntity("El valor " + duplicateValue + " ya existe en el sistema.");
            return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ExceptionEntity exceptionEntity = new ExceptionEntity("Error de base de datos: " + sqlMessage);
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final ResponseEntity<ExceptionEntity> handleConstraintViolationException(org.hibernate.exception.ConstraintViolationException constraintViolationException) {
        String sqlMessage = constraintViolationException.getSQLException().getMessage();
        String duplicateValue = sqlMessage.split("Duplicate entry '")[1].split("'")[0];
        ExceptionEntity exceptionEntity = new ExceptionEntity("El valor " + duplicateValue + " ya existe en el sistema.");
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final ResponseEntity<ExceptionEntity> handleJpaSystemException(org.springframework.orm.jpa.JpaSystemException jpaSystemException) {
        String sqlMessage = jpaSystemException.getCause().getCause().getMessage();
        ExceptionEntity exceptionEntity = new ExceptionEntity(sqlMessage);
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final ResponseEntity<ExceptionEntity> handleSqlException(java.sql.SQLException sqlException) {
        String sqlMessage = sqlException.getMessage();
        ExceptionEntity exceptionEntity = new ExceptionEntity(sqlMessage);
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final ResponseEntity<ExceptionEntity> handleClientAbortException(org.apache.catalina.connector.ClientAbortException clientAbortException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity("El servicio no está disponible, por favor, inténtelo de nuevo en unos minutos");
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final ResponseEntity<ExceptionEntity> handleCoreT2cException(com.proof.t2c.domain.exceptions.T2cException t2cException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity(t2cException.getMessage());
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final ResponseEntity<ExceptionEntity> handleAsyncRequestTimeoutException(org.springframework.web.context.request.async.AsyncRequestTimeoutException asyncRequestTimeoutException) {
        ExceptionEntity exceptionEntity = new ExceptionEntity("Excesivo tiempo de respuesta en el servidor");
        return new ResponseEntity<>(exceptionEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private final void printException(Exception exception) {
        String message = exception.getMessage();
        String cause = exception.getClass().getName();
        String filename = exception.getStackTrace()[0].getFileName();
        String method = exception.getStackTrace()[0].getMethodName();
        String line = Integer.toString(exception.getStackTrace()[0].getLineNumber());
        System.out.println("**************************************************************************************************************");
        System.out.println("Mensaje: " + message);
        System.out.println("Causa: " + cause);
        System.out.println("Archivo: " + filename);
        System.out.println("Método: " + method);
        System.out.println("Línea: " + line);
        System.out.println("**************************************************************************************************************");
    }

}
