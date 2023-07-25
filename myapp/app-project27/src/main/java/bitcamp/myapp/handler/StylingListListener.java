package bitcamp.myapp.handler;

import java.util.Iterator;
import java.util.List;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingListListener extends AbstractStylingListener {

  public StylingListListener(List<Styling> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 스타일, 브랜드, 핏, 조회수, 등록일");
    System.out.println("---------------------------------------");

    Iterator<Styling> iterator = list.iterator();

    while (iterator.hasNext()) {
      Styling styling = iterator.next();
      System.out.printf("%d, %s, %s, %s, %d, %tY-%5$tm-%5$td \n", styling.getNo(),
          styling.getStyle(), styling.getBrand(), styling.getFit(), styling.getViewCount(),
          styling.getCreatedDate());
    }
  }
}
