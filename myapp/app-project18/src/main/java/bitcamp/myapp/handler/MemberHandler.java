package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler implements Handler {

  private MemberList list = new MemberList();
  private Prompt prompt;
  private String title;

  public MemberHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void execute() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")) {
        return;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        this.inputMember();
      } else if (menuNo.equals("2")) {
        this.printMembers();
      } else if (menuNo.equals("3")) {
        this.viewMember();
      } else if (menuNo.equals("4")) {
        this.updateMember();
      } else if (menuNo.equals("5")) {
        this.deleteMember();
      } else {
        System.out.println(menuNo);
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 회원등록");
    System.out.println("2. 회원목록");
    System.out.println("3. 회원조회");
    System.out.println("4. 회원변경");
    System.out.println("5. 회원삭제");
    System.out.println("0. 메인");
  }

  public void inputMember() {
    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setAge(this.prompt.inputString("나이? "));
    m.setGender(inputGender((char) 0));
    inputTop(m);
    inputPants(m);
    inputShoes(m);
    if (!this.list.add(m)) {
      System.out.println("입력 실패입니다!");
    }
  }

  public void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발, 성별");
    System.out.println("---------------------------------------");

    Member[] arr = this.list.list();
    for (Member m : arr) {
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getTop(), m.getPants(), m.getShoes(), toGenderString(m.getGender()));
    }
  }

  public void viewMember() {
    int memberNo = this.prompt.inputInt("번호? ");
    Member m = this.list.get(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("나이: %s\n", m.getAge());
    System.out.printf("성별: %s\n", toGenderString(m.getGender()));
    System.out.printf("상의: %s\n", m.getTop());
    System.out.printf("바지: %s\n", m.getPants());
    System.out.printf("신발: %s\n", m.getShoes());

  }

  public String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  public void updateMember() {
    int memberNo = this.prompt.inputInt("번호? ");
    Member m = this.list.get(memberNo);
    if (m == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    m.setName(this.prompt.inputString("이름(%s)?", m.getName()));
    m.setAge(this.prompt.inputString("나이(%s)? ", m.getAge()));
    m.setGender(inputGender(m.getGender()));
    inputTop(m);
    inputPants(m);
    inputShoes(m);
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
    if (!this.list.delete(this.prompt.inputInt("번호? "))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }

  }

}
