package bitcamp.myapp.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingListListener implements StylingActionListener {

  StylingDao stylingDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public StylingListListener(StylingDao stylingDao) {
    this.stylingDao = stylingDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    prompt.println("---------------------------------------");
    prompt.println("번호, 스타일, 브랜드, 핏, 조회수, 등록일");
    prompt.println("---------------------------------------");

    List<Styling> list = stylingDao.list();

    for (Styling styling : list) {
      prompt.printf("%d, %s, %s, %s, %s, %d, %s\n", styling.getNo(), styling.getStyle(),
          styling.getBrand(), styling.getFit(), styling.getWriter().getName(),
          styling.getViewCount(), styling.getCreatedDate());
    }
  }
}
