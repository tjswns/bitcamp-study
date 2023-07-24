package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class AccAddListener extends AbstractAccListener {

  public AccAddListener(List<Acc> list) {
    super(list);
  }


  @Override
  public void service(BreadcrumbPrompt prompt) {
    Acc acc = new Acc();
    inputSelect(acc, prompt);
    inputStyle(acc, prompt);
    inputSize(acc, prompt);

    this.list.add(acc);
  }
}
