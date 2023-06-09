package bitcamp.app11;

import bitcamp.app11.handler3.MemberHandler02;
import bitcamp.app11.util3.Prompt02;

public class App11 {
  
  public static void main(String[] args) {

    printTitle();

    printMenu();

    while (true) {
      String menuNo = Prompt02.inputString("메인> ");
      if (menuNo.equals("6")) {
        break;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        MemberHandler02.inputMember();
      } else if (menuNo.equals("2")) {
        MemberHandler02.printMembers();
      } else if (menuNo.equals("3")) {
        MemberHandler02.viewMember();
      } else if (menuNo.equals("4")) {
        MemberHandler02.updateMember();
      } else if (menuNo.equals("5")) {
        MemberHandler02.deleteMember();
      } else {
        System.out.println(menuNo);
      }
    }

    Prompt02.close();
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
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");
  }

  static boolean promptContinue() {
    String response = Prompt02.inputString("계속 하시겠습니까?(Y/n) ");
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }
}