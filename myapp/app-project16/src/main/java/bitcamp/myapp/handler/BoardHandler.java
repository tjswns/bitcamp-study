package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardHandler {

  private static final int MAX_SIZE = 100;

  private Prompt prompt;
  private Board[] boards = new Board[MAX_SIZE];
  private int length = 0;
  private String title;

  public BoardHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void service() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")) {
        return;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        this.inputBoard();
      } else if (menuNo.equals("2")) {
        this.printBoard();
      } else if (menuNo.equals("3")) {
        this.viewBoard();
      } else if (menuNo.equals("4")) {
        this.updateBoard();
      } else if (menuNo.equals("5")) {
        this.deleteBoard();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 스타일등록");
    System.out.println("2. 스타일목록");
    System.out.println("3. 스타일조회");
    System.out.println("4. 스타일변경");
    System.out.println("5. 스타일삭제");
    System.out.println("0. 메인");
  }

  public void inputBoard() {
    if (!this.available()) {
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
          board.setBrand(inputOtherBrand());
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private String inputOtherBrand() {
    return this.prompt.inputString("좋아하는 브랜드를 입력하세요: ");
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
          board.setFit(inputOtherFit());
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private String inputOtherFit() {
    return this.prompt.inputString("선호하는 핏을 입력하세요: ");
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
      Board board = this.boards[i];
      if (board.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return length < MAX_SIZE;
  }
}
