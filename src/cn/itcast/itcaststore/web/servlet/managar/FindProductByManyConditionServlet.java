package cn.itcast.itcaststore.web.servlet.managar;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import cn.itcast.itcaststore.domain.Product;
import cn.itcast.itcaststore.service.ProductService;
public class FindProductByManyConditionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1.获取表单数据
		String id = request.getParameter("id"); // 商品id
		String name = request.getParameter("name"); // 商品名称
		String category = request.getParameter("category"); // 商品类别
		String minprice = request.getParameter("minprice"); // 最小价格
		String maxprice = request.getParameter("maxprice"); // 最大价格
		// 2.创建ProductService对象
		ProductService service = new ProductService();
		// 3.调用service层用于条件查询的方法
		List<Product> ps = service.findProductByManyCondition(id, name,
				category, minprice, maxprice);
		// 4.将条件查询的结果放进request域中
		request.setAttribute("ps", ps);
		// 5.请求重定向到商品管理首页list.jsp页面
		request.getRequestDispatcher("/admin/products/list.jsp").forward(
				request, response);
	}
}
