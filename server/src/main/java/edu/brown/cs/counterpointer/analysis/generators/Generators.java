package edu.brown.cs.counterpointer.analysis.generators;

import edu.brown.cs.counterpointer.analysis.checkers.Checkers;

public final class Generators {
  /**
   * Disable constructor.
   */
  private Generators() {
    throw new AssertionError("Instantiating static class");
  }

  public static final Generator species1 = new Generator(Checkers.species1);
  public static final Generator species2 = new Generator(Checkers.species2);
  public static final Generator species3 = new Generator(Checkers.species3);
  public static final Generator species4 = new Generator(Checkers.species4);
}
