package bitcamp.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class AccAddListener implements AccActionListener {

  int category;
  AccDao accDao;
  SqlSessionFactory sqlSessionFactory;

  public AccAddListener(int category, AccDao accDao, SqlSessionFactory sqlSessionFactory) {
    this.accDao = accDao;
    this.category = category;
    this.sqlSessionFactory = sqlSessionFactory;
  }


  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Acc acc = new Acc();
    AccActionListener.inputStyle(acc, prompt);
    AccActionListener.inputChoose(acc, prompt);
    AccActionListener.inputSize(acc, prompt);
    acc.setWriter((Member) prompt.getAttribute("loginUser"));
    acc.setCategory(category);

    try {
      accDao.insert(acc);


      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
