package bitcamp.myapp;

import bitcamp.myapp.handler.AccHandler;
import bitcamp.myapp.handler.Handler;
import bitcamp.myapp.handler.MemberHandler;
import bitcamp.myapp.handler.StylingHandler;
import bitcamp.util.Prompt;

public class App {

  public static void main(String[] args) {

    // 기본 생성자를 이용해 Prompt 인스턴스를 준비한다.
    // => 기본 생성자는 Scanner를 키보드와 연결한다.
    Prompt prompt = new Prompt();

    // 모든 핸들러는 Handler 규칙에 따라 정의되었기 때문에
    // Handler 레퍼런스에 그 주소를 담을 수 있다.
    Handler memberHandler = new MemberHandler(prompt, "회원");
    Handler stylingHandler = new StylingHandler(prompt, "스타일");
    Handler accHandler = new AccHandler(prompt, "악세서리");
    printTitle();

    printMenu();

    while (true) {
      String menuNo = prompt.inputString("메인> ");
      if (menuNo.equals("0")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        memberHandler.execute();
      } else if (menuNo.equals("2")) {
        stylingHandler.execute();
      } else if (menuNo.equals("3")) {
        accHandler.execute();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

    prompt.close();
  }

  static void printMenu() {
    System.out.println("1. 회원");
    System.out.println("2. 스타일");
    System.out.println("3. 악세서리");
    System.out.println("0. 종료");
  }

  static void printTitle() {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");
  }
}
