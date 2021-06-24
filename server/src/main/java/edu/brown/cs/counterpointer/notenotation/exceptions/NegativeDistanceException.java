package edu.brown.cs.counterpointer.notenotation.exceptions;

public class NegativeDistanceException extends RuntimeException {
  public static final long serialVersionUID = 1L;
  
  /**
   * Throws the exception.
   */
  public NegativeDistanceException() {
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   */
  public NegativeDistanceException(String message) {
    super(message);
  }

  /**
   * Throws the exception.
   * @param cause Parent error causing the exception
   */
  public NegativeDistanceException(Throwable cause) {
    super(cause);
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   * @param throwable Parent error causing the exception
   */
  public NegativeDistanceException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
