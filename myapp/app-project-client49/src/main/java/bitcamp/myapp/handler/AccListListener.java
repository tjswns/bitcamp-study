package bitcamp.myapp.handler;

import java.text.SimpleDateFormat;
import java.util.List;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;

public class AccListListener implements AccActionListener {

  AccDao accDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public AccListListener(AccDao accDao) {
    this.accDao = accDao;

  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("------------------------------------------------");
    System.out.println("번호, 악세서리, 스타일, 사이즈, 조회수, 등록일");
    System.out.println("------------------------------------------------");

    List<Acc> list = accDao.list();

    for (Acc acc : list) {
      System.out.printf("%d, %s, %s, %s, %s, %d, %s\n", acc.getNo(), acc.getStyle(),
          acc.getChoose(), acc.getSize(), acc.getWriter().getName(), acc.getViewCount(),
          dateFormatter.format(acc.getCreatedDate()));
    }
  }
}