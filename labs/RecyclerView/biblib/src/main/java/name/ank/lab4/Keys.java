package name.ank.lab4;

import org.jbibtex.BibTeXEntry;
import org.jbibtex.Key;

public enum Keys {
  ADDRESS(BibTeXEntry.KEY_ADDRESS),
  ANNOTE(BibTeXEntry.KEY_ANNOTE),
  AUTHOR(BibTeXEntry.KEY_AUTHOR),
  BOOKTITLE(BibTeXEntry.KEY_BOOKTITLE),
  CHAPTER(BibTeXEntry.KEY_CHAPTER),
  CROSSREF(BibTeXEntry.KEY_CROSSREF),
  DOI(BibTeXEntry.KEY_DOI),
  EDITION(BibTeXEntry.KEY_EDITION),
  EDITOR(BibTeXEntry.KEY_EDITOR),
  EPRINT(BibTeXEntry.KEY_EPRINT),
  HOWPUBLISHED(BibTeXEntry.KEY_HOWPUBLISHED),
  INSTITUTION(BibTeXEntry.KEY_INSTITUTION),
  JOURNAL(BibTeXEntry.KEY_JOURNAL),
  KEY(BibTeXEntry.KEY_KEY),
  MONTH(BibTeXEntry.KEY_MONTH),
  NOTE(BibTeXEntry.KEY_NOTE),
  NUMBER(BibTeXEntry.KEY_NUMBER),
  ORGANIZATION(BibTeXEntry.KEY_ORGANIZATION),
  PAGES(BibTeXEntry.KEY_PAGES),
  PUBLISHER(BibTeXEntry.KEY_PUBLISHER),
  SCHOOL(BibTeXEntry.KEY_SCHOOL),
  SERIES(BibTeXEntry.KEY_SERIES),
  TITLE(BibTeXEntry.KEY_TITLE),
  TYPE(BibTeXEntry.KEY_TYPE),
  URL(BibTeXEntry.KEY_URL),
  VOLUME(BibTeXEntry.KEY_VOLUME),
  YEAR(BibTeXEntry.KEY_YEAR);

  private final Key btKey;

  Keys(Key key) {
    this.btKey = key;
  }

  Key getBtKey() {
    return btKey;
  }
}
