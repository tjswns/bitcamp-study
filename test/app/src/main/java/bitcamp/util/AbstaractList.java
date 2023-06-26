package bitcamp.util;

public abstract class AbstaractList<E> implements List<E> {

  protected int size;

  @Override
  public int size() {
    return this.size;
  }

  protected boolean isValid(int index) {
    return index >= 0 && index < this.size;
  }


  @Override
  public Iterator<E> iterator() {
    return new ListIterator<>(this);
  }
}
