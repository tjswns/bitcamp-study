package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Styling;

public class StylingList {
  private static final int DEFAULT_SIZE = 3;

  private Styling[] stylings = new Styling[DEFAULT_SIZE];
  private int length = 0;

  public void add(Styling styling) {
    if (this.length == stylings.length) {
      increase();
    }
    this.stylings[this.length++] = styling;

  }

  private void increase() {
    Styling[] arr = new Styling[stylings.length + (stylings.length >> 1)];

    for (int i = 0; i < stylings.length; i++) {
      arr[i] = stylings[i];
    }

    stylings = arr;

  }

  public Styling[] list() {
    Styling[] arr = new Styling[this.length];
    for (int i = 0; i < this.length; i++) {
      arr[i] = stylings[i];
    }
    return arr;
  }

  public Styling get(int no) {
    for (int i = 0; i < this.length; i++) {
      Styling styling = this.stylings[i];
      if (styling.getNo() == no) {
        return styling;
      }
    }
    return null;
  }

  public boolean delete(int no) {
    int deletedIndex = indexOf(no);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.stylings[i] = this.stylings[i + 1];
    }
    this.stylings[--this.length] = null;
    return true;
  }

  private int indexOf(int stylingNo) {
    for (int i = 0; i < this.length; i++) {
      Styling m = this.stylings[i];
      if (m.getNo() == stylingNo) {
        return i;
      }
    }
    return -1;
  }
}
