package bitcamp.myapp;

import bitcamp.myapp.handler.AccHandler;
import bitcamp.myapp.handler.Handler;
import bitcamp.myapp.handler.MemberHandler;
import bitcamp.myapp.handler.StylingHandler;
import bitcamp.util.ArrayList;
import bitcamp.util.BreadcrumbPrompt;

public class App {

  public static void main(String[] args) {

    // 기본 생성자를 이용해 Prompt 인스턴스를 준비한다.
    // => 기본 생성자는 Scanner를 키보드와 연결한다.
    BreadcrumbPrompt prompt = new BreadcrumbPrompt();
    prompt.appendBreadcrumb("메인", getMenu());
    // 모든 핸들러는 Handler 규칙에 따라 정의되었기 때문에
    // Handler 레퍼런스에 그 주소를 담을 수 있다.
    Handler memberHandler = new MemberHandler(prompt, "회원", new ArrayList());
    Handler stylingHandler = new StylingHandler(prompt, "스타일", new ArrayList());
    Handler accHandler = new AccHandler(prompt, "악세서리", new ArrayList());
    printTitle();

    prompt.printMenu();

    loop: while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0":
          break loop;
        case "1":
          memberHandler.execute();
          break;
        case "2":
          stylingHandler.execute();
          break;
        case "3":
          accHandler.execute();
          break;
      }
    }

    prompt.close();
  }

  static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 회원\n");
    menu.append("2. 스타일\n");
    menu.append("3. 악세서리\n");
    menu.append("0. 종료\n");
    return menu.toString();
  }

  static void printTitle() {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");
  }
}
