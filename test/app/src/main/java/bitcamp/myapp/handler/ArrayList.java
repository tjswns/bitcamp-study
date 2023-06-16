package bitcamp.myapp.handler;

public class ArrayList {
  private static final int DEFAULT_SIZE = 100;
  private Object[] list = new Object[DEFAULT_SIZE];
  private int length;

  public void add(Object obj) {
    if (this.length == list.length) {
      increase();
    }
    this.list[this.length++] = obj;
  }

  private void increase() {
    // 기존 배열 보다 50% 큰 배열을 새로 만든다.
    Object[] arr = new Object[list.length + (list.length >> 1)];

    // 기존 배열의 값을 새 배열로 복사한다.
    for (int i = 0; i < list.length; i++) {
      arr[i] = list[i];
    }

    // boards 레퍼런스가 새 배열을 가리키도록 한다.
    list = arr;

    // System.out.println("배열을 늘렸음!");
  }

  public Object[] list() {

    Object[] arr = new Object[this.length];

    for (int i = 0; i < this.length; i++) {
      arr[i] = this.list[i];
    }
    // 새 배열을 리턴한다.
    return arr;
  }

  public Object get(Object obj) {
    for (int i = 0; i < this.length; i++) {
      Object item = this.list[i];
      if (item.equals(obj)) {
        return item;
      }
    }
    return null;
  }

  public boolean delete(Object obj) {
    int deletedIndex = indexOf(obj);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.list[i] = this.list[i + 1];
    }

    this.list[--length] = null;
    return true;
  }

  private int indexOf(Object obj) {
    for (int i = 0; i < this.length; i++) {
      Object item = this.list[i];
      if (item.equals(obj)) {
        return i;
      }
    }
    return -1;
  }
}

