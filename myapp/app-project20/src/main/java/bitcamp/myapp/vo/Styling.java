package bitcamp.myapp.vo;

public class Styling {

  private static int boardNo = 1;

  private int no;
  private String Style;
  private String Brand;
  private String fit;

  private long createdDate;


  public Styling() {
    this.no = boardNo++;
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

    Styling styling = (Styling) obj;

    if (this.getNo() != styling.getNo()) {
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


}
