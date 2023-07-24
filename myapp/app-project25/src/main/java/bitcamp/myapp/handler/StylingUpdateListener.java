package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

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
    inputStyle(styling, prompt);
    inputBrand(styling, prompt);
    inputFit(styling, prompt);


  }
}
