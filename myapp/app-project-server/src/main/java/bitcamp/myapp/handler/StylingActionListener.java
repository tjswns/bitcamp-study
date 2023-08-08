package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.ActionListener;
import bitcamp.util.BreadcrumbPrompt;

public interface StylingActionListener extends ActionListener {


  static void inputStyle(Styling styling, BreadcrumbPrompt prompt) throws IOException {
    loop: while (true) {
      String menuNo =
          prompt.inputString("좋아하는 스타일:\n" + "  1. 데일리\n" + "  2. 파티\n" + "  3. 데이트\n" + "> ");

      switch (menuNo) {
        case "1":
          styling.setStyle("데일리");
          break loop;
        case "2":
          styling.setStyle("파티");
          break loop;
        case "3":
          styling.setStyle("데이트");
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }


  static void inputBrand(Styling styling, BreadcrumbPrompt prompt) throws IOException {
    loop: while (true) {
      String menuNo = prompt.inputString(
          "좋아하는 브랜드:\n" + "  1. 구찌\n" + "  2. 샤넬\n" + "  3. 프라다\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          styling.setBrand("구찌");
          break loop;
        case "2":
          styling.setBrand("샤넬");
          break loop;
        case "3":
          styling.setBrand("프라다");
          break loop;
        case "4":
          styling.setBrand(inputOtherBrand(prompt));
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  static String inputOtherBrand(BreadcrumbPrompt prompt) throws IOException {
    return prompt.inputString("좋아하는 브랜드를 입력하세요: ");
  }

  static void inputFit(Styling styling, BreadcrumbPrompt prompt) throws IOException {
    loop: while (true) {
      String menuNo = prompt.inputString(
          "좋아하는 브랜드:\n" + "  1. 오버핏\n" + "  2. 슬림핏\n" + "  3. 스탠다드핏\n" + "  4. 그 외\n" + "> ");

      switch (menuNo) {
        case "1":
          styling.setFit("오버 핏");
          break loop;
        case "2":
          styling.setFit("슬림 핏");
          break loop;
        case "3":
          styling.setFit("스텐다드 핏");
          break loop;
        case "4":
          styling.setFit(inputOtherFit(prompt));
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  static String inputOtherFit(BreadcrumbPrompt prompt) throws IOException {
    return prompt.inputString("선호하는 핏을 입력하세요: ");
  }

}
