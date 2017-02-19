package com.epam.tat.menu.util.parser.exception;

/**
 * @author Uladzislau Seuruk.
 */
public class ParserException extends Exception {
    public ParserException () {
        super();
    }

    public ParserException (Exception e) {
        super(e);
    }

    public ParserException (String message) {
        super(message);
    }

    public ParserException (String message, Exception e) {
        super(message, e);
    }
}