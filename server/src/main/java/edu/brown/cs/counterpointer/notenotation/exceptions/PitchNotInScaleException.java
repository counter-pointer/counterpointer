package edu.brown.cs.counterpointer.notenotation.exceptions;

/**
 * Thrown when an attempt is made to determine a pitch's value in a scale the pitch
 * does not belong to.
 */
public class PitchNotInScaleException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  /**
   * Throws the exception.
   */
  public PitchNotInScaleException() {
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   */
  public PitchNotInScaleException(String message) {
    super(message);
  }

  /**
   * Throws the exception.
   * @param cause Parent error causing the exception
   */
  public PitchNotInScaleException(Throwable cause) {
    super(cause);
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   * @param throwable Parent error causing the exception
   */
  public PitchNotInScaleException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
