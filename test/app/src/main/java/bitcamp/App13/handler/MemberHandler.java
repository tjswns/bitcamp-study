package bitcamp.App13.handler;

import bitcamp.App13.util.Prompt;
import bitcamp.App13.vo.Member;


public class MemberHandler {

  static final int MAX_SIZE = 100;
  static Member[] members = new Member[MAX_SIZE];
  static int length = 0;



  public static void inputMember() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member();
    m.setName(Prompt.inputString("이름? "));
    m.setAge(Prompt.inputString("나이? "));
    m.setGender(inputGender((char) 0));
    inputTop(m);
    inputPants(m);
    inputShoes(m);
    members[length++] = m;
  }

  public static void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발, 성별");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getTop(), m.getPants(), m.getShoes(), toGenderString(m.getGender()));
    }
  }

  public static void viewMember() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름: %s\n", m.getName());
        System.out.printf("나이: %s\n", m.getAge());
        System.out.printf("성별: %s\n", toGenderString(m.getGender()));
        System.out.printf("상의: %s\n", m.getTop());
        System.out.printf("바지: %s\n", m.getPants());
        System.out.printf("신발: %s\n", m.getShoes());
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  public static void updateMember() {
    String memberNo = Prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s)? ", m.getName());
        m.setName(Prompt.inputString(""));
        System.out.printf("나이(%s)? ", m.getAge());
        m.setAge(Prompt.inputString(""));
        m.setGender(inputGender(m.getGender()));
        inputTop(m);
        inputPants(m);
        inputShoes(m);
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private static char inputGender(char gender) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }
    while (true) {
      String menuNo = Prompt.inputString(label + "  1. 남자\n" + "  2. 여자\n" + "> ");

      switch (menuNo) {
        case "1":
          return Member.MALE;
        case "2":
          return Member.FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private static void inputTop(Member m) {
    if (m.getGender() == Member.MALE) {
      loop: while (true) {
        String menuNo = Prompt.inputString("상의:\n" + "  1. M\n" + "  2. L\n" + "  3. XL\n" + "> ");

        switch (menuNo) {
          case "1":
            m.setTop("M");
            break loop;
          case "2":
            m.setTop("L");
            break loop;
          case "3":
            m.setTop("XL");
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }
    } else if (m.getGender() == Member.FEMALE) {

      loop: while (true) {
        String menuNo = Prompt.inputString("상의:\n" + "  1. XS\n" + "  2. S\n" + "  3. M\n" + "> ");

        switch (menuNo) {
          case "1":
            m.setTop("XS");
            break loop;
          case "2":
            m.setTop("S");
            break loop;
          case "3":
            m.setTop("M");
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }
    }
  }

  private static void inputPants(Member m) {
    if (m.getGender() == Member.MALE) {
      loop: while (true) {
        String menuNo =
            Prompt.inputString("바지:\n" + "  1. 28\n" + "  2. 30\n" + "  3. 32\n" + "> ");

        switch (menuNo) {
          case "1":
            m.setPants("28");
            break loop;
          case "2":
            m.setPants("30");
            break loop;
          case "3":
            m.setPants("32");
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }
    } else if (m.getGender() == Member.FEMALE) {

      loop: while (true) {
        String menuNo =
            Prompt.inputString("바지:\n" + "  1. 24\n" + "  2. 26\n" + "  3. 28\n" + "> ");

        switch (menuNo) {
          case "1":
            m.setPants("24");
            break loop;
          case "2":
            m.setPants("26");
            break loop;
          case "3":
            m.setPants("28");
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }
    }
  }

  private static void inputShoes(Member m) {
    if (m.getGender() == Member.MALE) {
      loop: while (true) {
        String menuNo =
            Prompt.inputString("신발:\n" + "  1. 260\n" + "  2. 265\n" + "  3. 270\n" + "> ");

        switch (menuNo) {
          case "1":
            m.setShoes("260");
            break loop;
          case "2":
            m.setShoes("265");
            break loop;
          case "3":
            m.setShoes("270");
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }
    } else if (m.getGender() == Member.FEMALE) {

      loop: while (true) {
        String menuNo =
            Prompt.inputString("신발:\n" + "  1. 240\n" + "  2. 245\n" + "  3. 250\n" + "> ");

        switch (menuNo) {
          case "1":
            m.setShoes("240");
            break loop;
          case "2":
            m.setShoes("245");
            break loop;
          case "3":
            m.setShoes("250");
            break loop;
          default:
            System.out.println("무효한 번호입니다.");
        }
      }
    }
  }

  public static void deleteMember() {
    int memberNo = Prompt.inputInt("번호? ");

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
      Member m = members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private static boolean available() {
    return length < MAX_SIZE;
  }
}
