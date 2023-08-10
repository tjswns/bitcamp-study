package bitcamp.myapp.handler;

import java.io.IOException;
import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/styling/detail")
public class StylingDetailServlet implements Servlet {

  StylingDao stylingDao;
  SqlSessionFactory sqlSessionFactory;

  public StylingDetailServlet(StylingDao stylingDao, SqlSessionFactory sqlSessionFactory) {
    this.stylingDao = stylingDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {

    Styling styling = stylingDao.findBy(Integer.parseInt(request.getParameter("category")),
        Integer.parseInt(request.getParameter("no")));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>스타일</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>스타일</h1>");

    if (styling == null) {
      out.println("해당 번호의 스타일이 없습니다!");
    } else {
      out.println("<form action='/board/update' method='post'>");
      out.printf("<input type='hidden' name='category' value='%d'>\n", styling.getCategory());
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>번호</th>"
          + " <td style='width:300px;'><input type='text' name='no' value='%d' readonly></td></tr>\n",
          styling.getNo());
      out.printf(
          "<tr><th>스타일</th>" + " <td><input type='text' name='title' value='%s'></td></tr>\n",
          styling.getStyle());
      out.printf("<tr><th>브랜드</th>"
          + " <td><textarea name='content' style='height:200px; width:400px;'>%s</textarea></td></tr>\n",
          styling.getBrand());
      out.printf("<tr><th>핏</th>"
          + " <td><textarea name='content' style='height:200px; width:400px;'>%s</textarea></td></tr>\n",
          styling.getFit());
      out.printf("<tr><th>작성자</th> <td>%s</td></tr>\n", styling.getWriter().getName());
      out.printf("<tr><th>조회수</th> <td>%s</td></tr>\n", styling.getViewCount());
      out.printf("<tr><th>등록일</th> <td>%tY-%1$tm-%1$td</td></tr>\n", styling.getCreatedDate());
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/board/delete?category=%d&no=%d'>삭제</a>\n", styling.getCategory(),
          styling.getNo());
      out.printf("<a href='/board/list?category=%d'>목록</a>\n", styling.getCategory());
      out.println("</div>");
      out.println("</form>");
      try {
        styling.setViewCount(styling.getViewCount() + 1);
        stylingDao.updateCount(styling);
        sqlSessionFactory.openSession(false).commit();

      } catch (Exception e) {
        sqlSessionFactory.openSession(false).rollback();
      }
    }

    out.println("</body>");
    out.println("</html>");

  }
}
