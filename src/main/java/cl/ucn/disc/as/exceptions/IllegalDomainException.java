package cl.ucn.disc.as.exceptions;

/**
 * The domain violation exception class
 *
 * @Author Edgardo Ortiz González
 */

public class IllegalDomainException extends RuntimeException{
    /**
     * The constructor
     * @param message: message to log
     */

    public IllegalDomainException(String message){
        super(message);
    }
}