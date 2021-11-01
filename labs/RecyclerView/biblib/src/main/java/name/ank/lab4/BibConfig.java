package name.ank.lab4;

public class BibConfig {
  /**
   * Should the database be shuffled or not (useful for debugging and testing).
   * May be modified at any moment. Only affects newly created {@link BibDatabase} instances.
   */
  public boolean shuffle = true;

  /**
   * Strict mode enabled flag.
   * May be modified at any time. Affects all the existing instances of {@link BibEntry}.
   */
  public boolean strict = false;

  /**
   * How many instances of {@link BibEntry} should remain valid. Has effect only if {@link #strict} set to {@code true}.
   */
  public long maxValid = 20;
}
