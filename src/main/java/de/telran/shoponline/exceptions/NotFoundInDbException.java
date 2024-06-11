package de.telran.shoponline.exceptions;

public class NotFoundInDbException extends RuntimeException{
    public NotFoundInDbException(String message) {
        super(message);
    }
}
