package name.ank.lab4;

import java.util.concurrent.atomic.AtomicLong;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Value;

public class BibEntry {

  private static final AtomicLong latestOrder = new AtomicLong();
  private final long myOrder;
  private BibTeXEntry delegate;
  private BibConfig cfg;

  BibEntry(BibTeXEntry delegate, BibConfig cfg) {
    myOrder = latestOrder.incrementAndGet();
    this.delegate = delegate;
    this.cfg = cfg;
  }

  private void assertDelegateIsValid() {
    if (cfg.strict) {
      long latestOrder = BibEntry.latestOrder.get();
      if (myOrder + cfg.maxValid <= latestOrder) {
        throw new IllegalStateException(
            String.format("This object has already been invalidated. myOrder=%d, latestOrder=%d", myOrder, latestOrder)
        );
      }
    }
  }

  /**
   * Gets bibtex type of this entry
   * @return bibtex type
   */
  public Types getType() {
    assertDelegateIsValid();
    Types ret = Types.fromBtValue(delegate.getType());
    assert ret != null : "All the types must have mappings";
    return ret;
  }

  /**
   * Gets field by its key (if any)
   * @param key field to return
   * @return field value or {@code null} if the key does not exist
   */
  public String getField(Keys key) {
    assertDelegateIsValid();
    Value field = delegate.getField(key.getBtKey());
    if (field != null) {
      return field.toUserString();
    } else {
      return null;
    }
  }
}
