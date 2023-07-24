package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class StylingAddListener extends AbstractStylingListener {

  public StylingAddListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Styling styling = new Styling();
    inputStyle(styling, prompt);
    inputBrand(styling, prompt);
    inputFit(styling, prompt);

    this.list.add(styling);
  }
}
