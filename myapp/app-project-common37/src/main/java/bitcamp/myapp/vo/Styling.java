package bitcamp.myapp.vo;

import java.io.Serializable;

public class Styling implements Serializable, CsvObject, AutoIncrement {

  private static final long serialVersionUID = 1L;
  public static int stylingNo = 1;

  private int no;
  private String Style;
  private String Brand;
  private String fit;
  private String password;
  private int viewCount;
  private long createdDate;


  public Styling() {}

  public Styling(int no) {
    this.no = no;
  }

  public static Styling fromCsv(String csv) {

    String[] values = csv.split(",");
    Styling styling = new Styling(Integer.parseInt(values[0]));
    styling.setStyle(values[1]);
    styling.setBrand(values[2]);
    styling.setFit(values[3]);
    styling.setPassword(values[4]);
    styling.setViewCount(Integer.parseInt(values[5]));
    styling.setCreatedDate(Long.parseLong(values[6]));


    if (Styling.stylingNo <= styling.getNo()) {
      Styling.stylingNo = styling.getNo() + 1;
    }

    return styling;
  }


  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%d,%d\n", this.getNo(), this.getStyle(), this.getBrand(),
        this.getFit(), this.getPassword(), this.getViewCount(), this.getCreatedDate());
  }

  @Override
  public void updateKey() {
    if (Styling.stylingNo <= this.no) {
      Styling.stylingNo = this.no + 1;
    }
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
