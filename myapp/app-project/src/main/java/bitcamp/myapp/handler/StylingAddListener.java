package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingAddListener extends AbstractStylingListener {

  public StylingAddListener(List<Styling> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Styling styling = new Styling();
    inputStyle(styling, prompt);
    inputBrand(styling, prompt);
    inputFit(styling, prompt);
    styling.setPassword(prompt.inputString("암호? "));
    this.list.add(styling);
  }
}
