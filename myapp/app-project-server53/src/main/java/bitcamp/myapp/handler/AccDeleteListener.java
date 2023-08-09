package bitcamp.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class AccDeleteListener implements AccActionListener {

  int category;
  AccDao accDao;
  SqlSessionFactory sqlSessionFactory;

  public AccDeleteListener(int category, AccDao accDao, SqlSessionFactory sqlSessionFactory) {
    this.category = category;
    this.accDao = accDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {

    Acc acc = new Acc();
    acc.setNo(prompt.inputInt("번호?"));
    acc.setWriter((Member) prompt.getAttribute("loginUser"));
    acc.setCategory(category);
    try {
      if (accDao.delete(acc) == 0) {
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
