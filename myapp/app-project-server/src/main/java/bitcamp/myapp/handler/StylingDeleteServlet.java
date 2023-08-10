package bitcamp.myapp.handler;

import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Member;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/styling/delete")
public class StylingDeleteServlet implements Servlet {

  StylingDao stylingDao;
  SqlSessionFactory sqlSessionFactory;

  public StylingDeleteServlet(StylingDao stylingDao, SqlSessionFactory sqlSessionFactory) {

    this.stylingDao = stylingDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    int category = Integer.parseInt(request.getParameter("category"));

    Styling sty = new Styling();
    sty.setNo(Integer.parseInt(request.getParameter("no")));
    sty.setWriter(loginUser);
    sty.setCategory(category);

    try {
      if (stylingDao.delete(sty) == 0) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        response.sendRedirect("/styling/list?category=" + category);
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
