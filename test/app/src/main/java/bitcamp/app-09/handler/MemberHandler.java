package bitcamp.handler;

import bitcamp.util.Prompt;

public class MemberHandler {

  static final int MAX_SIZE = 100;
  static int[] no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] age = new String[MAX_SIZE];
  static String[] top = new String[MAX_SIZE];
  static String[] pants = new String[MAX_SIZE];
  static String[] shoes = new String[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  public static void inputMember() {
    name[length] = Prompt.inputString("이름? ");
    age[length] = Prompt.inputString("나이? ");

    loop: while (true) {
        String menuNo = Prompt.inputString("상의:\n" + 
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
        String menuNo = Prompt.inputString("바지:\n" + 
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
         String menuNo = Prompt.inputString("신발:\n" + 
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

  public static void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %c\n", no[i], name[i], age[i], top[i], pants[i], shoes[i]);
    }
  }

  public static boolean available() {
    return length < MAX_SIZE;
  }
}