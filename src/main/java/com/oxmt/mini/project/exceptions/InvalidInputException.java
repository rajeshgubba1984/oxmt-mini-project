package com.oxmt.mini.project.exceptions;

/**
 * Please add your description here.
 *
 */
public class InvalidInputException extends RuntimeException
{
    private static final long serialVersionUID = 4087861913811471355L;

    public InvalidInputException(String message) {
        super(message);
    }
}
