/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-05-13 02:16:14 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.jsp.manager.rule;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class list_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n");
      out.write("<head>\r\n");
      out.write("    <title></title>\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/ligerUI/skins/Aqua/css/ligerui-all.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/ligerUI/skins/ligerui-icons.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("    <link href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/ligerUI/skins/Gray/css/all.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/jquery/jquery-1.9.0.min.js\" type=\"text/javascript\"></script> \r\n");
      out.write("    <script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/ligerUI/js/ligerui.all.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("\tvar dialog;\r\n");
      out.write("\t\t//获取多选框所选中的行，并将其id值存放到数组中\r\n");
      out.write("\t\tfunction getSelected(){\r\n");
      out.write("\t\t\tvar $selseted= $(\"table.l-grid-body-table tbody\").find(\"tr.l-selected\");\r\n");
      out.write("\t\t\tvar ids=new Array();\r\n");
      out.write("\t\t\tfor(var i=0;i<$selseted.length;i++){//获取选中的元素，由于复选框和表中的数据是两个不同的表格，故会出现2次\r\n");
      out.write("\t\t\t\tvar $element=  $($selseted[i]);\r\n");
      out.write("\t\t\t\tif($element.attr(\"id\").indexOf(\"|2|\")>0){\r\n");
      out.write("\t\t\t\t\tvar user_id= $element.find(\"td:first div\").text();//获取用户id\r\n");
      out.write("\t\t\t\t\tids.push(user_id);\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\treturn ids;\r\n");
      out.write("\t\t}\r\n");
      out.write("    \r\n");
      out.write("        function itemclick(item)\r\n");
      out.write("        {\r\n");
      out.write("            if(item.text == \"修改\"){\r\n");
      out.write("            \t var seleceted= getSelected();\r\n");
      out.write("\t\t\t\tif(seleceted.length > 1){\r\n");
      out.write("\t\t\t\t\talert(\"你所选的行数大于1!\")\r\n");
      out.write("\t\t\t\t}else if(seleceted.length < 1){\r\n");
      out.write("\t\t\t\t\talert(\"请选择要修改的行！\")\r\n");
      out.write("\t\t\t\t}else{//执行修改操作逻辑\r\n");
      out.write("\t\t\t\t\t$.ligerDialog.open({\r\n");
      out.write("\t\t                   height:600,\r\n");
      out.write("\t\t                   width: 800,\r\n");
      out.write("\t\t                   title : '修改规则',\r\n");
      out.write("\t\t                   url: 'toUpdate?id='+seleceted[0], \r\n");
      out.write("\t\t                   showMax: false,\r\n");
      out.write("\t\t                   showToggle: true,\r\n");
      out.write("\t\t                   showMin: false,\r\n");
      out.write("\t\t                   isResize: true,\r\n");
      out.write("\t\t                   slide: false,\r\n");
      out.write("\t\t                   isHidden:false,\r\n");
      out.write("\t\t                   allowClose:true\r\n");
      out.write("\t\t               }) ;\r\n");
      out.write("\t\t\t\t\t//window.location.reload(true)\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("             }else if(item.text == \"删除\"){\r\n");
      out.write("                //获取要删除数据的id\r\n");
      out.write("\t\t\t\tvar ids=getSelected();\r\n");
      out.write("\t\t\t\tif(ids.length == 0){\r\n");
      out.write("\t\t\t\t\talert(\"请选择要删除的行！\");\r\n");
      out.write("\t\t\t\t\treturn ;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t//向服务端发送要删除的数据\r\n");
      out.write("\t\t\t\tvar contents=\"<form action='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/manager/rule/delete' method='post'>\";\r\n");
      out.write("\t\t\t\tfor(var i=0;i<ids.length;i++){\r\n");
      out.write("\t\t\t\t\t contents+=\t\"<input type='hidden' name='ids' value='\"+ids[i]+\"'/>\"\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\tcontents+=\"</form>\";\r\n");
      out.write("\t\t\t\tvar $content=$(contents);\r\n");
      out.write("\t\t\t\t$(\"body\").append($content);\r\n");
      out.write("\t\t\t\t//提交表单数据\r\n");
      out.write("\t\t\t\t$(\"form:last\").submit();\r\n");
      out.write("             }else{\r\n");
      out.write("           \t\t  dialog= $.ligerDialog.open({\r\n");
      out.write("                   height:600,\r\n");
      out.write("                   width: 800,\r\n");
      out.write("                   title : '增加规则',\r\n");
      out.write("                   url: 'toAdd', \r\n");
      out.write("                   showMax: false,\r\n");
      out.write("                   showToggle: true,\r\n");
      out.write("                   showMin: false,\r\n");
      out.write("                   isResize: true,\r\n");
      out.write("                   slide: false,\r\n");
      out.write("                   isHidden:false,\r\n");
      out.write("                   allowClose:true\r\n");
      out.write("               }) ;\r\n");
      out.write("\r\n");
      out.write("           }\r\n");
      out.write("            \r\n");
      out.write("        \t\r\n");
      out.write("    }\r\n");
      out.write("        $(function ()\r\n");
      out.write("        {\r\n");
      out.write("            var data=");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${rules}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(";\r\n");
      out.write("\r\n");
      out.write("            //时间格式化\r\n");
      out.write("\t\t\tfor(var i=0;i<data.Rows.length;i++){\r\n");
      out.write("\t\t\t  var create_time=\tdata.Rows[i].create_time;\r\n");
      out.write("\t\t\t  var last_login_time=\tdata.Rows[i].last_login_time;\r\n");
      out.write("\t\t\t  var update_time=\tdata.Rows[i].update_time;\r\n");
      out.write("\t\t\t  var date=new Date();\r\n");
      out.write("\t\t\t  date.setTime(create_time);\r\n");
      out.write("\t\t\t  var time= date.getFullYear()+\"-\"+(date.getMonth()+1)+\"-\"+date.getDate()+\" \"+date.getHours()+\":\"+date.getMinutes()+\":\"+date.getSeconds();\r\n");
      out.write("\t\t\t  data.Rows[i].create_time=time;\r\n");
      out.write("\t\t\t  if(last_login_time != null){\r\n");
      out.write("\t\t\t\t  date.setTime(last_login_time);\r\n");
      out.write("\t\t\t\t  time= date.getFullYear()+\"-\"+(date.getMonth()+1)+\"-\"+date.getDate()+\" \"+date.getHours()+\":\"+date.getMinutes()+\":\"+date.getSeconds();\r\n");
      out.write("\t\t\t\t  data.Rows[i].last_login_time=time;\r\n");
      out.write("\t\t\t  }else{\r\n");
      out.write("\t\t\t\t  data.Rows[i].last_login_time=\"\";\r\n");
      out.write("\t\t\t }\r\n");
      out.write("\t\t\t  date.setTime(update_time);\r\n");
      out.write("\t\t\t  time= date.getFullYear()+\"-\"+(date.getMonth()+1)+\"-\"+date.getDate()+\" \"+date.getHours()+\":\"+date.getMinutes()+\":\"+date.getSeconds();\r\n");
      out.write("\t\t\t  data.Rows[i].update_time=time;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("            \r\n");
      out.write("            window['g'] =\r\n");
      out.write("            $(\"#maingrid\").ligerGrid({\r\n");
      out.write("                height: '100%',\r\n");
      out.write("                page :1,\r\n");
      out.write("                pageSize :10,\r\n");
      out.write("                columns: [\r\n");
      out.write("                { display: '规则id', name: 'rule_id', align: 'center', width: 60, minWidth: 60 },\r\n");
      out.write("                { display: '规则名称', name: 'rule_name', minWidth: 50 },\r\n");
      out.write("                { display: '规则描述', name: 'rule_desc', minWidth: 50 },\r\n");
      out.write("                { display: '规则标识符', name: 'rule_sysmbol', minWidth: 50 },\r\n");
      out.write("                { display: '规则控制字段', name: 'rule_control_field', minWidth: 50 },\r\n");
      out.write("                { display: '规则控制内容', name: 'rule_control_content', minWidth: 50 },\r\n");
      out.write("                { display: '创建者', name: 'creater_name', minWidth: 50 },\r\n");
      out.write("                { display: '创建时间', name: 'create_time', minWidth: 50 , type: 'date', format: 'yyyy年MM月dd'},\r\n");
      out.write("                { display: '更新时间', name: 'update_time', minWidth: 50 },\r\n");
      out.write("                ], data: data, rownumbers: true,\r\n");
      out.write("                toolbar: {\r\n");
      out.write("                    items: [\r\n");
      out.write("                    { text: '增加', click: itemclick, icon: 'add' },\r\n");
      out.write("                    { line: true },\r\n");
      out.write("                    { text: '修改', click: itemclick, icon: 'modify' },\r\n");
      out.write("                    { line: true },\r\n");
      out.write("                    { text: '删除', click: itemclick, img: '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${pageContext.request.contextPath}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("/js/ligerUI/skins/icons/delete.gif' }\r\n");
      out.write("                    ]\r\n");
      out.write("                },\r\n");
      out.write("                autoFilter: true,\r\n");
      out.write("                checkbox :true,\r\n");
      out.write("                onReload :function(){\r\n");
      out.write("                \twindow.location.reload(true); //刷新事件\r\n");
      out.write("                }\r\n");
      out.write("                \r\n");
      out.write("            });\r\n");
      out.write("\t             \r\n");
      out.write("\r\n");
      out.write("            $(\"#pageloading\").hide();\r\n");
      out.write("            \r\n");
      out.write("        });\r\n");
      out.write("\t\tfunction page(element){\r\n");
      out.write("\t\t\tconsole.log(element)\r\n");
      out.write("\t\t}\r\n");
      out.write("        function deleteRow()\r\n");
      out.write("        {\r\n");
      out.write("            g.deleteSelectedRow();\r\n");
      out.write("        }\r\n");
      out.write("\r\n");
      out.write("    </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body style=\"overflow-x:hidden; padding:2px;\">\r\n");
      out.write("<div class=\"l-loading\" style=\"display:block\" id=\"pageloading\"></div>\r\n");
      out.write(" <a class=\"l-button\" style=\"width:120px;float:left; margin-left:10px; display:none;\" onclick=\"deleteRow()\">删除选择的行</a>\r\n");
      out.write(" \r\n");
      out.write(" <div class=\"l-clear\"></div>\r\n");
      out.write("\r\n");
      out.write("    <div id=\"maingrid\"></div>\r\n");
      out.write("   \r\n");
      out.write("  <div style=\"display:none;\">\r\n");
      out.write("  \r\n");
      out.write("</div>\r\n");
      out.write(" \r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
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
