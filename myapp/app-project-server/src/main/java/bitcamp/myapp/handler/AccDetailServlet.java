package bitcamp.myapp.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.Component;

@Component("/acc/detail")
public class AccDetailServlet implements AccActionListener {

  AccDao accDao;
  SqlSessionFactory sqlSessionFactory;

  public AccDetailServlet(AccDao accDao, SqlSessionFactory sqlSessionFactory) {
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

    prompt.printf("스타일: %s\n", acc.getStyle());
    prompt.printf("악세사리: %s\n", acc.getChoose());
    prompt.printf("사이즈: %s\n", acc.getSize());
    prompt.printf("조회수: %s\n", acc.getViewCount());
    prompt.printf("등록일: %tY-%1$tm-%1$td\n", acc.getCreatedDate());
    try {
      acc.setViewCount(acc.getViewCount() + 1);
      accDao.updateCount(acc);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
