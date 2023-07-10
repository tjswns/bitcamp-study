package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Acc;

public class AccList {
  private static final int DEFAULT_SIZE = 3;
  private Acc[] accs = new Acc[DEFAULT_SIZE];
  private int length = 0;

  public void add(Acc acc) {
    if (this.length == accs.length) {
      increase();
    }

    this.accs[this.length++] = acc;
  }

  private void increase() {
    // 기존 배열 보다 50% 큰 배열을 새로 만든다.
    Acc[] arr = new Acc[accs.length + (accs.length >> 1)];

    // 기존 배열의 값을 새 배열로 복사한다.
    for (int i = 0; i < accs.length; i++) {
      arr[i] = accs[i];
    }

    // boards 레퍼런스가 새 배열을 가리키도록 한다.
    accs = arr;

    // System.out.println("배열 확장: " + accs.length);
  }

  public Acc[] list() {
    Acc[] arr = new Acc[this.length];
    for (int i = 0; i < this.length; i++) {
      arr[i] = accs[i];
    }
    return arr;
  }

  public Acc get(int no) {
    for (int i = 0; i < this.length; i++) {
      Acc acc = this.accs[i];
      if (acc.getNo() == no) {
        return acc;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = this.indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.accs[i] = this.accs[i + 1];
    }
    this.accs[--this.length] = null;
    return true;
  }

  private int indexOf(int boardNo) {
    for (int i = 0; i < this.length; i++) {
      Acc acc = this.accs[i];
      if (acc.getNo() == boardNo) {
        return i;
      }
    }
    return -1;
  }
}
