package bitcamp.myapp.handler;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import bitcamp.myapp.dao.StylingDao;
import bitcamp.myapp.vo.Styling;
import bitcamp.util.Component;
import bitcamp.util.HttpServletRequest;
import bitcamp.util.HttpServletResponse;
import bitcamp.util.Servlet;

@Component("/styling/list")
public class StylingListServlet implements Servlet {

  StylingDao stylingDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public StylingListServlet(StylingDao stylingDao) {
    this.stylingDao = stylingDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {

    int category = Integer.parseInt(request.getParameter("category"));

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>게시글</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>게시글 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.printf("<a href='/styling/form?category=%d'>새 글</a>\n", category);
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록일</th></tr>");
    out.println("</thead>");

    List<Styling> list = stylingDao.findAll(category);

    out.println("<tbody>");
    for (Styling styling : list) {
      out.printf(
          "<tr>" + " <td>%d</td>" + " <td><a href='/styling/detail?category=%d&no=%d'>%s</a></td>"
              + " <td>%s</td>" + " <td>%d</td>" + " <td>%s</td></tr>\n",
          styling.getNo(), styling.getCategory(), styling.getNo(),
          (styling.getStyle().length() > 0 ? styling.getStyle() : "제목없음"),
          styling.getWriter().getName(), styling.getViewCount(),
          dateFormatter.format(styling.getCreatedDate()));
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("</body>");
    out.println("</html>");
  }

}