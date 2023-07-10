package bitcamp.myapp;

import bitcamp.myapp.handler.AccHandler;
import bitcamp.myapp.handler.BoardHandler;
import bitcamp.myapp.handler.MemberHandler;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) {

    // 기본 생성자를 이용해 Prompt 인스턴스를 준비한다.
    // => 기본 생성자는 Scanner를 키보드와 연결한다.
    Prompt prompt = new Prompt();

    MemberHandler memberHandler = new MemberHandler(prompt);
    BoardHandler boardHandler = new BoardHandler(prompt);
    AccHandler accHandler = new AccHandler(prompt);
    printTitle();

    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("99")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        memberHandler.inputMember();
      } else if (menuNo.equals("2")) {
        memberHandler.printMembers();
      } else if (menuNo.equals("3")) {
        memberHandler.viewMember();
      } else if (menuNo.equals("4")) {
        memberHandler.updateMember();
      } else if (menuNo.equals("5")) {
        memberHandler.deleteMember();
      } else if (menuNo.equals("6")) {
        boardHandler.inputBoard();
      } else if (menuNo.equals("7")) {
        boardHandler.printBoard();
      } else if (menuNo.equals("8")) {
        boardHandler.viewBoard();
      } else if (menuNo.equals("9")) {
        boardHandler.updateBoard();
      } else if (menuNo.equals("10")) {
        boardHandler.deleteBoard();
      } else if (menuNo.equals("11")) {
        accHandler.inputAcc();
      } else if (menuNo.equals("12")) {
        accHandler.printAcc();
      } else if (menuNo.equals("13")) {
        accHandler.viewAcc();
      } else if (menuNo.equals("14")) {
        accHandler.updateAcc();
      } else if (menuNo.equals("15")) {
        accHandler.deleteAcc();
      } else {
        System.out.println(menuNo);
      }
    }

    prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 회원등록");
    System.out.println("2. 회원목록");
    System.out.println("3. 회원조회");
    System.out.println("4. 회원변경");
    System.out.println("5. 회원삭제");
    System.out.println("6. 스타일등록");
    System.out.println("7. 스타일목록");
    System.out.println("8. 스타일조회");
    System.out.println("9. 스타일변경");
    System.out.println("10. 스타일삭제");
    System.out.println("11. acc등록");
    System.out.println("12. acc목록");
    System.out.println("13. acc조회");
    System.out.println("14. acc변경");
    System.out.println("15. acc삭제");
    System.out.println("99. 종료");
  }

  static void printTitle() {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");
  }
}
