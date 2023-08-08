package bitcamp.myapp.handler;

import java.io.IOException;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;

public class AccUpdateListener implements AccActionListener {

  AccDao accDao;

  public AccUpdateListener(AccDao accDao) {
    this.accDao = accDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    int accNo = prompt.inputInt("번호? ");
    Acc acc = accDao.findBy(accNo);
    if (acc == null) {
      System.out.println("해당 번호의 스타일이 없습니다!");
      return;
    }
    AccActionListener.inputStyle(acc, prompt);
    AccActionListener.inputChoose(acc, prompt);
    AccActionListener.inputSize(acc, prompt);
    acc.setWriter((Member) prompt.getAttribute("loginUser"));


    if (accDao.update(acc) == 0) {
      prompt.println("게시글 변경 권한이 없습니다.");
    } else {
      prompt.println("변경했습니다!");
    }
  }

}
