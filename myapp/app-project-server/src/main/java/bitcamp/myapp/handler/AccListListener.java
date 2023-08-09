package bitcamp.myapp.handler;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;

public class AccListListener implements AccActionListener {

  int category;
  AccDao accDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public AccListListener(int category, AccDao accDao) {
    this.category = category;
    this.accDao = accDao;

  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    prompt.println("------------------------------------------------");
    prompt.println("번호, 악세서리, 스타일, 사이즈, 조회수, 등록일");
    prompt.println("------------------------------------------------");

    List<Acc> list = accDao.findAll(category);

    for (Acc acc : list) {
      prompt.printf("%d, %s, %s, %s, %s, %d, %s\n", acc.getNo(), acc.getStyle(), acc.getChoose(),
          acc.getSize(), acc.getWriter().getName(), acc.getViewCount(),
          dateFormatter.format(acc.getCreatedDate()));
    }
  }
}
