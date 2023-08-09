package bitcamp.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/styling/delete")
public class StylingDeleteListener implements StylingActionListener {

  StylingDao stylingDao;
  SqlSessionFactory sqlSessionFactory;

  public StylingDeleteListener(StylingDao stylingDao, SqlSessionFactory sqlSessionFactory) {

    this.stylingDao = stylingDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {

    Styling sty = new Styling();
    sty.setNo(prompt.inputInt("번호? "));
    sty.setWriter((Member) prompt.getAttribute("loginUser"));
    sty.setCategory(Integer.parseInt((String) prompt.getAttribute("category")));

    try {
      if (stylingDao.delete(sty) == 0) {
        prompt.println("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        prompt.println("삭제했습니다.");
      }
      sqlSessionFactory.openSession(false).commit();
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
