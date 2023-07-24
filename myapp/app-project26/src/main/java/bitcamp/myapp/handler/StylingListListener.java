package bitcamp.myapp.handler;

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
    System.out.println("번호, 스타일, 브랜드, 핏");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Styling styling = this.list.get(i);
      System.out.printf("%d, %s, %s, %s \n", styling.getNo(), styling.getStyle(),
          styling.getBrand(), styling.getFit());
    }
  }
}
