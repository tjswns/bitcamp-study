package bitcamp.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;

public class StylingAddListener implements StylingActionListener {
  int category;
  StylingDao stylingDao;
  SqlSessionFactory sqlSessionFactory;

  public StylingAddListener(int category, StylingDao stylingDao,
      SqlSessionFactory sqlSessionFactory) {
    this.category = category;
    this.stylingDao = stylingDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    Styling styling = new Styling();
    StylingActionListener.inputStyle(styling, prompt);
    StylingActionListener.inputBrand(styling, prompt);
    StylingActionListener.inputFit(styling, prompt);
    styling.setWriter((Member) prompt.getAttribute("loginUser"));
    styling.setCategory(category);

    try {
      stylingDao.insert(styling);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
