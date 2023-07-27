package bitcamp.myapp.handler;

import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;

public class AccDetailListener implements AccActionListener {

  AccDao accDao;

  public AccDetailListener(AccDao accDao) {
    this.accDao = accDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    int accNo = prompt.inputInt("번호? ");

    Acc acc = accDao.findBy(accNo);
    if (acc == null) {
      System.out.println("해당 번호의 스타일이 없습니다!");
      return;
    }

    System.out.printf("악세사리: %s\n", acc.getSelect());
    System.out.printf("스타일: %s\n", acc.getStyle());
    System.out.printf("사이즈: %s\n", acc.getSize());
    System.out.printf("조회수: %s\n", acc.getViewCount());
    System.out.printf("등록일: %tY-%1$tm-%1$td\n", acc.getCreatedDate());
    acc.setViewCount(acc.getViewCount() + 1);
  }
}
