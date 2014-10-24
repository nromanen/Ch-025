package org.apache.jsp.WEB_002dINF.templates.index;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class menu_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("<div id=\"wrapper\">\n");
      out.write("\n");
      out.write("\t<!-- Navigation -->\n");
      out.write("\t<nav class=\"navbar navbar-default navbar-static-top\" role=\"navigation\"\n");
      out.write("\t\tstyle=\"margin-bottom: 0\">\n");
      out.write("\t\t<div class=\"navbar-header\">\n");
      out.write("\t\t\t<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\"\n");
      out.write("\t\t\t\tdata-target=\".navbar-collapse\">\n");
      out.write("\t\t\t\t<span class=\"sr-only\">Toggle navigation</span> <span\n");
      out.write("\t\t\t\t\tclass=\"icon-bar\"></span> <span class=\"icon-bar\"></span> <span\n");
      out.write("\t\t\t\t\tclass=\"icon-bar\"></span>\n");
      out.write("\t\t\t</button>\n");
      out.write("\t\t\t<a class=\"navbar-brand\" href=\"/entity/\">SSEL SoftServe Academy</a>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t<!-- /.navbar-header -->\n");
      out.write("\n");
      out.write("\t\t<ul class=\"nav navbar-top-links navbar-right\">\n");
      out.write("\n");
      out.write("\t\t\t<li class=\"dropdown\"><a class=\"dropdown-toggle\"\n");
      out.write("\t\t\t\tdata-toggle=\"dropdown\" href=\"#\"> <i class=\"fa fa-user fa-fw\"></i>\n");
      out.write("\t\t\t\t\t<i class=\"fa fa-caret-down\"></i>\n");
      out.write("\t\t\t</a>\n");
      out.write("\t\t\t\t<ul class=\"dropdown-menu dropdown-user\">\n");
      out.write("\t\t\t\t\t<li><a href=\"student\"><i class=\"fa fa-user fa-fw\"></i> User\n");
      out.write("\t\t\t\t\t\t\tProfile</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"#\"><i class=\"fa fa-gear fa-fw\"></i> Settings</a>\n");
      out.write("\t\t\t\t\t</li>\n");
      out.write("\t\t\t\t\t<li class=\"divider\"></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"login\"><i class=\"fa fa-sign-in fa-fw\"></i>\n");
      out.write("\t\t\t\t\t\t\tSign in</a></li>\n");
      out.write("\t\t\t\t\t<li><a href=\"registration\"><i class=\"fa fa-sign-in fa-fw\"></i>\n");
      out.write("\t\t\t\t\t\t\tSign up</a></li>\n");
      out.write("\t\t\t\t</ul> <!-- /.dropdown-user --></li>\n");
      out.write("\t\t\t<!-- /.dropdown -->\n");
      out.write("\t\t</ul>\n");
      out.write("\t\t<!-- /.navbar-top-links -->\n");
      out.write("\t\t<div class=\"navbar-default sidebar\" role=\"navigation\">\n");
      out.write("\t\t\t<div class=\"sidebar-nav navbar-collapse\">\n");
      out.write("\t\t\t\t<form method=\"GET\" action=\"course\">\n");
      out.write("\t\t\t\t\t<ul class=\"nav\" id=\"side-menu\">\n");
      out.write("\t\t\t\t\t\t<li><a href=\"#\"><i class=\"fa fa-sitemap fa-fw\"></i> All\n");
      out.write("\t\t\t\t\t\t\t\tCourses<span class=\"fa arrow\"></span></a>\n");
      out.write("\n");
      out.write("\t\t\t\t\t\t\t<ul class=\"nav nav-second-level\">\n");
      out.write("\t\t\t\t\t\t\t\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t\t\t</ul></li>\n");
      out.write("\t\t\t\t\t</ul>\n");
      out.write("\t\t\t\t</form>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</nav>\n");
      out.write("</div>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /WEB-INF/templates/index/menu.jsp(46,8) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/templates/index/menu.jsp(46,8) '${catList}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${catList}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/templates/index/menu.jsp(46,8) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("cat");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t\t\t\t\t\t\t<li><a href=\"#\"> ");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${cat.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write(" <span class=\"fa arrow\"></span>\n");
          out.write("\t\t\t\t\t\t\t\t\t</a>\n");
          out.write("\t\t\t\t\t\t\t\t\t\t<ul class=\"nav nav-third-level\">\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t");
          if (_jspx_meth_c_005fforEach_005f1(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("\n");
          out.write("\t\t\t\t\t\t\t\t\t\t</ul>\n");
          out.write("\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /WEB-INF/templates/index/menu.jsp(50,11) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems(new org.apache.jasper.el.JspValueExpression("/WEB-INF/templates/index/menu.jsp(50,11) '${cat.subjects}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${cat.subjects}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /WEB-INF/templates/index/menu.jsp(50,11) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("subj");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t<li class=\"link\"><button value=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subj.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("\" name=\"subjectId\" \n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t\tclass=\"btn-link\" style=\"color:#428bca;\">\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subj.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
          out.write("</button></li>\n");
          out.write("\t\t\t\t\t\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }
}
