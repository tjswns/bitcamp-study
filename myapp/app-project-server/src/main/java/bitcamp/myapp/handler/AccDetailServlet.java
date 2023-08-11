package bitcamp.myapp.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.AccDao;
import bitcamp.myapp.vo.Acc;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/acc/detail")
public class AccDetailServlet implements Servlet {

  AccDao accDao;
  SqlSessionFactory sqlSessionFactory;

  public AccDetailServlet(AccDao accDao, SqlSessionFactory sqlSessionFactory) {
    this.accDao = accDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    Acc acc = accDao.findBy(Integer.parseInt(request.getParameter("category")),
        Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>악세사리</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>악세사리</h1>");

    if (acc == null) {
      out.println("<p>해당 번호의 게시글이 없습니다!</p>");
    } else {
      out.println("<form action='/acc/update' method='post'>");
      out.printf("<input type='hidden' name='category' value='%d'>\n", acc.getCategory());
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          acc.getNo());
      out.printf(
          "<tr><th>스타일</th>" + " <td><input type='text' name='title' value='%s'></td></tr>\n",
          acc.getStyle());
      out.printf("<tr><th>악세사리</th>"
          + " <td><textarea name='content' style='height:200px; width:400px;'>%s</textarea></td></tr>\n",
          acc.getChoose());
      out.printf("<tr><th>사이즈</th>"
          + " <td><textarea name='content' style='height:200px; width:400px;'>%s</textarea></td></tr>\n",
          acc.getSize());
      out.printf("<tr><th>작성자</th> <td>%s</td></tr>\n", acc.getWriter().getName());
      out.printf("<tr><th>조회수</th> <td>%s</td></tr>\n", acc.getViewCount());
      out.printf("<tr><th>등록일</th> <td>%tY-%1$tm-%1$td</td></tr>\n", acc.getCreatedDate());
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/board/delete?category=%d&no=%d'>삭제</a>\n", acc.getCategory(),
          acc.getNo());
      out.printf("<a href='/board/list?category=%d'>목록</a>\n", acc.getCategory());
      out.println("</div>");
      out.println("</form>");
      try {
        acc.setViewCount(acc.getViewCount() + 1);
        accDao.updateCount(acc);
        sqlSessionFactory.openSession(false).commit();

      } catch (Exception e) {
        sqlSessionFactory.openSession(false).rollback();
      }
    }

    out.println("</body>");
    out.println("</html>");
  }
}