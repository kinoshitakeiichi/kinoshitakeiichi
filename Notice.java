package com.action;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import com.bean.NoticeBean;
import com.dao.NoticeDao;
import com.opensymphony.xwork2.ActionSupport;
public class Notice extends ActionSupport{
	private List<NoticeBean> Noticelist;
	
	public List<NoticeBean> getNoticelist() {
		return Noticelist;
	}

	public void setNoticelist(List<NoticeBean> noticelist) {
		Noticelist = noticelist;
	}

		//处理用户请求的execute方法
		public String execute() throws Exception {
				
				//解决乱码，用于页面输出
				HttpServletResponse response=null;
				response=ServletActionContext.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				
				//创建session对象
				HttpSession session = ServletActionContext.getRequest().getSession();
				//验证是否正常登录
				if(session.getAttribute("id")==null){
					out.print("<script language='javascript'>alert('请重新登录！');window.location='Login.jsp';</script>");
					out.flush();out.close();return null;
				}
				
				Noticelist=new NoticeDao().GetList("", "");
				
				return SUCCESS;
			}
			
			//判断是否空值
			private boolean isInvalid(String value) {
				return (value == null || value.length() == 0);
			}
			
}
