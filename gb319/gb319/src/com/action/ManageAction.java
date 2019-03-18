package com.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.CaipinDao;
import com.dao.CantingDao;
import com.dao.DingdanDao;
import com.dao.LeibieDao;
import com.dao.PeisongDao;
import com.dao.UserDao;
import com.model.Caipin;
import com.model.Canting;
import com.model.Dingdan;
import com.model.Leibie;
import com.model.Peisong;
import com.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Pager;
import com.util.Util;

public class ManageAction extends ActionSupport {

	private static final long serialVersionUID = -4304509122548259589L;

	private String url = "./";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	// 获取请求对象
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ServletActionContext.getRequest();
		return request;
	}

	// 获取响应对象
	public HttpServletResponse getResponse() {
		HttpServletResponse response = ServletActionContext.getResponse();
		return response;
	}

	// 获取session对象
	public HttpSession getSession() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		return session;
	}

	// 获取输出对象
	public PrintWriter getPrintWriter() {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter writer = null;
		try {
			writer = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return writer;
	}

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 登入请求
	public String login() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userDao.selectBean(" where username = '" + username + "' and password= '" + password + "'"
				+ " and deletestatus=0 and role=0");
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			this.setUrl("manage/index.jsp");
			return "redirect";
		} else {
			writer.print(
					"<script language=javascript>alert('用户名或者密码错误');window.location.href='manage/login.jsp';</script>");
		}
		return null;
	}

	// 用户退出
	public String loginout() {
		HttpServletRequest request = this.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		this.setUrl("manage/login.jsp");
		return SUCCESS;
	}

	// 跳转到修改密码页面
	public String changepwd() {
		this.setUrl("manage/password.jsp");
		return SUCCESS;
	}

	// 修改密码操作
	public void changepwd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		User bean = userDao.selectBean(
				" where username= '" + u.getUsername() + "' and password= '" + password1 + "' and deletestatus=0");
		if (bean != null) {
			bean.setPassword(password2);
			userDao.updateBean(bean);
			writer.print("<script language=javascript>alert('修改成功');window.location.href='method!changepwd';</script>");
		} else {
			writer.print(
					"<script language=javascript>alert('原密码错误');window.location.href='method!changepwd';</script>");
		}
	}

	private LeibieDao leibieDao;

	public LeibieDao getLeibieDao() {
		return leibieDao;
	}

	public void setLeibieDao(LeibieDao leibieDao) {
		this.leibieDao = leibieDao;
	}

	// 菜品类别列表
	public String leibielist() {
		HttpServletRequest request = this.getRequest();
		String lname = request.getParameter("lname");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (lname != null && !"".equals(lname)) {

			sb.append("lname like '%" + lname + "%'");
			sb.append(" and ");
			request.setAttribute("lname", lname);
		}

		sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = leibieDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", leibieDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo",
				Pager.getPagerNormal(total, pagesize, currentpage, "method!leibielist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!leibielist");
		request.setAttribute("url2", "method!leibie");
		request.setAttribute("title", "菜品类别管理");
		this.setUrl("manage/leibie/leibielist.jsp");
		return SUCCESS;

	}

	// 跳转到添加菜品类别页面
	public String leibieadd() {
		HttpServletRequest request = this.getRequest();

		request.setAttribute("url", "method!leibieadd2");
		request.setAttribute("title", "添加新菜品类别");
		this.setUrl("manage/leibie/leibieadd.jsp");
		return SUCCESS;
	}

	// 添加菜品类别操作
	public void leibieadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String lname = request.getParameter("lname");

		Leibie bean = new Leibie();
		bean.setLname(lname);
		leibieDao.insertBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!leibielist';</script>");

	}

	// 跳转到更新菜品类别页面
	public String leibieupdate() {
		HttpServletRequest request = this.getRequest();
		Leibie bean = leibieDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!leibieupdate2?id=" + bean.getId());
		request.setAttribute("title", "菜品类别修改");
		this.setUrl("manage/leibie/leibieupdate.jsp");
		return SUCCESS;
	}

	// 更新菜品类别操作
	public void leibieupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String lname = request.getParameter("lname");

		Leibie bean = leibieDao.selectBean(" where id= " + request.getParameter("id"));

		bean.setLname(lname);

		leibieDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!leibielist';</script>");
	}

	// 删除菜品类别操作
	public void leibiedelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Leibie bean = leibieDao.selectBean(" where id= " + request.getParameter("id"));
		bean.setDeletestatus(1);
		leibieDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!leibielist';</script>");
	}

	// 跳转到查看菜品类别页面
	public String leibieupdate3() {
		HttpServletRequest request = this.getRequest();
		Leibie bean = leibieDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "菜品类别查看");
		this.setUrl("manage/leibie/leibieupdate3.jsp");
		return SUCCESS;
	}

	private CaipinDao caipinDao;

	public CaipinDao getCaipinDao() {
		return caipinDao;
	}

	public void setCaipinDao(CaipinDao caipinDao) {
		this.caipinDao = caipinDao;
	}

	// 菜品列表
	public String caipinlist() {
		HttpServletRequest request = this.getRequest();
		String mingchen = request.getParameter("mingchen");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (mingchen != null && !"".equals(mingchen)) {

			sb.append("mingchen like '%" + mingchen + "%'");
			sb.append(" and ");
			request.setAttribute("mingchen", mingchen);
		}

		sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = caipinDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", caipinDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo",
				Pager.getPagerNormal(total, pagesize, currentpage, "method!caipinlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!caipinlist");
		request.setAttribute("url2", "method!caipin");
		request.setAttribute("title", "菜品管理");
		this.setUrl("manage/caipin/caipinlist.jsp");
		return SUCCESS;

	}

	// 跳转到添加菜品页面
	public String caipinadd() {
		HttpServletRequest request = this.getRequest();

		request.setAttribute("leibielist", leibieDao.selectBeanList(0, 9999, " where deletestatus=0  "));
		request.setAttribute("url", "method!caipinadd2");
		request.setAttribute("title", "添加新菜品");
		this.setUrl("manage/caipin/caipinadd.jsp");
		return SUCCESS;
	}

	private File uploadfile;

	public File getUploadfile() {
		return uploadfile;
	}

	public void setUploadfile(File uploadfile) {
		this.uploadfile = uploadfile;
	}

	// 添加菜品操作
	public void caipinadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String jiage = request.getParameter("jiage");
		String miaoshu = request.getParameter("miaoshu");
		String mingchen = request.getParameter("mingchen");
		String leibieid = request.getParameter("leibieid");
		String yuanjia = request.getParameter("yuanjia");

		Caipin bean = new Caipin();

		if (uploadfile != null) {
			String savapath = ServletActionContext.getServletContext().getRealPath("/") + "/uploadfile/";
			String time = Util.getTime2();
			String imgpath = time + ".jpg";
			File file = new File(savapath + imgpath);
			Util.copyFile(uploadfile, file);
			bean.setTupian(imgpath);
		}
		bean.setJiage(jiage);
		bean.setMiaoshu(miaoshu);
		bean.setMingchen(mingchen);
		bean.setShijian(Util.getTime());
		bean.setTuijian("未推荐");
		bean.setDianji(0);
		bean.setYuanjia(yuanjia);
		bean.setLeibie(leibieDao.selectBean(" where id= " + leibieid));

		caipinDao.insertBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!caipinlist';</script>");

	}

	// 跳转到更新菜品页面
	public String caipinupdate() {
		HttpServletRequest request = this.getRequest();
		Caipin bean = caipinDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("leibielist", leibieDao.selectBeanList(0, 9999, " where deletestatus=0  "));

		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!caipinupdate2?id=" + bean.getId());
		request.setAttribute("title", "菜品修改");
		this.setUrl("manage/caipin/caipinupdate.jsp");
		return SUCCESS;
	}

	// 更新菜品操作
	public void caipinupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String jiage = request.getParameter("jiage");
		String miaoshu = request.getParameter("miaoshu");
		String mingchen = request.getParameter("mingchen");
		String leibieid = request.getParameter("leibieid");
		String yuanjia = request.getParameter("yuanjia");

		Caipin bean = caipinDao.selectBean(" where id= " + request.getParameter("id"));

		if (uploadfile != null) {
			String savapath = ServletActionContext.getServletContext().getRealPath("/") + "/uploadfile/";
			String time = Util.getTime2();
			String imgpath = time + "01.jpg";
			File file = new File(savapath + imgpath);
			Util.copyFile(uploadfile, file);
			bean.setTupian(imgpath);
		}
		bean.setJiage(jiage);
		bean.setMiaoshu(miaoshu);
		bean.setMingchen(mingchen);
		bean.setLeibie(leibieDao.selectBean(" where id= " + leibieid));
		bean.setYuanjia(yuanjia);
		caipinDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!caipinlist';</script>");
	}

	// 删除菜品操作
	public void caipindelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Caipin bean = caipinDao.selectBean(" where id= " + request.getParameter("id"));
		bean.setDeletestatus(1);
		caipinDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!caipinlist';</script>");
	}

	// 跳转到查看菜品页面
	public String caipinupdate3() {
		HttpServletRequest request = this.getRequest();
		Caipin bean = caipinDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "菜品查看");
		this.setUrl("manage/caipin/caipinupdate3.jsp");
		return SUCCESS;
	}

	// 推荐菜品操作
	public void caipindelete2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Caipin bean = caipinDao.selectBean(" where id= " + request.getParameter("id"));
		bean.setTuijian("推荐");
		caipinDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!caipinlist';</script>");
	}

	// 取消推荐菜品操作
	public void caipindelete3() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Caipin bean = caipinDao.selectBean(" where id= " + request.getParameter("id"));
		bean.setTuijian("未推荐");
		;
		caipinDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!caipinlist';</script>");
	}

	private CantingDao cantingDao;

	public CantingDao getCantingDao() {
		return cantingDao;
	}

	public void setCantingDao(CantingDao cantingDao) {
		this.cantingDao = cantingDao;
	}

	// 餐厅信息列表
	public String cantinglist() {
		HttpServletRequest request = this.getRequest();
		String ctitle = request.getParameter("ctitle");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (ctitle != null && !"".equals(ctitle)) {

			sb.append("ctitle like '%" + ctitle + "%'");
			sb.append(" and ");
			request.setAttribute("ctitle", ctitle);
		}

		sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = cantingDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", cantingDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo",
				Pager.getPagerNormal(total, pagesize, currentpage, "method!cantinglist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!cantinglist");
		request.setAttribute("url2", "method!canting");
		request.setAttribute("title", "餐厅信息管理");
		this.setUrl("manage/canting/cantinglist.jsp");
		return SUCCESS;

	}

	// 跳转到添加餐厅信息页面
	public String cantingadd() {
		HttpServletRequest request = this.getRequest();

		request.setAttribute("url", "method!cantingadd2");
		request.setAttribute("title", "添加新餐厅信息");
		this.setUrl("manage/canting/cantingadd.jsp");
		return SUCCESS;
	}

	// 添加餐厅信息操作
	public void cantingadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String ctitle = request.getParameter("ctitle");
		String neirong = request.getParameter("neirong");
		String str1 = request.getParameter("day");
		String str2 = request.getParameter("timestart");
		String str3 = request.getParameter("timeend");
		String yysj = str1 + " " + str2 + "至" + str3;
		Canting bean = new Canting();
		if (uploadfile != null) {
			String savapath = ServletActionContext.getServletContext().getRealPath("/") + "/uploadfile/";
			String time = Util.getTime2();
			String imgpath = time + ".jpg";
			File file = new File(savapath + imgpath);
			Util.copyFile(uploadfile, file);
			bean.setTupian(imgpath);
		}

		bean.setCtitle(ctitle);
		bean.setNeirong(neirong);
		bean.setShijian(yysj);

		cantingDao.insertBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!cantinglist';</script>");

	}

	// 跳转到更新餐厅信息页面
	public String cantingupdate() {
		HttpServletRequest request = this.getRequest();
		Canting bean = cantingDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!cantingupdate2?id=" + bean.getId());
		request.setAttribute("title", "餐厅信息修改");
		this.setUrl("manage/canting/cantingupdate.jsp");
		return SUCCESS;
	}

	// 更新餐厅信息操作
	public void cantingupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String ctitle = request.getParameter("ctitle");
		String neirong = request.getParameter("neirong");
		String str1 = request.getParameter("day");
		String str2 = request.getParameter("timestart");
		String str3 = request.getParameter("timeend");
		String yysj = str1 + " " + str2 + "至" + str3;
		Canting bean = cantingDao.selectBean(" where id= " + request.getParameter("id"));

		if (uploadfile != null) {
			String savapath = ServletActionContext.getServletContext().getRealPath("/") + "/uploadfile/";
			String time = Util.getTime2();
			String imgpath = time + "01.jpg";
			File file = new File(savapath + imgpath);
			Util.copyFile(uploadfile, file);
			bean.setTupian(imgpath);
		}

		bean.setCtitle(ctitle);
		bean.setNeirong(neirong);
		bean.setShijian(yysj);
		cantingDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!cantinglist';</script>");
	}

	// 删除餐厅信息操作
	public void cantingdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Canting bean = cantingDao.selectBean(" where id= " + request.getParameter("id"));
		bean.setDeletestatus(1);
		cantingDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!cantinglist';</script>");
	}

	// 跳转到查看餐厅信息页面
	public String cantingupdate3() {
		HttpServletRequest request = this.getRequest();
		Canting bean = cantingDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "餐厅信息查看");
		this.setUrl("manage/canting/cantingupdate3.jsp");
		return SUCCESS;
	}

	private DingdanDao dingdanDao;

	public DingdanDao getDingdanDao() {
		return dingdanDao;
	}

	public void setDingdanDao(DingdanDao dingdanDao) {
		this.dingdanDao = dingdanDao;
	}

	// 订单列表
	public String dingdanlist() {
		HttpServletRequest request = this.getRequest();
		String orderid = request.getParameter("orderid");

		String status = request.getParameter("status");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (orderid != null && !"".equals(orderid)) {

			sb.append("orderid like '%" + orderid + "%'");
			sb.append(" and ");
			request.setAttribute("orderid", orderid);
		}

		if (status != null && !"".equals(status)) {

			sb.append("status like '%" + status + "%'");
			sb.append(" and ");
			request.setAttribute("status", status);
		}

		sb.append("   deletestatus=0 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = dingdanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", dingdanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo",
				Pager.getPagerNormal(total, pagesize, currentpage, "method!dingdanlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!dingdanlist");
		request.setAttribute("url2", "method!dingdan");
		request.setAttribute("title", "订单管理");
		this.setUrl("manage/dingdan/dingdanlist.jsp");
		return SUCCESS;

	}

	// 处理订单操作
	public void dingdandelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Dingdan bean = dingdanDao.selectBean(" where id= " + request.getParameter("id"));

		bean.setStatus("配送订单");

		dingdanDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!dingdanlist';</script>");
	}

	// 处理订单操作
	public void dingdandelete2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Dingdan bean = dingdanDao.selectBean(" where id= " + request.getParameter("id"));

		bean.setStatus("完成订单");

		dingdanDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!dingdanlist';</script>");
	}

	// 跳转到查看订单页面
	public String dingdanupdate3() {
		HttpServletRequest request = this.getRequest();
		Dingdan bean = dingdanDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "订单查看");
		this.setUrl("manage/dingdan/dingdanupdate3.jsp");
		return SUCCESS;
	}

	// 会员列表
	public String userlist() {
		HttpServletRequest request = this.getRequest();
		String username = request.getParameter("username");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (username != null && !"".equals(username)) {

			sb.append("username like '%" + username + "%'");
			sb.append(" and ");
			request.setAttribute("username", username);
		}

		sb.append("   deletestatus=0  and role=1 order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = userDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", userDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo",
				Pager.getPagerNormal(total, pagesize, currentpage, "method!userlist", "共有" + total + "条记录"));
		request.setAttribute("url", "method!userlist");
		request.setAttribute("url2", "method!user");
		request.setAttribute("title", "会员管理");
		this.setUrl("manage/user/userlist.jsp");
		return SUCCESS;

	}

	// 删除会员操作
	public void userdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		User bean = userDao.selectBean(" where id= " + request.getParameter("id"));

		bean.setDeletestatus(1);

		userDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!userlist';</script>");
	}

	private PeisongDao peisongDao;

	public PeisongDao getPeisongDao() {
		return peisongDao;
	}

	public void setPeisongDao(PeisongDao peisongDao) {
		this.peisongDao = peisongDao;
	}

	// 订单列表
	public String dingdanlist2() {
		HttpServletRequest request = this.getRequest();
		String orderid = request.getParameter("orderid");

		String status = request.getParameter("status");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (orderid != null && !"".equals(orderid)) {

			sb.append("orderid like '%" + orderid + "%'");
			sb.append(" and ");
			request.setAttribute("orderid", orderid);
		}

		if (status != null && !"".equals(status)) {

			sb.append("status like '%" + status + "%'");
			sb.append(" and ");
			request.setAttribute("status", status);
		}

		sb.append("   deletestatus=0 and status='配送订单' order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = dingdanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", dingdanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo",
				Pager.getPagerNormal(total, pagesize, currentpage, "method!dingdanlist2", "共有" + total + "条记录"));
		request.setAttribute("url", "method!dingdanlist2");
		request.setAttribute("url2", "method!dingdan");
		request.setAttribute("title", "配送管理");
		this.setUrl("manage/dingdan/dingdanlist2.jsp");
		return SUCCESS;

	}

	// 配送信息列表
	public String peisonglist() {
		HttpServletRequest request = this.getRequest();
		String dingdanid = request.getParameter("dingdanid");

		request.setAttribute("dingdanid", dingdanid);

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		sb.append("   deletestatus=0 and dingdan.id=" + dingdanid + " order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = peisongDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		request.setAttribute("list", peisongDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo", Pager.getPagerNormal(total, pagesize, currentpage,
				"method!peisonglist?dingdanid=" + dingdanid, "共有" + total + "条记录"));
		request.setAttribute("url", "method!peisonglist?dingdanid=" + dingdanid);
		request.setAttribute("url2", "method!peisong");
		request.setAttribute("title", "配送信息管理");
		this.setUrl("manage/peisong/peisonglist.jsp");
		return SUCCESS;

	}

	// 跳转到添加配送信息页面
	public String peisongadd() {
		HttpServletRequest request = this.getRequest();
		String dingdanid = request.getParameter("dingdanid");
		request.setAttribute("url", "method!peisongadd2?dingdanid=" + dingdanid);
		request.setAttribute("title", "录入配送信息");
		this.setUrl("manage/peisong/peisongadd.jsp");
		return SUCCESS;
	}

	// 添加配送信息操作
	public void peisongadd2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		String dingdanid = request.getParameter("dingdanid");
		String info = request.getParameter("info");

		Peisong bean = new Peisong();

		bean.setDingdan(dingdanDao.selectBean(" where id= " + dingdanid));
		bean.setInfo(info);
		bean.setShijian(Util.getTime());
		bean.setChuanda("未送达");
		peisongDao.insertBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!peisonglist?dingdanid="
				+ dingdanid + "';</script>");

	}

	// 跳转到更新配送信息页面
	public String peisongupdate() {
		HttpServletRequest request = this.getRequest();
		Peisong bean = peisongDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("url", "method!peisongupdate2?id=" + bean.getId());
		request.setAttribute("title", "配送信息修改");
		this.setUrl("manage/peisong/peisongupdate.jsp");
		return SUCCESS;
	}

	// 更新配送信息操作
	public void peisongupdate2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String info = request.getParameter("info");

		Peisong bean = peisongDao.selectBean(" where id= " + request.getParameter("id"));

		bean.setInfo(info);

		peisongDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!peisonglist?dingdanid="
				+ bean.getDingdan().getId() + "';</script>");
	}

	// 删除配送信息操作
	public void peisongdelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Peisong bean = peisongDao.selectBean(" where id= " + request.getParameter("id"));
		bean.setDeletestatus(1);
		peisongDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!peisonglist?dingdanid="
				+ bean.getDingdan().getId() + "';</script>");

	}

	// 配送送达信息操作
	public void peisongdelete2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Peisong bean = peisongDao.selectBean(" where id= " + request.getParameter("id"));
		bean.setChuanda("已送达");
		peisongDao.updateBean(bean);

		writer.print("<script language=javascript>alert('操作成功');window.location.href='method!peisonglist?dingdanid="
				+ bean.getDingdan().getId() + "';</script>");

	}

	// 跳转到查看配送信息页面
	public String peisongupdate3() {
		HttpServletRequest request = this.getRequest();
		Peisong bean = peisongDao.selectBean(" where id= " + request.getParameter("id"));
		request.setAttribute("bean", bean);
		request.setAttribute("title", "配送信息查看");
		this.setUrl("manage/peisong/peisongupdate3.jsp");
		return SUCCESS;
	}
}
