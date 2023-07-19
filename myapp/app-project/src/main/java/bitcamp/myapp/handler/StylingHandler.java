package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Styling;
import bitcamp.util.List;
import bitcamp.util.BreadcrumbPrompt;

public class StylingHandler implements Handler {

  private List list;
  private BreadcrumbPrompt prompt;
  private String title;

  public StylingHandler(BreadcrumbPrompt prompt, String title, List list) {
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
          this.inputStyling();
          break;
        case "2":
          this.printStyling();
          break;
        case "3":
          this.viewStyling();
          break;
        case "4":
          this.updateStyling();
          break;
        case "5":
          this.deleteStyling();
          break;
      }
    }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 스타일등록\n");
    menu.append("2. 스타일목록\n");
    menu.append("3. 스타일조회\n");
    menu.append("4. 스타일변경\n");
    menu.append("5. 스타일삭제\n");
    menu.append("0. 메인\n");
    return menu.toString();
  }

  public void inputStyling() {
    Styling styling = new Styling();
    inputStyle(styling);
    inputBrand(styling);
    inputFit(styling);

    this.list.add(styling);
  }

  public void printStyling() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 스타일, 브랜드, 핏");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Styling styling = (Styling) this.list.get(i);
      System.out.printf("%d, %s, %s, %s \n", styling.getNo(), styling.getStyle(),
          styling.getBrand(), styling.getFit());
    }
  }

  public void viewStyling() {
    int stylingNo = this.prompt.inputInt("번호? ");

    Styling styling = this.findBy(stylingNo);
    if (styling == null) {
      System.out.println("해당 번호의 스타일이 없습니다!");
      return;
    }
    System.out.printf("이름: %s\n", styling.getStyle());
    System.out.printf("이름: %s\n", styling.getBrand());
    System.out.printf("이름: %s\n", styling.getFit());
    return;
  }


  public void updateStyling() {
    int stylingNo = this.prompt.inputInt("번호? ");
    Styling styling = this.findBy(stylingNo);
    if (styling == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    inputStyle(styling);
    inputBrand(styling);
    inputFit(styling);


  }

  private void inputStyle(Styling board) {
    loop: while (true) {
      String menuNo =
          this.prompt.inputString("좋아하는 스타일:\n" + "  1. 데일리\n" + "  2. 파티\n" + "  3. 데이트\n" + "> ");

      switch (menuNo) {
        case "1":
          board.setStyle("데일리");
          break loop;
        case "2":
          board.setStyle("파티");
          break loop;
        case "3":
          board.setStyle("데이트");
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }


  private void inputBrand(Styling board) {
    loop: while (true) {
      String menuNo = this.prompt.inputString(
          "좋아하는 브랜드:\n" + "  1. 구찌\n" + "  2. 샤넬\n" + "  3. 프라다\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          board.setBrand("구찌");
          break loop;
        case "2":
          board.setBrand("샤넬");
          break loop;
        case "3":
          board.setBrand("프라다");
          break loop;
        case "4":
          board.setBrand(inputOtherBrand());
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private String inputOtherBrand() {
    return this.prompt.inputString("좋아하는 브랜드를 입력하세요: ");
  }

  private void inputFit(Styling board) {
    loop: while (true) {
      String menuNo = this.prompt.inputString(
          "좋아하는 브랜드:\n" + "  1. 오버핏\n" + "  2. 슬림핏\n" + "  3. 스탠다드핏\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          board.setFit("오버 핏");
          break loop;
        case "2":
          board.setFit("슬림 핏");
          break loop;
        case "3":
          board.setFit("스텐다드 핏");
          break loop;
        case "4":
          board.setFit(inputOtherFit());
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private String inputOtherFit() {
    return this.prompt.inputString("선호하는 핏을 입력하세요: ");
  }


  public void deleteStyling() {
    if (!this.list.remove(new Styling(this.prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }

  private Styling findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Styling sty = (Styling) this.list.get(i);
      if (sty.getNo() == no) {
        return sty;
      }
    }
    return null;
  }
}
