package bitcamp.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/acc/update")
public class AccUpdateListener implements AccActionListener {

  AccDao accDao;
  SqlSessionFactory sqlSessionFactory;

  public AccUpdateListener(AccDao accDao, SqlSessionFactory sqlSessionFactory) {
    this.accDao = accDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int accNo = prompt.inputInt("번호? ");

    Acc acc = accDao.findBy(Integer.parseInt((String) prompt.getAttribute("category")), accNo);
    if (acc == null) {
      prompt.println("해당 번호의 스타일이 없습니다!");
      return;
    }
    AccActionListener.inputStyle(acc, prompt);
    AccActionListener.inputChoose(acc, prompt);
    AccActionListener.inputSize(acc, prompt);
    acc.setWriter((Member) prompt.getAttribute("loginUser"));

    try {
      if (accDao.update(acc) == 0) {
        prompt.println("게시글 변경 권한이 없습니다.");
      } else {
        prompt.println("변경했습니다!");
      }
      sqlSessionFactory.openSession(false).commit();
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
