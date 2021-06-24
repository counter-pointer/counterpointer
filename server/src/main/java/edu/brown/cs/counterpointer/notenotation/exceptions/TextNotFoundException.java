package edu.brown.cs.counterpointer.notenotation.exceptions;

/**
 * Thrown if the an abcjs representation is invalid.
 */
public class TextNotFoundException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  /**
   * Throws the exception.
   */
  public TextNotFoundException() {
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   */
  public TextNotFoundException(String message) {
    super(message);
  }

  /**
   * Throws the exception.
   * @param cause Parent error causing the exception
   */
  public TextNotFoundException(Throwable cause) {
    super(cause);
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   * @param throwable Parent error causing the exception
   */
  public TextNotFoundException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
