package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;

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
    acc.setPassword(prompt.inputString("암호? "));
    this.list.add(acc);
  }
}
