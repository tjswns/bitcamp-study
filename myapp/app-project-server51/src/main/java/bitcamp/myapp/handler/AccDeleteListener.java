package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class AccDeleteListener implements AccActionListener {

  AccDao accDao;
  DataSource ds;

  public AccDeleteListener(AccDao accDao, DataSource ds) {
    this.accDao = accDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {

    Acc acc = new Acc();
    acc.setNo(prompt.inputInt("번호?"));
    acc.setWriter((Member) prompt.getAttribute("loginUser"));

    try {
      if (accDao.delete(acc) == 0) {
        prompt.println("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        prompt.println("삭제했습니다.");
      }
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
