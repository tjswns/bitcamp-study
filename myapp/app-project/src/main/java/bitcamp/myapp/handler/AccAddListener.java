package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Acc;

public class AccAddListener extends AbstractAccListener {

  public AccAddListener(List list) {
    super(list);
  }


  @Override
  private void inputAcc() {
    Acc acc = new Acc();
    inputSelect(acc);
    inputStyle(acc);
    inputSize(acc);

    this.list.add(acc);
  }
}
