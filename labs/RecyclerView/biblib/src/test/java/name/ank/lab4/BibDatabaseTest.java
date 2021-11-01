package name.ank.lab4;


import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class BibDatabaseTest {

  private BibDatabase openDatabase(String s) throws IOException {
    try (InputStreamReader reader = new InputStreamReader(getClass().getResourceAsStream(s))) {
      return new BibDatabase(reader);
    }
  }

  @Test
  public void getFirstEntry() throws IOException {
    BibDatabase database = openDatabase("/references.bib");
    BibEntry first = database.getEntry(0);
    Assert.assertEquals(Types.ARTICLE, first.getType());
    Assert.assertEquals("The semantic web", first.getField(Keys.TITLE));
    Assert.assertNull("Field 'chapter' does not exist", first.getField(Keys.CHAPTER));
  }

  @Test
  public void normalModeDoesNotThrowException() throws IOException {
    BibDatabase database = openDatabase("/mixed.bib");
    BibConfig cfg = database.getCfg();
    cfg.strict = false;

    BibEntry first = database.getEntry(0);
    for (int i = 0; i < cfg.maxValid + 1; i++) {
      BibEntry unused = database.getEntry(0);
      assertNotNull("Should not throw any exception @" + i, first.getType());
    }
  }

  @Test
  public void canReadAllItemsFromMixed() throws IOException {
    BibDatabase database = openDatabase("/mixed.bib");

    for (int i = 0; i < database.size(); i++) {
      BibEntry entry = database.getEntry(i);
      assertNotNull(entry.getType());
    }
  }

  @Test
  public void strictModeThrowsException() {
    fail("Not implemented");
  }

  @Test
  public void shuffleFlag() {
    fail("Not implemented");
  }
}
