/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.79
 * Generated at: 2023-08-25 07:35:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.board;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import bitcamp.myapp.vo.Board;

public final class delete_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = new java.util.HashSet<>();
    _jspx_imports_classes.add("bitcamp.myapp.vo.Board");
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPs only permit GET, POST or HEAD. Jasper also permits OPTIONS");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			"/error.jsp", true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      bitcamp.myapp.dao.BoardDao boardDao = null;
      synchronized (application) {
        boardDao = (bitcamp.myapp.dao.BoardDao) _jspx_page_context.getAttribute("boardDao", javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        if (boardDao == null){
          throw new java.lang.InstantiationException("bean boardDao not found within scope");
        }
      }
      org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory = null;
      synchronized (application) {
        sqlSessionFactory = (org.apache.ibatis.session.SqlSessionFactory) _jspx_page_context.getAttribute("sqlSessionFactory", javax.servlet.jsp.PageContext.APPLICATION_SCOPE);
        if (sqlSessionFactory == null){
          throw new java.lang.InstantiationException("bean sqlSessionFactory not found within scope");
        }
      }
      bitcamp.myapp.vo.Member loginUser = null;
      synchronized (session) {
        loginUser = (bitcamp.myapp.vo.Member) _jspx_page_context.getAttribute("loginUser", javax.servlet.jsp.PageContext.SESSION_SCOPE);
        if (loginUser == null){
          loginUser = new bitcamp.myapp.vo.Member();
          _jspx_page_context.setAttribute("loginUser", loginUser, javax.servlet.jsp.PageContext.SESSION_SCOPE);
        }
      }


    if (loginUser.getNo() == 0) {
        response.sendRedirect("/auth/form.jsp");
        return;
    }
    request.setAttribute("refresh", "2;url=list.jsp?category=" + request.getParameter("category"));

int category = Integer.parseInt(request.getParameter("category"));

Board b = new Board();
b.setNo(Integer.parseInt(request.getParameter("no")));
b.setWriter(loginUser);
b.setCategory(category);

boardDao.deleteFiles(b.getNo());

if (boardDao.delete(b) == 0) {
throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
} else {
sqlSessionFactory.openSession(false).commit();
response.sendRedirect("list.jsp?category=" + request.getParameter("category"));
}



    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
