/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.62
 * Generated at: 2022-08-27 05:39:54 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(2);
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1654240298871L));
    _jspx_dependants.put("jar:file:/C:/6_Framework_Spring_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/2_SpringAjax/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
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
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("	<title>Home</title>\n");
      out.write("	<script type=\"text/javascript\" src=\"https://code.jquery.com/jquery-3.6.0.min.js\"></script>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("	<h1 align=\"center\">Spring에서의 aJax 사용</h1>\n");
      out.write("	\n");
      out.write("	<ol>\n");
      out.write("		<li>\n");
      out.write("			서버 쪽 컨트롤러로 값 보내기\n");
      out.write("			<button id=\"test1\">테스트</button>\n");
      out.write("			<script>\n");
      out.write("				$('#test1').on('click', function(){\n");
      out.write("					$.ajax({\n");
      out.write("						url: 'test1.do',\n");
      out.write("						data: {name: '강건강', age:20},\n");
      out.write("						type: 'post',\n");
      out.write("						success: function(data){\n");
      out.write("							console.log(data);\n");
      out.write("							if(data == 'ok'){\n");
      out.write("								alert('전송성공');\n");
      out.write("							} else{\n");
      out.write("								alert('전송실패');\n");
      out.write("							}\n");
      out.write("						},\n");
      out.write("						error: function(data){\n");
      out.write("							console.log(data);\n");
      out.write("						}\n");
      out.write("					});\n");
      out.write("				});\n");
      out.write("			</script>\n");
      out.write("		</li>\n");
      out.write("		<li>\n");
      out.write("			컨트롤러에서 리턴하는 JSON객체 받아 출력하기\n");
      out.write("			<button id=\"test2\">테스트</button>\n");
      out.write("			<div id=\"d2\"></div>\n");
      out.write("			<script>\n");
      out.write("				$('#test2').click(function(){\n");
      out.write("					$.ajax({\n");
      out.write("						url: 'test2.do',\n");
      out.write("// 						dataType: 'json', //data = JSON.parse(data); 두가지 인코딩 방법!\n");
      out.write("						success: function(data){\n");
      out.write("// 							data = JSON.parse(data);\n");
      out.write("// 							console.log(data);\n");
      out.write("// 							//String으로 들어가는 곳에 디코딩해줘야 한다. 띄어쓰기에 +가 들어간다 : url방식으로 +가 들어가서 이걸 띄어쓰기로 바꿔줘야한다.모든 + 를 띄어쓰기로 변경 replaceAll()\n");
      out.write("// 							$('#d2').html('번호: ' + data.no\n");
      out.write("// 										+ '<br>제목 : ' + data.title	\n");
      out.write("// 										+ '<br>작성자 : ' + decodeURIComponent(data.writer)\n");
      out.write("// 										+ '<br>내용 : ' + decodeURIComponent(data.content.replaceAll('+', ' ')));\n");
      out.write("							console.log(data);\n");
      out.write("							$('#d2').html('번호: ' + data.no\n");
      out.write("										+ '<br>제목 : ' + data.title	\n");
      out.write("										+ '<br>작성자 : ' + data.writer\n");
      out.write("										+ '<br>내용 : ' + data.content);\n");
      out.write("						},\n");
      out.write("						error: function(data){\n");
      out.write("							console.log(data);\n");
      out.write("						}\n");
      out.write("					});\n");
      out.write("				});\n");
      out.write("			</script>\n");
      out.write("			\n");
      out.write("			\n");
      out.write("			\n");
      out.write("			\n");
      out.write("			\n");
      out.write("		</li>\n");
      out.write("	</ol>\n");
      out.write("	\n");
      out.write("</body>\n");
      out.write("</html>\n");
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
