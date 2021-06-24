package edu.brown.cs.counterpointer.notenotation.interval;

/**
 * All possible base intervals (distance or semitone distance lesser
 * than that of a perfect octave).
 */
public final class Intervals {
  /**
   * Disable constructor.
   */
  private Intervals() {
    throw new AssertionError("Instantiating static class");
  }
  public static final Interval PER1 = new Interval(0, 0);
  public static final Interval AUG1 = new Interval(0, 1);
  public static final Interval DIM2 = new Interval(1, 1);
  public static final Interval MIN2 = new Interval(1, 1);
  public static final Interval MAJ2 = new Interval(1, 2);
  public static final Interval AUG2 = new Interval(1, 3);
  public static final Interval DIM3 = new Interval(2, 2);
  public static final Interval MIN3 = new Interval(2, 3);
  public static final Interval MAJ3 = new Interval(2, 4);
  public static final Interval AUG3 = new Interval(2, 5);
  public static final Interval DIM4 = new Interval(3, 4);
  public static final Interval PER4 = new Interval(3, 5);
  public static final Interval AUG4 = new Interval(3, 6);
  public static final Interval DIM5 = new Interval(4, 6);
  public static final Interval PER5 = new Interval(4, 7);
  public static final Interval AUG5 = new Interval(4, 8);
  public static final Interval DIM6 = new Interval(5, 7);
  public static final Interval MIN6 = new Interval(5, 8);
  public static final Interval MAJ6 = new Interval(5, 9);
  public static final Interval AUG6 = new Interval(5, 10);
  public static final Interval DIM7 = new Interval(6, 9);
  public static final Interval MIN7 = new Interval(6, 10);
  public static final Interval MAJ7 = new Interval(6, 11);
  public static final Interval AUG7 = new Interval(6, 12);
  public static final Interval DIM8 = new Interval(7, 11);
}
