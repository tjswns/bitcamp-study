package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler {

  private static final int MAX_SIZE = 100;
  // variable initializer(변수초기화 문장) => static 블록으로 이동
  // 단 final 변수는 static 블록에서 값을 할당하지 않고 그냥 상수로 취급한다.

  private Prompt prompt;

  private Member[] members = new Member[MAX_SIZE];
  // variable initializer(변수초기화 문장) => 생성자로 이동

  private int length;

  // 생성자: 인스턴스를 사용할 수 있도록 유효한 값으로 초기화시키는 일을 한다.
  // => 필요한 값을 외부에서 받고 싶으면 파라미터를 선언하라.
  public MemberHandler(Prompt prompt) {
    this.prompt = prompt;
  }

  public void inputMember() {
    if (!available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setAge(this.prompt.inputString("나이? "));
    m.setGender(inputGender((char) 0));
    inputTop(m);
    inputPants(m);
    inputShoes(m);
    this.members[this.length++] = m;
  }

  public void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발, 성별");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.length; i++) {
      Member m = this.members[i];
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getTop(), m.getPants(), m.getShoes(), toGenderString(m.getGender()));
    }
  }

  public void viewMember() {
    String memberNo = this.prompt.inputString("번호? ");
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

  public String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  public void updateMember() {
    String memberNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < length; i++) {
      Member m = members[i];
      if (m.getNo() == Integer.parseInt(memberNo)) {
        System.out.printf("이름(%s)? ", m.getName());
        m.setName(this.prompt.inputString(""));
        System.out.printf("나이(%s)? ", m.getAge());
        m.setAge(this.prompt.inputString(""));
        m.setGender(inputGender(m.getGender()));
        inputTop(m);
        inputPants(m);
        inputShoes(m);
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private char inputGender(char gender) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }
    while (true) {
      String menuNo = this.prompt.inputString(label + "  1. 남자\n" + "  2. 여자\n" + "> ");

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

  private void inputTop(Member m) {
    if (m.getGender() == Member.MALE) {
      loop: while (true) {
        String menuNo =
            this.prompt.inputString("상의:\n" + "  1. M\n" + "  2. L\n" + "  3. XL\n" + "> ");

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
        String menuNo =
            this.prompt.inputString("상의:\n" + "  1. XS\n" + "  2. S\n" + "  3. M\n" + "> ");

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

  private void inputPants(Member m) {
    if (m.getGender() == Member.MALE) {
      loop: while (true) {
        String menuNo =
            this.prompt.inputString("바지:\n" + "  1. 28\n" + "  2. 30\n" + "  3. 32\n" + "> ");

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
            this.prompt.inputString("바지:\n" + "  1. 24\n" + "  2. 26\n" + "  3. 28\n" + "> ");

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

  private void inputShoes(Member m) {
    if (m.getGender() == Member.MALE) {
      loop: while (true) {
        String menuNo =
            this.prompt.inputString("신발:\n" + "  1. 260\n" + "  2. 265\n" + "  3. 270\n" + "> ");

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
            this.prompt.inputString("신발:\n" + "  1. 240\n" + "  2. 245\n" + "  3. 250\n" + "> ");

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

  public void deleteMember() {
    int memberNo = this.prompt.inputInt("번호? ");

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

  private int indexOf(int memberNo) {
    for (int i = 0; i < this.length; i++) {
      Member m = members[i];
      if (m.getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return this.length < MAX_SIZE;
  }
}
