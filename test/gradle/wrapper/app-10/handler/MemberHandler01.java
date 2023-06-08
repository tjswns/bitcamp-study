package bitcamp.app.handler;

import bitcamp.util.Prompt01;
import bitcamp.app.vo.Member01;

public class MemberHandler01 {

  static final int MAX_SIZE = 100;
  static Member01[] members = new Member01[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final char S = 'S';
  static final char M = 'M';
  static final char L = 'L';
  static final char PONE = '1';
  static final char PTWO = '2';
  static final char PTHREE = '3';
  static final char SONE = '4';
  static final char STWO = '5';
  static final char STHREE = '6';

  public static void inputMember() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member01 m = new Member01();
    m.name = Prompt01.inputString("이름? ");
    m.age = Prompt01.inputString("나이? ");
    m.top = Prompt01.inputTop((char)0);
    m.pants = inputPants((char)0);
    m.shoes = inputShoes((char)0);
    m.no = userId++;

    // 위에서 만든 Member 인스턴스의 주소를 잃어버리지 않게 
    // 레퍼런스 배열에 담는다.
    members[length++] = m;
  }

  public static void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Member01 m = members[i];
      System.out.printf("%d, %s, %s, %s, %s, $s\n", 
        m.no, m.name, m.age,  
        toTopString(m.top),
        toPantsString(m.pants),
        toShoesString(m.shoes));
    }
  }

  public static void viewMember() {
    String memberNo = Prompt01.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member01 m = members[i];
      if (m.no == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", m.name);
        System.out.printf("나이: %s\n", m.age);
        System.out.printf("상의: %s\n", toTopString(m.top));
        System.out.printf("상의: %s\n", toPantsString(m.pants));
        System.out.printf("상의: %s\n", toShoesString(m.shoes));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toTopString(char top) {
    return top == 'S' ? "S" : (top == 'M' ? "M" : "L");
  }

  public static String toPantsString(char pants) {
    return pants == '1' ? "1" : (pants == '2' ? "2" : "3");
  }

  public static String toShoesString(char shoes) {
    return shoes == '4' ? "4" : (shoes == '5' ? "5" : "6");
  }




  public static void updateMember() {
    String memberNo = Prompt01.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member01 m = members[i];
      if (m.no == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s)? ", m.name);
        m.name = Prompt01.inputString("");
        System.out.printf("나이(%s)? ", m.age);
        m.age = Prompt01.inputString("");
        m.top = inputTop(m.top);
        m.pants = inputPants(m.pants);
        m.shoes = inputShoes(m.shoes);
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private static char inputTop(char top) {
    String label;
    if (top == 0) {
      label = "상의?\n";
    } else {
      label = String.format("상의(%s)?\n", toTopString(top));
    }
    loop: while (true) {
      String menuNo = Prompt01.inputString(label + 
      "  1. S\n" + 
      "  2. L\n" +
      "  3. L\n" + 
      "> ");

      switch (menuNo) {
        case "1":
          return S;
        case "2":
          return M;
        case "3":
          return L;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private static char inputPants(char pants) {
    String label;
    if (pants == 0) {
      label = "바지?\n";
    } else {
      label = String.format("바지(%s)?\n", toPantsString(pants));
    }
    loop: while (true) {
      String menuNo = Prompt01.inputString(label + 
      "  1. 28\n" + 
      "  2. 30\n" +
      "  3. 32\n" + 
      "> ");

      switch (menuNo) {
        case "1":
          return PONE;
        case "2":
          return PTWO;
        case "3":
          return PTHREE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private static char inputShoes(char shoes) {
    String label;
    if (shoes == 0) {
      label = "신발?\n";
    } else {
      label = String.format("신발(%s)?\n", toShoeString(shoes));
    }
    loop: while (true) {
      String menuNo = Prompt01.inputString(label + 
      "  1. 260\n" + 
      "  2. 265\n" +
      "  3. 270\n" + 
      "> ");

      switch (menuNo) {
        case "1":
          return SONE;
        case "2":
          return STWO;
        case "3":
          return STHREE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteMember() {
    int memberNo = Prompt01.inputInt("번호? ");

    int deletedIndex = indexOf(memberNo);
    if (deletedIndex == -1) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < length - 1; i++) {
      members[i] = members[i + 1];
    }

    members[--length] = null;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      Member01 m = members[i];
      if (m.no == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }
}

