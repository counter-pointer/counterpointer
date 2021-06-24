package edu.brown.cs.counterpointer.notenotation.exceptions;

/**
 * An exception thrown when the end beat of subMelody is greater than the begin beat.
 */
public class EndBeatGreatThanBeginBeatException extends RuntimeException {
  public static final long serialVersionUID = 1L;

  /**
   * Throws the exception.
   */
  public EndBeatGreatThanBeginBeatException() {
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   */
  public EndBeatGreatThanBeginBeatException(String message) {
    super(message);
  }

  /**
   * Throws the exception.
   * @param cause Parent error causing the exception
   */
  public EndBeatGreatThanBeginBeatException(Throwable cause) {
    super(cause);
  }

  /**
   * Throws the exception.
   * @param message Error message to show
   * @param throwable Parent error causing the exception
   */
  public EndBeatGreatThanBeginBeatException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
