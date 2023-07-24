package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Acc;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public abstract class AbstractAccListener implements ActionListener {

  protected List<Acc> list;

  public AbstractAccListener(List<Acc> list) {
    this.list = list;
  }

  protected Acc findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Acc b = this.list.get(i);
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }

  protected void inputStyle(Acc acc, BreadcrumbPrompt prompt) {
    while (true) {
      String menuNo = prompt.inputString(
          "좋아하는 스타일:\n" + "  1. 데일리\n" + "  2. 힙\n" + "  3. 스트릿\n" + "  4. 아메카지\n" + "> ");

      switch (menuNo) {
        case "1":
          acc.setStyle("데일리");
          break;
        case "2":
          acc.setStyle("힙");
          break;
        case "3":
          acc.setStyle("스트릿");
          break;
        case "4":
          acc.setStyle("아메카지");
          break;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  protected void inputSelect(Acc acc, BreadcrumbPrompt prompt) {
    while (true) {
      String menuNo = prompt.inputString(
          "좋아하는 악세사리:\n" + "  1. 목걸이\n" + "  2. 반지\n" + "  3. 안경\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          acc.setSelect("목걸이");
          break;
        case "2":
          acc.setSelect("반지");
          break;
        case "3":
          acc.setSelect("안경");
          break;
        case "4":
          acc.setSelect(inputOtherBrand(prompt));
          break;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  protected String inputOtherBrand(BreadcrumbPrompt prompt) {
    return prompt.inputString("좋아하는 악세사리를 입력하세요: ");
  }

  protected void inputSize(Acc acc, BreadcrumbPrompt prompt) {
    while (true) {
      String menuNo = prompt.inputString(
          "좋아하는 사이즈:\n" + "  1. 9호\n" + "  2. 10호\n" + "  3. 11호\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          acc.setSize("9호");
          break;
        case "2":
          acc.setSize("10호");
          break;
        case "3":
          acc.setSize("11호");
          break;
        case "4":
          acc.setSize(inputOtherSize(prompt));
          break;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  private String inputOtherSize(BreadcrumbPrompt prompt) {
    return prompt.inputString("자신의 사이즈를 입력하세요: ");
  }

}
