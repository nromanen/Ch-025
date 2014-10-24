package org.apache.jsp.WEB_002dINF.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class course_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
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
      out.write("\n");
      out.write("\n");
      out.write("<html>\n");
      out.write("\n");
      if (_jspx_meth_tiles_005finsertDefinition_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("</html>\n");
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

  private boolean _jspx_meth_tiles_005finsertDefinition_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:insertDefinition
    org.apache.tiles.jsp.taglib.InsertDefinitionTag _jspx_th_tiles_005finsertDefinition_005f0 = new org.apache.tiles.jsp.taglib.InsertDefinitionTag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_tiles_005finsertDefinition_005f0);
    _jspx_th_tiles_005finsertDefinition_005f0.setJspContext(_jspx_page_context);
    // /WEB-INF/views/course.jsp(9,0) name = name type = java.lang.String reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005finsertDefinition_005f0.setName("indexTemplate");
    _jspx_th_tiles_005finsertDefinition_005f0.setJspBody(new Helper( 0, _jspx_page_context, _jspx_th_tiles_005finsertDefinition_005f0, null));
    _jspx_th_tiles_005finsertDefinition_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_tiles_005finsertDefinition_005f0);
    return false;
  }

  private boolean _jspx_meth_tiles_005fputAttribute_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  tiles:putAttribute
    org.apache.tiles.jsp.taglib.PutAttributeTag _jspx_th_tiles_005fputAttribute_005f0 = new org.apache.tiles.jsp.taglib.PutAttributeTag();
    org.apache.jasper.runtime.AnnotationHelper.postConstruct(_jsp_annotationprocessor, _jspx_th_tiles_005fputAttribute_005f0);
    _jspx_th_tiles_005fputAttribute_005f0.setJspContext(_jspx_page_context);
    _jspx_th_tiles_005fputAttribute_005f0.setParent(_jspx_parent);
    // /WEB-INF/views/course.jsp(11,1) name = name type = java.lang.String reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_tiles_005fputAttribute_005f0.setName("main-content");
    _jspx_th_tiles_005fputAttribute_005f0.setJspBody(new Helper( 1, _jspx_page_context, _jspx_th_tiles_005fputAttribute_005f0, null));
    _jspx_th_tiles_005fputAttribute_005f0.doTag();
    org.apache.jasper.runtime.AnnotationHelper.preDestroy(_jsp_annotationprocessor, _jspx_th_tiles_005fputAttribute_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    // /WEB-INF/views/course.jsp(25,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isSubscribe eq true}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t<button value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subject.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" name=\"subjectId\" class=\"btn-success\"\n");
        out.write("\t\t\t\t\tstyle=\"border-radius:5px; margin-top:8px;\">Subscribe to course </button>\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(javax.servlet.jsp.tagext.JspTag _jspx_parent, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    // /WEB-INF/views/course.jsp(29,5) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isSubscribe eq false}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t\t\t\t<button value=\"");
        out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subject.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
        out.write("\" name=\"subjectId\" class=\"btn-success\"\n");
        out.write("\t\t\t\t\tstyle=\"border-radius:5px; margin-top:8px;\">Unsubscribe form course </button>\n");
        out.write("\t\t\t\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      throw new SkipPageException();
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private class Helper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public Helper( int discriminator, JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public boolean invoke0( JspWriter out ) 
      throws Throwable
    {
      out.write('\n');
      out.write('\n');
      out.write('	');
      if (_jspx_meth_tiles_005fputAttribute_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write('\n');
      return false;
    }
    public boolean invoke1( JspWriter out ) 
      throws Throwable
    {
      out.write("\n");
      out.write("\t\t<div id=\"page-wrapper\">\n");
      out.write("\t\t\t<div class=\"row\">\n");
      out.write("\t\t\t\t<div class=\"col-lg-12\">\n");
      out.write("\t\t\t\t\t<h1 class=\"page-header\">SoftServe SSEL Academy</h1>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t<div class=\"col-lg-12\">\n");
      out.write("\t\t\t\t\t<h3 class=\"title\">\n");
      out.write("\t\t\t\t\t\tAbout course\n");
      out.write("\t\t\t\t\t</h3>\n");
      out.write("\t\t\t\t<div>\n");
      out.write("\t\t\t\t\t<div class=\"title\"> ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subject.name}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</div>\n");
      out.write("\t\t\t\t\t<div style=\"padding-top:8px;\"> Description: ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subject.description}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("</div>\n");
      out.write("\t\t\t\t\t<form method=\"GET\" action=\"subscribe\">\t\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("\t\t\t\t\t");
      if (_jspx_meth_c_005fif_005f1(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("\t\t\t\t\t<input type=\"hidden\" name=\"op\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${isSubscribe}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t\t<form method=\"GET\" action=\"courseInformation\">\t\n");
      out.write("\t\t\t\t\t<button value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${subject.id}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" name=\"subjectId\" class=\"btn-info\"\n");
      out.write("\t\t\t\t\tstyle=\"border-radius:5px; margin-top:8px;\"> Details </button>\n");
      out.write("\t\t\t\t\t</form>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</body>\n");
      out.write("\t\t\n");
      out.write("\t\t\n");
      out.write("    <!-- jQuery Version 1.11.0 -->\n");
      out.write("    <script src=\"resources/js/jquery-1.11.0.js\"></script>\n");
      out.write("\n");
      out.write("    <!-- Bootstrap Core JavaScript -->\n");
      out.write("    <script src=\"resources/js/bootstrap.min.js\"></script>\n");
      out.write("\n");
      out.write("    <!-- Metis Menu Plugin JavaScript -->\n");
      out.write("    <script src=\"resources/js/plugins/metisMenu/metisMenu.min.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("    <!-- Custom Theme JavaScript -->\n");
      out.write("    <script src=\"resources/js/sb-admin-2.js\"></script>\n");
      out.write("\t\n");
      out.write("\t    <!-- DataTables JavaScript -->\n");
      out.write("    <script src=\"resources/js/plugins/dataTables/jquery.dataTables.js\"></script>\n");
      out.write("    <script src=\"resources/js/plugins/dataTables/dataTables.bootstrap.js\"></script>\n");
      out.write("\n");
      out.write("\n");
      out.write("\t");
      return false;
    }
    public void invoke( java.io.Writer writer )
      throws JspException
    {
      JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        this.jspContext.getELContext().putContext(JspContext.class,this.jspContext);
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
          case 1:
            invoke1( out );
            break;
        }
      }
      catch( Throwable e ) {
        if (e instanceof SkipPageException)
            throw (SkipPageException) e;
        throw new JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}
