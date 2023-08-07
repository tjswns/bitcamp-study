package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class Styling implements Serializable {

  private static final long serialVersionUID = 1L;
  public static int stylingNo = 1;

  private int no;
  private String Style;
  private String Brand;
  private String fit;
  private Member writer;
  private String password;
  private int viewCount;
  private Timestamp createdDate;
  private int category;

  @Override
  public int hashCode() {
    return Objects.hash(no);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Styling other = (Styling) obj;
    return no == other.no;
  }

  public int getNo() {
    return no;
  }


  public void setNo(int no) {
    this.no = no;
  }


  public String getStyle() {
    return Style;
  }


  public void setStyle(String style) {
    Style = style;
  }


  public String getBrand() {
    return Brand;
  }


  public void setBrand(String brand) {
    Brand = brand;
  }


  public String getFit() {
    return fit;
  }


  public void setFit(String fit) {
    this.fit = fit;
  }



  public Timestamp getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Timestamp createdDate) {
    this.createdDate = createdDate;
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

  public int getCategory() {
    return category;
  }

  public void setCategory(int category) {
    this.category = category;
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }


}
