/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-05-14 08:37:13 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.manager.catelogtype;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class Add_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("<title>Insert title here</title>\r\n");
      out.write("<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery1.42.min.js\" ></script>\r\n");
      out.write("\t\t<style type=\"text/css\">\r\n");
      out.write("\t\t\t.dataSerizeals table{\r\n");
      out.write("\t\t\t\twidth: 800px;\r\n");
      out.write("\t\t\t\tmargin-bottom: 20px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.dataSerizeals table tr{\r\n");
      out.write("\t\t\t\tmargin-bottom: 20px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.dataSerizeals table tr td select{\r\n");
      out.write("\t\t\t\twidth: 173px;\r\n");
      out.write("\t\t\t\tpadding: 2px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t.buttons button{\r\n");
      out.write("\t\t\t\tpadding: 5px 10px;\r\n");
      out.write("\t\t\t\tbackground-color: cornflowerblue;\r\n");
      out.write("\t\t\t\tcolor: white;\r\n");
      out.write("\t\t\t\tborder-radius: 4px;\r\n");
      out.write("\t\t\t\tmargin-left: 54px;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<form action=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/manager/catelogtype/add\" method=\"post\">\r\n");
      out.write("\t\t<div class=\"dataSerizeals\">\r\n");
      out.write("\t\t\t<table>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>内容类型名称:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"catelog_type_name\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${catelogeType.catelog_type_name}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>排序:</td>\r\n");
      out.write("\t\t\t\t\t<td><input type=\"text\" name=\"sort\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${catelogeType.sort}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"parent_id\" />\r\n");
      out.write("\t\t\t\t\t<td>父内容类型权限:</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<select id=\"parent_name\" name=\"parent_name\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"0\">无</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\t\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t<div class=\"buttons\">\r\n");
      out.write("\t\t\t<button class=\"close\">取消</button>\r\n");
      out.write("\t\t\t<button class=\"ok\">确认</button>\r\n");
      out.write("\t\t</div>\r\n");
      out.write("\t</form>\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t$(function(){\r\n");
      out.write("\t\t\tvar dialog = frameElement.dialog;\r\n");
      out.write("\t\t\t$.post(\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/manager/catelogtype/all\",null,function(data){\r\n");
      out.write("\t\t\t\t\tif(data != null && data.length >0){\r\n");
      out.write("\t\t\t\t\t\tfor(var i=0;i<data.length;i++){\r\n");
      out.write("\t\t\t\t\t\t\tvar $node=\"<option value=\"+data[i].catelog_type_id+\">\"+data[i].catelog_type_name+\"</option>\";\r\n");
      out.write("\t\t\t\t\t\t\t$(\".dataSerizeals table tr td select\").append($node);\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\t\t\t},\"json\");\r\n");
      out.write("\t\t\t$(\".ok\").click(function(){\r\n");
      out.write("\t\t\t\tvar selected= $(\".dataSerizeals table tr td select\").find(\"option:selected\").text();\r\n");
      out.write("\t\t\t\t$(\"table tr:eq(4) input[type='hidden']\").val(selected);\r\n");
      out.write("\t\t\t\t$(\"form:last\").submit();\t\r\n");
      out.write("\t\t\t\t//parent.window.location.reload(true);//刷新当前框架\r\n");
      out.write("\t\t\t\tparent.parent.window.location.reload(true);//刷新主框架\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\t$(\".close\").click(function(){\r\n");
      out.write("\t\t\t\tdialog.close()\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t})\r\n");
      out.write("\t</script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
