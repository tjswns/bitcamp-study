package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardHandler {

  // 인스턴스에 상관없이 공통으로 사용하는 필드라면 스태틱 필드로 선언한다.
  private static final int MAX_SIZE = 100;

  // 인스턴스 마다 별개로 관리해야 할 데이터라면 논스태틱 필드(인스턴스 필드)로 선언한다.
  private Prompt prompt;
  private Board[] boards = new Board[MAX_SIZE];
  private int length = 0;

  public BoardHandler(Prompt prompt) {
    this.prompt = prompt;
  }

  // 인스턴스 멤버(필드나 메서드)를 사용하는 경우 인스턴스 메서드로 정의해야 한다.
  public void inputBoard() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Board board = new Board();
    board.setStyle(this.prompt.inputString("스타일? "));
    inputStyle(board);
    inputBrand(board);
    inputFit(board);
    this.boards[this.length++] = board;
  }

  public void printBoard() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 스타일, 브랜드, 핏");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i];
      System.out.printf("%d, %s, %s, %s \n", board.getNo(), board.getStyle(), board.getBrand(),
          board.getFit());
    }
  }

  public void viewBoard() {
    String memberNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < this.length; i++) {
      Board board = this.boards[i];
      if (board.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", board.getStyle());
        System.out.printf("이름: %s\n", board.getBrand());
        System.out.printf("이름: %s\n", board.getFit());
        return;
      }
    }
    System.out.println("해당 번호의 스타일이 없습니다!");
  }


  public void updateBoard() {
    String boardNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Board board = this.boards[i];
      if (board.getNo() == Integer.parseInt(boardNo)) {
        inputStyle(board);
        inputBrand(board);
        inputFit(board);
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private void inputStyle(Board board) {
    loop: while (true) {
      String menuNo =
          this.prompt.inputString("좋아하는 스타일:\n" + "  1. 데일리\n" + "  2. 파티\n" + "  3. 데이트\n" + "> ");

      switch (menuNo) {
        case "1":
          board.setStyle("데일리");
          break loop;
        case "2":
          board.setStyle("파티");
          break loop;
        case "3":
          board.setStyle("데이트");
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }


  private void inputBrand(Board board) {
    loop: while (true) {
      String menuNo = this.prompt.inputString(
          "좋아하는 브랜드:\n" + "  1. 구찌\n" + "  2. 샤넬\n" + "  3. 프라다\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          board.setBrand("구찌");
          break loop;
        case "2":
          board.setBrand("샤넬");
          break loop;
        case "3":
          board.setBrand("프라다");
          break loop;
        case "4":
          board.setBrand("그 외");
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private void inputFit(Board board) {
    loop: while (true) {
      String menuNo = this.prompt.inputString(
          "좋아하는 브랜드:\n" + "  1. 오버핏\n" + "  2. 슬림핏\n" + "  3. 스탠다드핏\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          board.setFit("오버 핏");
          break loop;
        case "2":
          board.setFit("슬림 핏");
          break loop;
        case "3":
          board.setFit("스텐다드 핏");
          break loop;
        case "4":
          board.setFit("그 외");
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }



  public void deleteBoard() {
    int boardNo = this.prompt.inputInt("번호? ");

    int deletedIndex = indexOf(boardNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      this.boards[i] = this.boards[i + 1];
    }

    this.boards[--length] = null;
  }

  private int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Board m = this.boards[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return length < MAX_SIZE;
  }
}
