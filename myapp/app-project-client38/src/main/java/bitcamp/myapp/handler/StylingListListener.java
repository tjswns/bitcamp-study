package bitcamp.myapp.handler;

import java.util.List;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingListListener implements StylingActionListener {

  StylingDao stylingDao;

  public StylingListListener(StylingDao stylingDao) {
    this.stylingDao = stylingDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 스타일, 브랜드, 핏, 조회수, 등록일");
    System.out.println("---------------------------------------");

    List<Styling> list = stylingDao.list();

    for (Styling styling : list) {
      System.out.printf("%d, %s, %s, %s, %d, %tY-%6$tm-%6$td \n", styling.getNo(),
          styling.getStyle(), styling.getBrand(), styling.getFit(), styling.getViewCount(),
          styling.getCreatedDate());
    }
  }
}
