package cn.itcast.itcaststore.web.servlet.client;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import cn.itcast.itcaststore.domain.Product;
/**
 * 购物车内容变更
 * @author admin
 *
 */
public class ChangeCartServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.得到商品id
		String id = request.getParameter("id");
		// 2.得到要修改的数量
		int count = Integer.parseInt(request.getParameter("count"));
		// 3.从session中获取购物车.
		HttpSession session = request.getSession();
		Map<Product, Integer> cart = (Map<Product, Integer>) session.getAttribute("cart");
		Product p = new Product();
		p.setId(id);
		if (count != 0) {
			cart.put(p, count);
		} else {
			cart.remove(p);
		}
		response.sendRedirect(request.getContextPath() + "/client/cart.jsp");
		return;
	}
}
