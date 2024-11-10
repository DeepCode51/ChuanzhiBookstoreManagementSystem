package cn.itcast.itcaststore.web.servlet.managar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.domain.Notice;
import cn.itcast.itcaststore.service.NoticeService;

/**
 * 
 *	后台根据id查询公告的servlet
 */
public class FindByIdNoticeServlet extends HttpServlet{

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
		//获取公告id
		String n_id = req.getParameter("id");
		Notice notice = nService.findNoticeById(n_id);
		
		req.setAttribute("n", notice);
		
		req.getRequestDispatcher("/admin/notices/edit.jsp").forward(req, resp);
	}
}
