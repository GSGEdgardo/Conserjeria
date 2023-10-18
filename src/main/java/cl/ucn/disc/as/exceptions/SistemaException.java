package cl.ucn.disc.as.exceptions;

import javax.persistence.PersistenceException;

/**
 * The sistema exception class
 * @Author Edgardo Ortiz Gonzalez
 */

public class SistemaException extends RuntimeException {

    /**
     *Creates an exception
     *@param message message to log
     *@param ex persistence exception associated
     */

    public SistemaException(String message, PersistenceException ex) {
        super(message, ex);
    }

    public SistemaException(String message){
        super(message);
    }
}