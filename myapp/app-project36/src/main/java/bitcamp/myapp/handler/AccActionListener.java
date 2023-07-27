package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Acc;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public interface AccActionListener extends ActionListener {


  static void inputStyle(Acc acc, BreadcrumbPrompt prompt) {
    loop: while (true) {
      String menuNo = prompt.inputString(
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

  static void inputSelect(Acc acc, BreadcrumbPrompt prompt) {
    loop: while (true) {
      String menuNo = prompt.inputString(
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
          acc.setSelect(inputOtherBrand(prompt));
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  static String inputOtherBrand(BreadcrumbPrompt prompt) {
    return prompt.inputString("좋아하는 악세사리를 입력하세요: ");
  }

  static void inputSize(Acc acc, BreadcrumbPrompt prompt) {
    loop: while (true) {
      String menuNo = prompt.inputString(
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
          acc.setSize(inputOtherSize(prompt));
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  static String inputOtherSize(BreadcrumbPrompt prompt) {
    return prompt.inputString("자신의 사이즈를 입력하세요: ");
  }

}
