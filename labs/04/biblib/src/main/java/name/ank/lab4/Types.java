package name.ank.lab4;

import java.util.HashMap;
import java.util.Map;
import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

public enum Types {
  ARTICLE(BibTeXEntry.TYPE_ARTICLE),
  BOOK(BibTeXEntry.TYPE_BOOK),
  BOOKLET(BibTeXEntry.TYPE_BOOKLET),
  CONFERENCE(BibTeXEntry.TYPE_CONFERENCE),
  INBOOK(BibTeXEntry.TYPE_INBOOK),
  INCOLLECTION(BibTeXEntry.TYPE_INCOLLECTION),
  INPROCEEDINGS(BibTeXEntry.TYPE_INPROCEEDINGS),
  MANUAL(BibTeXEntry.TYPE_MANUAL),
  MASTERSTHESIS(BibTeXEntry.TYPE_MASTERSTHESIS),
  MISC(BibTeXEntry.TYPE_MISC),
  PHDTHESIS(BibTeXEntry.TYPE_PHDTHESIS),
  PROCEEDINGS(BibTeXEntry.TYPE_PROCEEDINGS),
  TECHREPORT(BibTeXEntry.TYPE_TECHREPORT),
  UNPUBLISHED(BibTeXEntry.TYPE_UNPUBLISHED);

  private static final Map<Key, Types> typeMapping = new HashMap<>();

  static {
    for (Types value : Types.values()) {
      typeMapping.put(value.btType, value);
    }
  }

  private final Key btType;

  Types(Key typeArticle) {
    btType = typeArticle;
  }

  static Types fromBtValue(Key typeArticle) {
    return typeMapping.get(typeArticle);
  }

  Key getBtType() {
    return btType;
  }
}
