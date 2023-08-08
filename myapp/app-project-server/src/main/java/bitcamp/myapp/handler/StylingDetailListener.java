package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingDetailListener implements StylingActionListener {

  StylingDao stylingDao;

  public StylingDetailListener(StylingDao stylingDao) {
    this.stylingDao = stylingDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int stylingNo = prompt.inputInt("번호? ");

    Styling styling = stylingDao.findBy(stylingNo);
    if (styling == null) {
      prompt.println("해당 번호의 스타일이 없습니다!");
      return;
    }
    prompt.printf("스타일: %s\n", styling.getStyle());
    prompt.printf("브랜드: %s\n", styling.getBrand());
    prompt.printf("핏: %s\n", styling.getFit());
    prompt.printf("조회수: %s\n", styling.getViewCount());
    prompt.printf("등록일: %tY-%1$tm-%1$td\n", styling.getCreatedDate());
    styling.setViewCount(styling.getViewCount() + 1);
    stylingDao.update(styling);
  }
}
