package bitcamp.myapp.vo;

public class Acc {

  private static int accNo = 1;

  private int no;
  private String Style;
  private String select;
  private String Size;
  private String ohter;
  private long createdDate;


  public Acc() {
    this.no = accNo++;
    this.createdDate = System.currentTimeMillis();
  }

  public Acc(int no) {
    this.no = no;
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Acc acc = (Acc) obj;

    if (this.getNo() != acc.getNo()) {
      return false;
    }
    return true;
  }

  public int getNo() {
    return no;
  }


  public void setNo(int no) {
    this.no = no;
  }

  public String getOhter() {
    return ohter;
  }

  public void setOhter(String ohter) {
    this.ohter = ohter;
  }

  public String getStyle() {
    return Style;
  }


  public void setStyle(String style) {
    Style = style;
  }



  public String getSelect() {
    return select;
  }

  public void setSelect(String select) {
    this.select = select;
  }

  public String getSize() {
    return Size;
  }

  public void setSize(String size) {
    Size = size;
  }

  public long getCreatedDate() {
    return createdDate;
  }


  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }


}
