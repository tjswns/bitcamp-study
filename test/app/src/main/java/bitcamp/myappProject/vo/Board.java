package bitcamp.myappProject.vo;

import java.io.Serializable;

public class Board implements Serializable, CsvObject, AutoIncrement {
  private static final long serialVersionUID = 1L;

  public static int boardNo = 1;

  private int no;
  private String style;
  private String brand;
  private String fit;

  private long createdDate;

  public Board() {}

  public Board(int no) {
    this.no = no;
  }

  public static Board fromCsv(String csv) {
    String[] values = csv.split(",");

    Board board = new Board(Integer.parseInt(values[0]));
    board.setStyle(values[1]);
    board.setBrand(values[2]);
    board.setFit(values[3]);


    board.setCreatedDate(Long.parseLong(values[6]));

    if (Board.boardNo <= board.getNo()) {
      Board.boardNo = board.getNo() + 1;
    }

    return board;
  }

  @Override
  public void updateKey() {
    if (Board.boardNo <= this.no) {
      Board.boardNo = this.no + 1;
    }
  }

  public String getStyle() {
    return style;
  }

  public void setStyle(String style) {
    this.style = style;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getFit() {
    return fit;
  }

  public void setFit(String fit) {
    this.fit = fit;
  }

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%d,%d", this.getNo(), this.getCreatedDate());
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Board b = (Board) obj;

    if (this.getNo() != b.getNo()) {
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



  public long getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
  }



}
