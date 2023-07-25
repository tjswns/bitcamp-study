package bitcamp.myapp.vo;

public class Acc {

  public static int accNo = 1;

  private int no;
  private String style;
  private String select;
  private String size;
  private String ohter;
  private String password;
  private int viewCount;
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


  public String getSelect() {
    return select;
  }

  public void setSelect(String select) {
    this.select = select;
  }



  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getSize() {
    return size;
  }

  public void setSize(String size) {
    this.size = size;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public long getCreatedDate() {
    return createdDate;
  }


  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }


}
