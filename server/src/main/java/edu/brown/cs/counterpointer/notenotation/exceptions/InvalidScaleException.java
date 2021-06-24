package edu.brown.cs.counterpointer.notenotation.exceptions;

/**
 * Thrown if somehow a pitch's ScaleValue is not found in the enum of ScaleValues.
 */
public class InvalidScaleException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  /**
   * Throws the exception.
   */
  public InvalidScaleException() {
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   */
  public InvalidScaleException(String message) {
    super(message);
  }

  /**
   * Throws the exception.
   * @param cause Parent error causing the exception
   */
  public InvalidScaleException(Throwable cause) {
    super(cause);
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   * @param throwable Parent error causing the exception
   */
  public InvalidScaleException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
