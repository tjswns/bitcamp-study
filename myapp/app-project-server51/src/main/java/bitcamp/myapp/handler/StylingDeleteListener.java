package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.DataSource;

public class StylingDeleteListener implements StylingActionListener {

  StylingDao stylingDao;
  DataSource ds;

  public StylingDeleteListener(StylingDao stylingDao, DataSource ds) {
    this.stylingDao = stylingDao;
    this.ds = ds;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {

    Styling sty = new Styling();
    sty.setNo(prompt.inputInt("번호? "));
    sty.setWriter((Member) prompt.getAttribute("loginUser"));

    try {
      if (stylingDao.delete(sty) == 0) {
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
