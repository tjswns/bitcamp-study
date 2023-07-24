package bitcamp.myapp.vo;

public class Styling {

  public static int stylingNo = 1;

  private int no;
  private String Style;
  private String Brand;
  private String fit;
  private String password;
  private int viewCount;
  private long createdDate;


  public Styling() {
    this.no = stylingNo++;
    this.createdDate = System.currentTimeMillis();
  }

  public Styling(int no) {
    this.no = no;
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Styling sty = (Styling) obj;

    if (this.getNo() != sty.getNo()) {
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


  public long getCreatedDate() {
    return createdDate;
  }


  public void setCreatedDate(long createdDate) {
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


}
