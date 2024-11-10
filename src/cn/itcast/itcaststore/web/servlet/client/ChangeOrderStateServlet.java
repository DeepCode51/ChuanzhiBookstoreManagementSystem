package cn.itcast.itcaststore.web.servlet.client;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.itcaststore.service.OrderService;

public class ChangeOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获得订单号数据
		String orderid = request.getParameter("orderid");
		String paySuccess = "恭喜您支付成功！";
		if (null != orderid) {
				OrderService service = new OrderService();
				// 根据订单号修改订单状态
				try {
					service.updateState(orderid);
					request.setAttribute("paySuccess", paySuccess);
					request.getRequestDispatcher("/findOrderByUser").forward(request, response);
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().write("修改订单状态失败");
				}
			}
	}
}
