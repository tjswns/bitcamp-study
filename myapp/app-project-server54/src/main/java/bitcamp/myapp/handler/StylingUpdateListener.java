package bitcamp.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/styling/update")
public class StylingUpdateListener implements StylingActionListener {

  StylingDao stylingDao;
  SqlSessionFactory sqlSessionFactory;

  public StylingUpdateListener(StylingDao stylingDao, SqlSessionFactory sqlSessionFactory) {
    this.stylingDao = stylingDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int stylingNo = prompt.inputInt("번호? ");

    Styling styling =
        stylingDao.findBy(Integer.parseInt((String) prompt.getAttribute("category")), stylingNo);
    if (styling == null) {
      prompt.println("해당 번호의 회원이 없습니다!");
      return;
    }

    StylingActionListener.inputStyle(styling, prompt);
    StylingActionListener.inputBrand(styling, prompt);
    StylingActionListener.inputFit(styling, prompt);
    styling.setWriter((Member) prompt.getAttribute("loginUser"));

    try {
      if (stylingDao.update(styling) == 0) {
        System.out.println("게시글 변경 권한이 없습니다.");
      } else {
        System.out.println("변경했습니다!");
      }
      sqlSessionFactory.openSession(false).commit();
    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
