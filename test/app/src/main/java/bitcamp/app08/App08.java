package bitcamp.app08;

import java.util.Scanner;

public class App08 {
  
  static Scanner scanner = new Scanner(System.in);

  static final int MAX_SIZE = 100;
  static int[] no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] age = new String[MAX_SIZE];
  static String[] top = new String[MAX_SIZE];
  static String[] pants = new String[MAX_SIZE];
  static String[] shoes = new String[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  public static void main(String[] args) {

  printTitle();


    while (length < MAX_SIZE) {
      inputMember();
      if (!promptContinue()) {
        break;
      }
    }

    printMembers();

    scanner.close();
  }

  static void printTitle() {
    System.out.println("회원별 옷 사이즈 관리 시스템");
    System.out.println("----------------------------------");
  }

  static void inputMember() {
    name[length] = prompt("이름? ");
    age[length] = prompt("나이? ");

    loop: while (true) {
        String menuNo = prompt("상의:\n" + 
        "  1. M\n" + 
        "  2. L\n" +
        "  3. XL\n" + 
        "> ");

        switch (menuNo) {
            case "1":
              top[length] = "M";
              break loop;
            case "2":
              top[length] = "L";
              break loop;
            case "3":
              top[length] = "XL";
              break loop;
            default:
              System.out.println("무효한 번호입니다.");
          }
        }

    loop: while (true) {
        String menuNo = prompt("바지:\n" + 
        "  1. 28\n" + 
        "  2. 30\n" +
        "  3. 32\n" + 
        "> ");

        switch (menuNo) {
            case "1":
              pants[length] = "28";
              break loop;
            case "2":
              pants[length] = "30";
              break loop;
            case "3":
              pants[length] = "32";
              break loop;
            default:
              System.out.println("무효한 번호입니다.");
          }
        }
    loop: while (true) {
         String menuNo = prompt("신발:\n" + 
        "  1. 260\n" + 
        "  2. 265\n" +
        "  3. 270\n" + 
        "> ");

        switch (menuNo) {
            case "1":
              pants[length] = "260";
              break loop;
            case "2":
              pants[length] = "265";
              break loop;
            case "3":
              pants[length] = "270";
              break loop;
            default:
              System.out.println("무효한 번호입니다.");
          }
        }

        no[length] = userId++;
    length++;
  }

  static boolean promptContinue() {
    String response = prompt("계속 하시겠습니까?(Y/n) ");
    if (!response.equals("") && !response.equalsIgnoreCase("Y")) {
      return false;
    }
    return true;
  }

  static void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %c\n", no[i], name[i], age[i], top[i], pants[i], shoes[i]);
    }
  }

  static String prompt(String title) {
    System.out.print(title);
    return scanner.nextLine();
  }


}

