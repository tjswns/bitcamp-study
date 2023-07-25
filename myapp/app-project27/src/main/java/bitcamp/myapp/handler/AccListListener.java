package bitcamp.myapp.handler;

import java.util.Iterator;
import java.util.List;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;

public class AccListListener extends AbstractAccListener {

  public AccListListener(List<Acc> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("------------------------------------------------");
    System.out.println("번호, 악세서리, 스타일, 사이즈, 조회수, 등록일");
    System.out.println("------------------------------------------------");

    Iterator<Acc> iterator = list.iterator();

    while (iterator.hasNext()) {
      Acc acc = iterator.next();
      System.out.printf("%d, %s, %s, %s, %d, %tY-%5$tm-%5$td \n", acc.getNo(), acc.getSelect(),
          acc.getStyle(), acc.getSize(), acc.getViewCount(), acc.getCreatedDate());
    }
  }
}
