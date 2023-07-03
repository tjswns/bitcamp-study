package bitcamp.myappProject.handler;

import java.util.List;
import bitcamp.App13.util.Prompt;
import bitcamp.myappProject.vo.Member;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public abstract class AbstractMemberListener implements ActionListener {

  protected List<Member> list;

  public AbstractMemberListener(List<Member> list) {
    this.list = list;
  }

  protected static String toGenderString(char gender) {
    return gender == 'M' ? "남성" : "여성";
  }

  protected Member findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  protected char inputGender(char gender, BreadcrumbPrompt prompt) {
    String label;
    if (gender == 0) {
      label = "성별?\n";
    } else {
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }

    while (true) {
      String menuNo = prompt.inputString(label + "  1. 남자\n" + "  2. 여자\n" + "> ");

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

  protected static void inputTop(Member m) {
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

  protected static void inputPants(Member m) {
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

  protected static void inputShoes(Member m) {
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
}
