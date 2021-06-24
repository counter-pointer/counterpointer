package edu.brown.cs.counterpointer.notenotation.exceptions;

/**
 * An exception to be thrown when sharping or flatting a note is no longer possible.
 */
public class AccidentalOutOfBoundsException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  /**
   * Throws the exception.
   */
  public AccidentalOutOfBoundsException() {
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   */
  public AccidentalOutOfBoundsException(String message) {
    super(message);
  }

  /**
   * Throws the exception.
   * @param cause Parent error causing the exception
   */
  public AccidentalOutOfBoundsException(Throwable cause) {
    super(cause);
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   * @param throwable Parent error causing the exception
   */
  public AccidentalOutOfBoundsException(String message, Throwable throwable) {
    super(message, throwable);
  }
}

