package cn.itcast.itcaststore.web.servlet.client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.service.ProductService;
//分页显示数据
public class ShowProductByPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		// 1.定义当前页码，默认为1
		int currentPage = 1;
		String _currentPage = request.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 2.定义每页显示条数,默认为4
		int currentCount = 4;
		String _currentCount = request.getParameter("currentCount");
		if (_currentCount != null) {
			currentCount = Integer.parseInt(_currentCount);
		}
		// 3.获取查找的分类
		String category = "全部商品";
		//如果是乱码, 请在EncodingFilter的65行设置为ISO-8859-1 ,浏览器默认是ISO-8859-1, 如果改为了utf-8 ,就设置为utf-8
//		String _category = new String (request.getParameter("category").getBytes("utf-8"),"utf-8");
		String _category = request.getParameter("category");
		if (_category != null) {
			category = _category;
		}
		// 4.调用service，完成获取当前页分页Bean数据.
		ProductService service = new ProductService();
		PageBean bean = service.findProductByPage(currentPage, currentCount,
				category);
		// 将数据存储到request范围，跳转到product_list.jsp页面展示
		request.setAttribute("bean", bean);
		request.getRequestDispatcher("/client/product_list.jsp").forward(request, response);
		return;
	}
}
