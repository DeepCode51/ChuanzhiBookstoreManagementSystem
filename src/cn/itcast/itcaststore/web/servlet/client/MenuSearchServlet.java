package cn.itcast.itcaststore.web.servlet.client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.PageBean;
import cn.itcast.itcaststore.service.ProductService;
/**
 * 前台页面，用于菜单栏下面搜索功能的servlet
 */
public class MenuSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 1.定义当前页码，默认为1
		int currentPage = 1;
		String _currentPage = req.getParameter("currentPage");
		if (_currentPage != null) {
			currentPage = Integer.parseInt(_currentPage);
		}
		// 2.定义每页显示条数,默认为4
		int currentCount = 4;	
		//获取前台页面搜索框输入的值
		String searchfield = req.getParameter("textfield");
		//如果搜索框中没有输入值，则表单传递的为默认值，此时默认查询全部商品目录
		if("请输入书名".equals(searchfield)){
			req.getRequestDispatcher("/showProductByPage").forward(req, resp);
			return;
		}
		//调用service层的方法，通过书名模糊查询，查找相应的图书
		ProductService service = new ProductService();
		PageBean bean = service.findBookByName(currentPage,currentCount,searchfield);
		// 将数据存储到request范围，跳转到product_search_list.jsp页面展示
		req.setAttribute("bean", bean);
		req.getRequestDispatcher("/client/product_search_list.jsp").forward(req, resp);
	}
}
