package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class AccDetailListener implements AccActionListener {

  AccDao accDao;
  DataSource ds;

  public AccDetailListener(AccDao accDao, DataSource ds) {
    this.accDao = accDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int accNo = prompt.inputInt("번호? ");

    Acc acc = accDao.findBy(accNo);
    if (acc == null) {
      System.out.println("해당 번호의 스타일이 없습니다!");
      return;
    }

    prompt.printf("스타일: %s\n", acc.getStyle());
    prompt.printf("악세사리: %s\n", acc.getChoose());
    prompt.printf("사이즈: %s\n", acc.getSize());
    prompt.printf("조회수: %s\n", acc.getViewCount());
    prompt.printf("등록일: %tY-%1$tm-%1$td\n", acc.getCreatedDate());
    acc.setViewCount(acc.getViewCount() + 1);
    try {
      accDao.update(acc);
      ds.getConnection().commit();

    } catch (Exception e) {
      try {
        ds.getConnection().rollback();
      } catch (Exception e2) {
      }
      throw new RuntimeException(e);
    }
  }
}
