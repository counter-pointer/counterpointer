package edu.brown.cs.counterpointer.notenotation.exceptions;

/**
 * Thrown when a beat being accessed is not in the melody.
 */
public class BeatOutOfBoundsException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  /**
   * Throws the exception.
   */
  public BeatOutOfBoundsException() {
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   */
  public BeatOutOfBoundsException(String message) {
    super(message);
  }

  /**
   * Throws the exception.
   * @param cause Parent error causing the exception
   */
  public BeatOutOfBoundsException(Throwable cause) {
    super(cause);
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   * @param throwable Parent error causing the exception
   */
  public BeatOutOfBoundsException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
