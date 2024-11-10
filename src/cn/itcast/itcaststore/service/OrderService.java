package cn.itcast.itcaststore.service;
import java.sql.SQLException;
import java.util.List;
import cn.itcast.itcaststore.dao.OrderDao;
import cn.itcast.itcaststore.dao.OrderItemDao;
import cn.itcast.itcaststore.dao.ProductDao;
import cn.itcast.itcaststore.domain.Order;
import cn.itcast.itcaststore.domain.OrderItem;
import cn.itcast.itcaststore.domain.User;
import cn.itcast.itcaststore.utils.DataSourceUtils;
public class OrderService {
	private OrderItemDao oidao = new OrderItemDao();
	private OrderDao odao = new OrderDao();
	private ProductDao pdao = new ProductDao();
	// 1.添加订单
	public void addOrder(Order order) {
		try {
			// 1.开启事务
			DataSourceUtils.startTransaction();
			// 2.完成操作
			// 2.1向orders表中添加数据
			odao.addProduct(order);
			// 2.2向orderItem表中添加数据
			oidao.addOrderItem(order);
			// 2.3修改商品表中数据.
			pdao.changeProductNum(order);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback(); // 事务回滚
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				// 关闭，释放以及提交事务
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	// 根据用户查找订单
	public List<Order> findOrderByUser(User user) {
		List<Order> orders = null;
		try {
			// 查找出订单信息
			orders = odao.findOrderByUser(user);

			// // 查找出订单项信息.
			// for (Order order : orders) {
			// List<OrderItem> items = oidao.findOrderItemByOrder(order);
			// //查找到订单中的订单项信息
			//
			// order.setOrderItems(items);
			// }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	// 根据id查找订单
	public Order findOrderById(String id) {
		Order order = null;
		try {
			order = odao.findOrderById(id);
			List<OrderItem> items = oidao.findOrderItemByOrder(order);
			order.setOrderItems(items);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	// 查找所有订单
	public List<Order> findAllOrder() {
		List<Order> orders = null;
		try {
			// 查找出订单信息
			orders = odao.findAllOrder();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	// 支付成功后修改订单状态
	public void updateState(String id) {
		try {
			odao.updateOrderState(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 多条件查询订单信息
	public List<Order> findOrderByManyCondition(String id, String receiverName) {
		List<Order> orders = null;
		try {
			orders = odao.findOrderByManyCondition(id, receiverName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}
	//根据id删除订单 管理员删除订单
	public void delOrderById(String id) {			
		try {
			DataSourceUtils.startTransaction();//开启事务
			oidao.delOrderItems(id);
			odao.delOrderById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
	//普通用户删除订单
	public void delOrderByIdWithClient(String id) {
		try {
			DataSourceUtils.startTransaction();//开启事务
			//从订单项中查找商品购买数量
			Order order=new Order();
			order.setId(id);
			List<OrderItem> items=oidao.findOrderItemByOrder(order);
			//修改商品数量			
			pdao.updateProductNum(items);						
			oidao.delOrderItems(id); //删除订单项
			odao.delOrderById(id); //删除订单
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				DataSourceUtils.releaseAndCloseConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}