package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingUpdateListener extends AbstractStylingListener {

  public StylingUpdateListener(List<Styling> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int stylingNo = prompt.inputInt("번호? ");

    Styling styling = this.findBy(stylingNo);
    if (styling == null) {
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    if (!prompt.inputString("암호? ").equals(styling.getPassword())) {
      System.out.println("암호가 일치하지 않습니다!");
      return;
    }
    inputStyle(styling, prompt);
    inputBrand(styling, prompt);
    inputFit(styling, prompt);


  }
}
