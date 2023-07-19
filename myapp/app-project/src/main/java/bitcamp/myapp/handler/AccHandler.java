package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Acc;
import bitcamp.util.List;
import bitcamp.util.BreadcrumbPrompt;

public class AccHandler implements Handler {


  private List list;
  private BreadcrumbPrompt prompt;
  private String title;

  public AccHandler(BreadcrumbPrompt prompt, String title, List list) {
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
          this.inputAcc();
          break;
        case "2":
          this.printAcc();
          break;
        case "3":
          this.viewAcc();
          break;
        case "4":
          this.updateAcc();
          break;
        case "5":
          this.deleteAcc();
          break;
      }
    }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. acc등록\n");
    menu.append("2. acc목록\n");
    menu.append("3. acc조회\n");
    menu.append("4. acc변경\n");
    menu.append("5. acc삭제\n");
    menu.append("0. 메인\n");
    return menu.toString();
  }

  private void inputAcc() {
    Acc acc = new Acc();
    inputSelect(acc);
    inputStyle(acc);
    inputSize(acc);

    this.list.add(acc);
  }

  public void printAcc() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 악세서리, 스타일, 사이즈");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Acc acc = (Acc) this.list.get(i);
      System.out.printf("%d, %s, %s, %s \n", acc.getNo(), acc.getSelect(), acc.getStyle(),
          acc.getSize());
    }
  }

  private void viewAcc() {
    int accNo = this.prompt.inputInt("번호? ");

    Acc acc = this.findBy(accNo);
    if (acc == null) {
      System.out.println("해당 번호의 스타일이 없습니다!");
      return;
    }

    System.out.printf("악세사리: %s\n", acc.getSelect());
    System.out.printf("스타일: %s\n", acc.getStyle());
    System.out.printf("사이즈: %s\n", acc.getSize());
  }

  public void updateAcc() {
    int accNo = this.prompt.inputInt("번호? ");
    Acc acc = this.findBy(accNo);
    if (acc == null) {
      System.out.println("해당 번호의 스타일이 없습니다!");
      return;
    }
    inputSelect(acc);
    inputStyle(acc);
    inputSize(acc);
  }



  private void inputStyle(Acc acc) {
    loop: while (true) {
      String menuNo = this.prompt.inputString(
          "좋아하는 스타일:\n" + "  1. 데일리\n" + "  2. 힙\n" + "  3. 스트릿\n" + "  4. 아메카지\n" + "> ");

      switch (menuNo) {
        case "1":
          acc.setStyle("데일리");
          break loop;
        case "2":
          acc.setStyle("힙");
          break loop;
        case "3":
          acc.setStyle("스트릿");
          break loop;
        case "4":
          acc.setStyle("아메카지");
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private void inputSelect(Acc acc) {
    loop: while (true) {
      String menuNo = this.prompt.inputString(
          "좋아하는 악세사리:\n" + "  1. 목걸이\n" + "  2. 반지\n" + "  3. 안경\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          acc.setSelect("목걸이");
          break loop;
        case "2":
          acc.setSelect("반지");
          break loop;
        case "3":
          acc.setSelect("안경");
          break loop;
        case "4":
          acc.setSelect(inputOtherBrand());
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private String inputOtherBrand() {
    return this.prompt.inputString("좋아하는 악세사리를 입력하세요: ");
  }

  private void inputSize(Acc acc) {
    loop: while (true) {
      String menuNo = this.prompt.inputString(
          "좋아하는 사이즈:\n" + "  1. 9호\n" + "  2. 10호\n" + "  3. 11호\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          acc.setSize("9호");
          break loop;
        case "2":
          acc.setSize("10호");
          break loop;
        case "3":
          acc.setSize("11호");
          break loop;
        case "4":
          acc.setSize(inputOtherSize());
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private String inputOtherSize() {
    return this.prompt.inputString("자신의 사이즈를 입력하세요: ");
  }

  private void deleteAcc() {
    if (!this.list.remove(new Acc(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }

  private Acc findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Acc acc = (Acc) this.list.get(i);
      if (acc.getNo() == no) {
        return acc;
      }
    }
    return null;
  }
}
