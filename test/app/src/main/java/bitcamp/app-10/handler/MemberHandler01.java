package bitcamp.handler;

import bitcamp.util.Prompt01;

public class MemberHandler01 {

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
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    name[length] = Prompt01.inputString("이름? ");
    age[length] = Prompt01.inputString("나이? ");
    top[length] = String.valueOf(inputTop((char)1));
    pants[length] = String.valueOf(inputPants((char)1));
    shoes[length] = String.valueOf(inputShoes((char)2));
    no[length] = userId++;
    length++;
  }

  public static void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      System.out.printf("%d, %s, %s, %s, %s, %s\n",
       no[i], name[i], age[i], toTopString(top[i]),
       toPantsString(pants[i]), toShoesString(shoes[i]));
    }
  }

  public static void viewMember() {
    String memberNo = Prompt01.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      if (no[i] == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", name[i]);
        System.out.printf("나이: $d\n", age[i]);
        System.out.printf("상의: %s\n", toTopString(top[i]));
        System.out.printf("하의: %s\n", toPantsString(pants[i]));
        System.out.printf("신발: %s\n", toShoesString(shoes[i]));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

public static String toTopString(char top) {
    return top == 'M' ? "M" : (top == 'L' ? "L" : "XL");
}

public static String toPantsString(char pants) {
  return pants == '1' ? "28" : (pants == '2' ? "30" : "32");
}

public static String toShoesString(char shoes) {
  return shoes == '1' ? "260" : (shoes == '2' ? "265" : "270");
}

public static void updateMember() {
  String memberNo = Prompt01.inputString("번호? ");
  for (int i = 0; i < length; i++) {
    if (no[i] == Integer.parseInt(memberNo)) {
      System.out.printf("이름(%s)? ", name[i]);
      name[i] = Prompt01.inputString("");
      System.out.printf("나이(%d)? ", age[i]);
      age[i] = Prompt01.inputString("");
      top[i] = inputTop(top[i]);
    pants[i] = inputPants(pants[i]);
    shoes[i] = inputShoes(shoes[i]);
    return;
    }
  }
  System.out.println("해당 번호의 회원이 없습니다!");
}

private static char inputTop(char top) {
  String label;
  if (top == 1) {
    label = "상의?\n";
  } else {
    label = String.format("상의(%s)?\n", toTopString(top));
  }
  loop: while (true) {
    String menuNo = Prompt01.inputString(label + 
    "  1. M\n" + 
    "  2. L\n" +
    "  3. XL\n" + 
    "> ");

    switch (menuNo) {
      case "1":
        return M;
      case "2":
        return L;
      case "3":
        return XL;
      default:
        System.out.println("무효한 번호입니다.");
    }
  }
}

private static char inputPants(char pants) {
  String label;
  if (pants == 1) {
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
        return 28;
      case "2":
        return 30;
      case "3":
        return 32;
      default:
        System.out.println("무효한 번호입니다.");
    }
  }
}

private static char inputShoes(char shoes) {
  String label;
  if (shoes == 2) {
    label = "신발?\n";
  } else {
    label = String.format("신발(%s)?\n", toShoesString(shoes));
  }
  loop: while (true) {
    String menuNo = Prompt01.inputString(label + 
    "  1. 260\n" + 
    "  2. 265\n" +
    "  3. 270\n" + 
    "> ");

    switch (menuNo) {
      case "1":
        return 260;
      case "2":
        return 265;
      case "3":
        return 270;
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
    no[i] = no[i + 1];
    name[i] = name[i + 1];
    age[i] = age[i + 1];
    top[i] = top[i +1];
    pants[i] = pants[i + 1];
    shoes[i] = shoes[i + 1];
  }

  no[length - 1] =0;
  name[length - 1] = null;
  age[length - 1] = null;
  top[length - 1] = (char) 0;
  pants[length - 1] = (char) 0;
  shoes[length - 1] = (char) 0;

  length--;
  }

  private static int indexOf(int memberNo) {
    for (int i = 0; i < length; i++) {
      if (no[i] == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }
}