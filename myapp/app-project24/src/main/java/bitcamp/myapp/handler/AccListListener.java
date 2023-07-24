package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class AccListListener extends AbstractAccListener {

  public AccListListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 악세서리, 스타일, 사이즈");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Acc acc = (Acc) this.list.get(i);
      System.out.printf("%d, %s, %s, %s \n", acc.getNo(), acc.getSelect(), acc.getStyle(),
          acc.getSize());
    }
  }
}