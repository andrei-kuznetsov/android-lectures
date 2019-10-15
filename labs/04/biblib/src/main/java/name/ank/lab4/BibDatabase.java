package name.ank.lab4;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jbibtex.BibTeXDatabase;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.BibTeXParser;
import org.jbibtex.ParseException;

public class BibDatabase {

  private final List<BibTeXEntry> entries;
  private BibConfig cfg;


  public BibDatabase(Reader reader) throws IOException {
    this(reader, new BibConfig());
  }

  /**
   * Constructor.
   * @param reader database source. Caller should take care anc close the reader after the constructor exits.
   * @throws IOException when underlying parser fails to read the database
   */
  public BibDatabase(Reader reader, BibConfig cfg) throws IOException {
    this.cfg = cfg;
    try {
      BibTeXParser bibtexParser = new BibTeXParser();
      BibTeXDatabase database = bibtexParser.parse(reader);
      entries = new ArrayList<>(database.getEntries().values());
      if (cfg.shuffle) {
        Collections.shuffle(entries);
      }
    } catch (ParseException e) {
      throw new IOException(e);
    }
  }

  /**
   * Gets database entry
   * @param idx index in the database. Note that {@link BibConfig#shuffle} flag may affect the order in different instances.
   * @return database entry at the requested index.
   * @throws IndexOutOfBoundsException when requested index does not exist.
   */
  public BibEntry getEntry(int idx) throws IndexOutOfBoundsException {
    return new BibEntry(entries.get(idx), cfg);
  }

  public BibConfig getCfg() {
    return cfg;
  }
}
