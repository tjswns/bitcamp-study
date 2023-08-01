package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Acc implements Serializable {
  private static final long serialVersionUID = 1L;

  private int no;
  private String style;
  private String choose;
  private String size;
  private String ohter;
  private String password;
  private int viewCount;
  private Timestamp createdDate;
  private int category;

  public Acc() {

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


  public String getChoose() {
    return choose;
  }

  public void setChoose(String choose) {
    this.choose = choose;
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

  public Timestamp getCreatedDate() {
    return createdDate;
  }


  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
  }

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }


}
