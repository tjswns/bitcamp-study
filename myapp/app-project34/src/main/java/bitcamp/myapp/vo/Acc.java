package bitcamp.myapp.vo;

import java.io.Serializable;

public class Acc implements Serializable, CsvObject {
  private static final long serialVersionUID = 1L;
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

  public static Acc fromCsv(String csv) {

    String[] values = csv.split(",");

    Acc acc = new Acc(Integer.parseInt(values[0]));
    acc.setSelect(values[1]);
    acc.setStyle(values[2]);
    acc.setSize(values[3]);
    acc.setPassword(values[4]);
    acc.setViewCount(Integer.parseInt(values[5]));
    acc.setCreatedDate(Long.parseLong(values[6]));

    if (Acc.accNo <= acc.getNo()) {
      Acc.accNo = acc.getNo() + 1;
    }
    return acc;
  }

  @Override
  public String toCsvString() {

    return String.format("%d,%s,%s,%s,%s,%d,%d\n", this.getNo(), this.getSelect(), this.getStyle(),
        this.getSize(), this.getPassword(), this.getViewCount(), this.getCreatedDate());
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
