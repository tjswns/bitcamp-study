package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class StylingDetailListener extends AbstractStylingListener {

  public StylingDetailListener(List<Styling> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int stylingNo = prompt.inputInt("번호? ");

    Styling styling = this.findBy(stylingNo);
    if (styling == null) {
      System.out.println("해당 번호의 스타일이 없습니다!");
      return;
    }
    System.out.printf("스타일: %s\n", styling.getStyle());
    System.out.printf("브랜드: %s\n", styling.getBrand());
    System.out.printf("핏: %s\n", styling.getFit());
    return;
  }
}
