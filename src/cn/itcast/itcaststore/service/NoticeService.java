package cn.itcast.itcaststore.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.itcaststore.dao.NoticeDao;
import cn.itcast.itcaststore.domain.Notice;

public class NoticeService {
	private NoticeDao dao = new NoticeDao();
	//后台系统，查询所有公告
	public List<Notice> getAllNotices() {
		try {
			return dao.getAllNotices();
		} catch (SQLException e) {
			throw new RuntimeException("查询所有的公告失败！");
		}
	}
	//后台系统，添加公告
	public void addNotice(Notice notice) {
		try {
			dao.addNotice(notice);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加公告失败!");
		}
	}
	//后台系统，根据id查找公告
	public Notice findNoticeById(String n_id) {
		try {
			return dao.findNoticeById(n_id);
		} catch (SQLException e) {
			throw new RuntimeException("根据id查找公告失败！");
		}
	}
	
	//后台系统，根据id修改公告
	public void updateNotice(Notice bean) {
		try {
			dao.updateNotice(bean);
		} catch (SQLException e) {
//			throw new RuntimeException("根据id修改公告失败！");
			e.printStackTrace();
		}
	}
	
	//后台系统，根据id删除公告
	public void deleteNotice(String n_id) {
		try {
			dao.deleteNotice(n_id);
		} catch (SQLException e) {
			throw new RuntimeException("根据id删除公告失败！");
		}
	}
	
	//前台系统，查询最新添加或修改的一条公告
	public Notice getRecentNotice() {
		try {
			return dao.getRecentNotice();
		} catch (SQLException e) {
			throw new RuntimeException("查询最新添加或修改的一条公告失败！");
		}
	}
}
