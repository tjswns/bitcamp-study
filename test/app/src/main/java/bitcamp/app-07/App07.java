package bitcamp.app;

import java.util.Scanner;

public class App07 {
  public static void main(String[] args) {
   
    Scanner scanner = new Scanner(System.in);

    final int MAX_SIZE = 100;
    int userId = 1;
    int length = 0;

    int[] no = new int[MAX_SIZE];
    String[] name = new String[MAX_SIZE];
    String[] age = new String[MAX_SIZE];
    String[] top = new String[MAX_SIZE];
    String[] pants = new String[MAX_SIZE];
    String[] shoes = new String[MAX_SIZE];

    printTitle();

    for (int i = 0; i < MAX_SIZE; i++) {
      inputMember(scanner, i, name, age, top, pants,shoes, no, userId++);
      length++;
      if (!promptContinue(scanner)) {
        break;
      }
    }

    printMembers(length, no, name, age, top, pants, shoes);

    scanner.close();
  }
  static void printTitle() {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");
  }

  static void inputMember(Scanner scanner, int i,
      String[] name, String[] age, String[] top, String[] pants,String[] shoes, int[] no, int userId) {

    System.out.print("이름? ");
    name[i] = scanner.next();

    System.out.print("나이? ");
    age[i] = scanner.next();

    loop: while (true) {
      System.out.println("상의: ");
      System.out.println("  1. M");
      System.out.println("  2. L");
      System.out.println("  3. XL");
      System.out.print("> ");
      String menuNo = scanner.next();


      switch (menuNo) {
        case "1":
          top[i] = "M";
          break loop;
        case "2":
          top[i] = "L";
          break loop;
        case "3":
          top[i] = "XL";
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }

    loop: while (true) {
      System.out.println("바지: ");
      System.out.println("  1. 28");
      System.out.println("  2. 30");
      System.out.println("  3. 32");
      System.out.print("> ");
      String menuNo = scanner.next();

      
      switch (menuNo) {
        case "1":
          pants[i] = "28";
          break loop;
        case "2":
          pants[i] = "30";
          break loop;
        case "3":
          pants[i] = "32";
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }

    loop: while (true) {
      System.out.println("신발: ");
      System.out.println("  1. 260");
      System.out.println("  2. 265");
      System.out.println("  3. 270");
      System.out.print("> ");
      String menuNo = scanner.next();

      
      switch (menuNo) {
        case "1":
          pants[i] = "260";
          break loop;
        case "2":
          pants[i] = "265";
          break loop;
        case "3":
          pants[i] = "270";
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }

    no[i] = userId;
  }
  static boolean promptContinue(Scanner scanner) {
    System.out.print("계속 하시겠습니까?(Y/n) ");
    String response = scanner.nextLine();
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }

  static void printMembers(int length, int[] no, String[] name, String[] age, String[] top, String[] pants,String[] shoes) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %c\n", no[i], name[i], age[i], top[i], pants[i], shoes[i]);
    }
  }
}