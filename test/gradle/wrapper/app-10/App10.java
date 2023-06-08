package bitcamp.app;

import bitcamp.app.handler.MemberHandler01;
import bitcamp.util.Prompt01;

public class App10 {
  
  public static void main(String[] args) {

    printTitle();

    printMenu();

    while (true) {
      String menuNo = Prompt01.inputString("메인> ");
      if (menuNo.equals("6")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        MemberHandler01.inputMember();
      } else if (menuNo.equals("2")) {
        MemberHandler01.printMembers();
      } else if (menuNo.equals("3")) {
        MemberHandler01.viewMember();
      } else if (menuNo.equals("4")) {
        MemberHandler01.updateMember();
      } else if (menuNo.equals("5")) {
        MemberHandler01.deleteMember();
      } else {
        System.out.println(menuNo);
      }
    }

    Prompt01.close();
  }
  static void printMenu() {
    System.out.println("1. 회원등록");
    System.out.println("2. 회원목록");
    System.out.println("3. 회원조회");
    System.out.println("4. 회원변경");
    System.out.println("5. 회원삭제");
    System.out.println("6. 종료");
  }

  static void printTitle() {
    System.out.println("나의 목록 관리 시스템");
    System.out.println("----------------------------------");
  }

  static boolean promptContinue() {
    String response = Prompt01.inputString("계속 하시겠습니까?(Y/n) ");
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }
}
