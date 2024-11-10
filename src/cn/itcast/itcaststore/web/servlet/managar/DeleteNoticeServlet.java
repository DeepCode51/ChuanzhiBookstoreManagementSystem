package cn.itcast.itcaststore.web.servlet.managar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.service.NoticeService;

/**
 * 
 *	后台删除公告的servlet
 */
public class DeleteNoticeServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		NoticeService nService = new NoticeService();
		//获取请求参数，公告id
		String n_id = req.getParameter("id");

		//调用dao层方法
		nService.deleteNotice(n_id);
		
		req.getRequestDispatcher("/managar/ListNoticeServlet").forward(req, resp);
	}
}
