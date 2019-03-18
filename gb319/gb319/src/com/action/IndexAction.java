package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.dao.CaipinDao;
import com.dao.CantingDao;
import com.dao.DingdanDao;
import com.dao.GouwucheDao;
import com.dao.LeibieDao;
import com.dao.PeisongDao;
import com.dao.UserDao;
import com.model.Caipin;
import com.model.Canting;
import com.model.Dingdan;
import com.model.Gouwuche;
import com.model.User;
import com.opensymphony.xwork2.ActionSupport;
import com.util.Arith;
import com.util.Pager;
import com.util.Util;

public class IndexAction extends ActionSupport {

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

	private LeibieDao leibieDao;

	private CaipinDao caipinDao;

	public LeibieDao getLeibieDao() {
		return leibieDao;
	}

	public void setLeibieDao(LeibieDao leibieDao) {
		this.leibieDao = leibieDao;
	}

	public CaipinDao getCaipinDao() {
		return caipinDao;
	}

	public void setCaipinDao(CaipinDao caipinDao) {
		this.caipinDao = caipinDao;
	}

	// 网站首页
	public String index() throws Exception {

		HttpServletRequest request = this.getRequest();

		String mingchen = request.getParameter("mingchen");

		String leibieid = request.getParameter("leibieid");

		String tuijian = request.getParameter("tuijian");

		String type = request.getParameter("type");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (mingchen != null && !"".equals(mingchen)) {

			sb.append("mingchen like '%" + mingchen + "%'");
			sb.append(" and ");
		}

		if (leibieid != null && !"".equals(leibieid)) {

			sb.append("leibie.id =" + leibieid + "");
			sb.append(" and ");

		}

		if ("1".equals(tuijian)) {

			sb.append("tuijian ='推荐'");
			sb.append(" and ");

		}

		String where = "";

		int total = 0;

		if ("1".equals(type)) {
			sb.append("   deletestatus=0 order by id desc ");

			where = sb.toString();

			total = caipinDao.selectBeanCount(where.replaceAll("order by id desc", ""));

		} else if ("2".equals(type)) {
			sb.append("   deletestatus=0 order by dianji desc ");

			where = sb.toString();

			total = caipinDao.selectBeanCount(where.replaceAll("order by dianji desc", ""));

		} else if ("3".equals(type)) {
			sb.append("   deletestatus=0 order by buy desc ");

			where = sb.toString();

			total = caipinDao.selectBeanCount(where.replaceAll("order by buy desc", ""));

		} else {
			sb.append("   deletestatus=0 order by id desc ");

			where = sb.toString();

			total = caipinDao.selectBeanCount(where.replaceAll("order by id desc", ""));

		}

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}

		request.setAttribute("list", caipinDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));

		request.setAttribute("pagerinfo",
				Pager.getPagerNormal(total, pagesize, currentpage, ".", "共有" + total + "条记录"));

		request.setAttribute("leibielist", leibieDao.selectBeanList(0, 10, " where deletestatus=0  order by id desc "));

		return "success";
	}

	// 跳转到查看菜品页面
	public String caipin() {
		HttpServletRequest request = this.getRequest();
		Caipin bean = caipinDao.selectBean(" where id= " + request.getParameter("id"));
		bean.setDianji(bean.getDianji() + 1);

		caipinDao.updateBean(bean);

		request.setAttribute("bean", bean);
		request.setAttribute("title", "菜品详情");

		this.setUrl("caipin.jsp");
		return SUCCESS;

	}

	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 跳转到用户注册页面
	public String register() {
		HttpServletRequest request = this.getRequest();

		request.setAttribute("url", "indexmethod!register2");
		request.setAttribute("title", "用户注册");
		this.setUrl("register.jsp");
		return SUCCESS;
	}

	// 用户注册操作
	public void register2() throws IOException {
		HttpServletRequest request = this.getRequest();

		PrintWriter writer = this.getPrintWriter();

		String address = request.getParameter("address");
		String dianhua = request.getParameter("dianhua");
		String password = request.getParameter("password");
		String username = request.getParameter("username");
		String xingming = request.getParameter("xingming");

		User bean = userDao.selectBean(" where deletestatus=0 and username='" + username + "' ");
		if (bean == null) {
			bean = new User();
			bean.setAddress(address);
			bean.setCreatetime(Util.getTime());
			bean.setDianhua(dianhua);
			bean.setPassword(password);
			bean.setRole(1);
			bean.setUsername(username);
			bean.setXingming(xingming);
			userDao.insertBean(bean);

			writer.print("<script language=javascript>alert('注册成功');window.location.href='.';</script>");
		} else {
			writer.print(
					"<script language=javascript>alert('注册失败，该用户名已经存在');window.location.href='indexmethod!register';</script>");
		}

	}

	// 跳转到修改个人信息页面
	public String update() {
		HttpServletRequest request = this.getRequest();
		request.setAttribute("url", "indexmethod!update2");
		request.setAttribute("title", "修改个人信息");
		this.setUrl("update.jsp");

		return SUCCESS;
	}

	// 修改个人信息
	public void update2() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		HttpSession session = request.getSession();
		User us = (User) session.getAttribute("qiantai");
		System.out.println(us.toString());
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
		String address = request.getParameter("address");
		String dianhua = request.getParameter("dianhua");

		User bean = userDao.selectBean(
				" where username= '" + us.getUsername() + "' and password= '" + password1 + "' and deletestatus=0");
		if ((bean != null)) {

			bean.setAddress(address);
			bean.setDianhua(dianhua);
			bean.setPassword(password2);

			userDao.updateBean(bean);
			writer.print("<script language=javascript>alert('修改成功');window.location.href='.';</script>");
		} else {
			writer.print(
					"<script language=javascript>alert('原密码错误');window.location.href='indexmethod!update';</script>");

		}
	}

	// 跳转到登录页面
	public String login() {
		HttpServletRequest request = this.getRequest();

		request.setAttribute("url", "indexmethod!login2");
		request.setAttribute("title", "登录");
		this.setUrl("login.jsp");
		return SUCCESS;
	}

	// 登录操作
	public void login2() throws IOException {
		HttpServletRequest request = this.getRequest();

		PrintWriter writer = this.getPrintWriter();

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User bean = userDao.selectBean(" where username = '" + username + "' and password= '" + password
				+ "' and deletestatus=0 and role!=0 ");

		if (bean != null) {
			HttpSession session = request.getSession();
			session.setAttribute("qiantai", bean);

			writer.print("<script language=javascript>alert('登录成功');window.location.href='.';</script>");
		} else {
			writer.print(
					"<script language=javascript>alert('用户名或者密码错误');window.location.href='indexmethod!login';</script>");
		}

	}

	// 用户退出
	public void loginout() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		HttpSession session = request.getSession();
		session.removeAttribute("qiantai");
		writer.print("<script language=javascript>alert('退出成功');window.location.href='.';</script>");
	}

	private GouwucheDao gouwucheDao;

	public GouwucheDao getGouwucheDao() {
		return gouwucheDao;
	}

	public void setGouwucheDao(GouwucheDao gouwucheDao) {
		this.gouwucheDao = gouwucheDao;
	}

	// 购物车列表
	public String gouwuchelist() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("qiantai");
		if (user == null) {
			writer.print(
					"<script language=javascript>alert('请先登录');window.location.href='indexmethod!login';</script>");
			return null;
		}

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		sb.append("   user.id=" + user.getId() + " order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 9999;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		List<Gouwuche> list = gouwucheDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where);
		for (Gouwuche bean : list) {
			bean.setTotal(Arith.mul(bean.getSl(), Double.parseDouble(bean.getCaipin().getJiage())));

		}

		request.setAttribute("list", list);

		double zongjia1 = 0;
		double zongjia2 = 0;
		for (Gouwuche bean : list) {
			double jiage1 = Arith.mul(bean.getSl(), Double.parseDouble(bean.getCaipin().getYuanjia()));
			double jiage2 = Arith.mul(bean.getSl(), Double.parseDouble(bean.getCaipin().getJiage()));

			zongjia1 = zongjia1 + jiage1;
			zongjia2 = zongjia2 + jiage2;
		}
		request.setAttribute("zongjia1", zongjia1);

		request.setAttribute("zongjia2", zongjia2);

		request.setAttribute("title", "我的购物车");

		this.setUrl("gouwuchelist.jsp");
		return SUCCESS;

	}

	// 添加到购物车操作
	public void gouwucheadd() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("qiantai");
		if (user == null) {

			writer.print(
					"<script language=javascript>alert('请先登录');window.location.href='indexmethod!login';</script>");
			return;
		}

		String cid = request.getParameter("cid");
		Caipin c = caipinDao.selectBean(" where id= " + cid);

		Gouwuche bean = gouwucheDao.selectBean(" where caipin.id= " + cid + " and user.id= " + user.getId());
		if (bean == null) {
			bean = new Gouwuche();
			bean.setCreatetime(Util.getTime());
			bean.setSl(1);
			bean.setUser(user);
			bean.setCaipin(c);
			gouwucheDao.insertBean(bean);
			writer.print("<script language=javascript>alert('操作成功');window.location.href='indexmethod!caipin?id=" + cid
					+ "';</script>");
		} else {
			writer.print(
					"<script language=javascript>alert('该餐饮已经存在购物车中，请勿重复添加！');window.location.href='indexmethod!caipin?id="
							+ cid + "';</script>");
		}

	}

	// 删除购物车中的餐饮操作
	public void gouwuchedelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		Gouwuche bean = gouwucheDao.selectBean(" where id= " + request.getParameter("id"));

		gouwucheDao.deleteBean(bean);

		writer.print(
				"<script language=javascript>alert('操作成功');window.location.href='indexmethod!gouwuchelist';</script>");
	}

	// 修改购物车的数量的操作
	public void gouwucheupdate() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		String sl = request.getParameter("sl");
		Gouwuche gouwuche = gouwucheDao.selectBean(" where id= " + request.getParameter("id"));

		gouwuche.setSl(Integer.parseInt(sl));

		gouwucheDao.updateBean(gouwuche);

		writer.print("<script  language='javascript'>alert('操作成功');"
				+ "window.location.href='indexmethod!gouwuchelist'; </script>");

	}

	private DingdanDao dingdanDao;

	public DingdanDao getDingdanDao() {
		return dingdanDao;
	}

	public void setDingdanDao(DingdanDao dingdanDao) {
		this.dingdanDao = dingdanDao;
	}

	// 跳转到客户填写收件信息页面
	public String dingdanadd() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("qiantai");

		String sjname = request.getParameter("sjname");
		if (sjname != null) {
			writer.print("<script  language='javascript'>alert('操作成功');"
					+ "window.location.href='indexmethod!dingdanlist'; </script>");
			return null;
		}

		List<Gouwuche> list2 = gouwucheDao.selectBeanList(0, 9999, " where user.id=" + user.getId());
		if (list2.size() <= 0) {
			writer.print(
					"<script  language='javascript'>alert('操作失败，购物车不能为空！');window.location.href='indexmethod!gouwuchelist'; </script>");
			return null;
		}

		request.setAttribute("title", "添加收货信息");
		request.setAttribute("url", "indexmethod!dingdanadd2");
		this.setUrl("dingdanadd.jsp");
		return SUCCESS;
	}

	// 添加订单操作
	public void dingdanadd2() throws IOException {
		HttpServletRequest request = this.getRequest();

		String sjname = request.getParameter("sjname");
		String phone = request.getParameter("phone");
		String beizhu = request.getParameter("beizhu");
		String address = request.getParameter("address");

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("qiantai");

		Dingdan bean = new Dingdan();

		bean.setAddress(address);
		bean.setBeizhu(beizhu);
		bean.setCreatetime(Util.getTime());
		bean.setDeletestatus(0);
		String orderid = new Date().getTime() + "";
		bean.setOrderid(orderid);
		bean.setPhone(phone);
		bean.setSjname(sjname);
		bean.setStatus("未处理");
		bean.setUser(user);

		List<Gouwuche> list = gouwucheDao.selectBeanList(0, 9999, " where user.id=" + user.getId());
		StringBuffer sb = new StringBuffer();
		double zongjia = 0;
		for (Gouwuche g : list) {
			double price = 0;

			price = Double.parseDouble(g.getCaipin().getJiage());

			sb.append(" 餐饮名： " + g.getCaipin().getMingchen() + ",购买数量:" + g.getSl() + ",单价" + price + ",￥小计"
					+ Arith.mul(g.getSl(), price));

			Caipin caipin = g.getCaipin();
			caipin.setBuy(caipin.getBuy() + g.getSl());

			caipinDao.updateBean(caipin);

			gouwucheDao.deleteBean(g);

			zongjia = zongjia + (Arith.mul(g.getSl(), price));

		}

		bean.setXiangqing(sb.toString());
		bean.setZongjia(zongjia);
		dingdanDao.insertBean(bean);
		PrintWriter writer = this.getPrintWriter();
		writer.print(
				"<script  language='javascript'>alert('操作成功');window.location.href='indexmethod!dingdanlist'; </script>");

	}

	// 查看订单列表
	public String dingdanlist() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("qiantai");
		if (user == null) {

			writer.print(
					"<script  language='javascript'>alert('请先登录');window.location.href='indexmethod!login'; </script>");
			return null;
		}

		String orderid = request.getParameter("orderid");
		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		if (orderid != null && !"".equals(orderid)) {

			sb.append("orderid like '%" + orderid + "%'");
			sb.append(" and ");
			request.setAttribute("orderid", orderid);
		}

		sb.append(" user.id=" + user.getId() + " and deletestatus=0 order by id desc ");

		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}

		long total = dingdanDao.selectBeanCount(where.replaceAll("order by id desc", ""));
		List<Dingdan> list = dingdanDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where);
		request.setAttribute("list", list);
		String pagerinfo = Pager.getPagerNormal((int) total, pagesize, currentpage, "indexmethod!dingdanlist",
				"共有" + total + "条记录");
		request.setAttribute("pagerinfo", pagerinfo);

		request.setAttribute("list", list);
		request.setAttribute("url", "indexmethod!dingdanlist");
		request.setAttribute("url2", "indexmethod!dingdan");
		request.setAttribute("title", "我的订单");
		this.setUrl("dingdanlist.jsp");
		return SUCCESS;
	}

	// 跳转到订单详细信息页面
	public String dingdanupdate3() {
		HttpServletRequest request = this.getRequest();

		String id = request.getParameter("id");
		Dingdan bean = dingdanDao.selectBean(" where id= " + id);
		request.setAttribute("bean", bean);
		this.setUrl("dingdanupdate3.jsp");
		return SUCCESS;
	}

	// 取消订单操作
	public void dingdandelete() throws IOException {
		HttpServletRequest request = this.getRequest();
		PrintWriter writer = this.getPrintWriter();

		Dingdan bean = dingdanDao.selectBean(" where id= " + request.getParameter("id"));

		bean.setStatus("取消订单");

		dingdanDao.updateBean(bean);

		writer.print(
				"<script  language='javascript'>alert('操作成功');window.location.href='indexmethod!dingdanlist'; </script>");

	}

	private PeisongDao peisongDao;

	public PeisongDao getPeisongDao() {
		return peisongDao;
	}

	public void setPeisongDao(PeisongDao peisongDao) {
		this.peisongDao = peisongDao;
	}

	// 配送信息列表
	public String peisonglist() {
		HttpServletRequest request = this.getRequest();

		String dingdanid = request.getParameter("dingdanid");

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		sb.append("   dingdan.id=" + dingdanid + " and  deletestatus=0  order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = peisongDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", peisongDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo",
				Pager.getPagerNormal(total, pagesize, currentpage, "indexmethod!peisonglist", "共有" + total + "条记录"));
		request.setAttribute("url", "indexmethod!peisonglist");
		request.setAttribute("url2", "indexmethod!peisong");
		this.setUrl("peisonglist.jsp");
		request.setAttribute("title", "配送查询");
		return SUCCESS;

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

		StringBuffer sb = new StringBuffer();
		sb.append(" where ");

		sb.append("   deletestatus=0  order by id desc ");
		String where = sb.toString();

		int currentpage = 1;
		int pagesize = 10;
		if (request.getParameter("pagenum") != null) {
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		}
		int total = cantingDao.selectBeanCount(where.replaceAll(" order by id desc ", ""));
		request.setAttribute("list", cantingDao.selectBeanList((currentpage - 1) * pagesize, pagesize, where));
		request.setAttribute("pagerinfo",
				Pager.getPagerNormal(total, pagesize, currentpage, "indexmethod!cantinglist", "共有" + total + "条记录"));
		request.setAttribute("url", "indexmethod!cantinglist");
		request.setAttribute("url2", "indexmethod!canting");
		this.setUrl("cantinglist.jsp");
		request.setAttribute("title", "餐厅信息列表");
		return SUCCESS;

	}

	// 跳转到查看餐厅页面
	public String cantingupdate3() {
		HttpServletRequest request = this.getRequest();

		String id = request.getParameter("id");
		Canting bean = cantingDao.selectBean(" where id= " + id);
		request.setAttribute("bean", bean);

		request.setAttribute("title", "查看餐厅信息");
		this.setUrl("cantingupdate3.jsp");
		return SUCCESS;
	}

}
