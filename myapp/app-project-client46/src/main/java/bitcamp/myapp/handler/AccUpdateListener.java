package bitcamp.myapp.handler;

import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.BreadcrumbPrompt;

public class AccUpdateListener implements AccActionListener {

  AccDao accDao;

  public AccUpdateListener(AccDao accDao) {
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
    AccActionListener.inputSelect(acc, prompt);
    AccActionListener.inputStyle(acc, prompt);
    AccActionListener.inputSize(acc, prompt);

    accDao.update(acc);
  }

}
