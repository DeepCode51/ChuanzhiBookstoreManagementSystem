package cn.itcast.itcaststore.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.itcaststore.domain.Notice;
import cn.itcast.itcaststore.utils.DataSourceUtils;

public class NoticeDao {
	//后台系统，查询所有的公告
	public List<Notice> getAllNotices() throws SQLException {
		String sql = "select * from notice order by n_time desc limit 0,10";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanListHandler<Notice>(Notice.class));
	}

	//后台系统，添加公告
	public void addNotice(Notice n) throws SQLException {
		String sql = "insert into notice(title,details,n_time) values(?,?,?)";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, n.getTitle(),n.getDetails(),n.getN_time());
	}

	//后台系统，根据id查找公告
	public Notice findNoticeById(String n_id) throws SQLException {
		String sql = "select * from notice where n_id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Notice>(Notice.class),n_id);
	}

	//后台系统，根据id修改单个公告
	public void updateNotice(Notice n) throws SQLException {
		String sql = "update notice set title=?,details=?,n_time=? where n_id=?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, n.getTitle(),n.getDetails(),n.getN_time(),n.getN_id());
	}

	//后台系统，根据id删除公告
	public void deleteNotice(String n_id) throws SQLException {
		String sql = "delete from notice where n_id = ?";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		runner.update(sql, n_id);
	}

	//前台系统，查询最新添加或修改的一条公告
	public Notice getRecentNotice() throws SQLException {
		String sql = "select * from notice order by n_time desc limit 0,1";
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		return runner.query(sql, new BeanHandler<Notice>(Notice.class));
	}
}
