package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingDeleteListener extends AbstractStylingListener {

  public StylingDeleteListener(List<Styling> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Styling(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 회원이 없습니다!");
    }
  }
}
