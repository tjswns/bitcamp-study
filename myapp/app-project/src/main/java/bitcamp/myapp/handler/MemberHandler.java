package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.List;
import bitcamp.util.BreadcrumbPrompt;

public class MemberHandler implements Handler {

  private List list;
  private BreadcrumbPrompt prompt;
  private String title;

  public MemberHandler(BreadcrumbPrompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
    this.list = list;
  }

  public void execute() {
    prompt.appendBreadcrumb(this.title, getMenu());

    prompt.printMenu();
    while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0":
          prompt.removeBreadcrumb();
          return;
        case "1":
          this.inputMember();
          break;
        case "2":
          this.printMembers();
          break;
        case "3":
          this.viewMember();
          break;
        case "4":
          this.updateMember();
          break;
        case "5":
          this.deleteMember();
          break;
      }
    }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 회원등록\n");
    menu.append("2. 회원목록\n");
    menu.append("3. 회원조회\n");
    menu.append("4. 회원변경\n");
    menu.append("5. 회원삭제\n");
    menu.append("0. 메인\n");
    return menu.toString();
  }

  public void inputMember() {
    Member m = new Member();
    m.setName(this.prompt.inputString("이름? "));
    m.setAge(this.prompt.inputString("나이? "));
    m.setGender(inputGender((char) 0));
    inputTop(m);
    inputPants(m);
    inputShoes(m);
    this.list.add(m);
  }


  public void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 나이, 상의, 하의, 신발, 성별");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      System.out.printf("%d, %s, %s, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getAge(),
          m.getTop(), m.getPants(), m.getShoes(), toGenderString(m.getGender()));
    }
  }

  private void viewMember() {
    int memberNo = this.prompt.inputInt("번호? ");

    Member m = this.findBy(memberNo);
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
    Member m = this.findBy(memberNo);
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
    if (!this.list.remove(new Member(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }

  }

  private Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }
}
