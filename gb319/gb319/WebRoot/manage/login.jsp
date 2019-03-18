<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@page import="com.util.Util"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
Util.init(request);
%>

<html>
	<head><base href="<%=basePath%>"><base href="<%=basePath%>">

		<title>伞幺玖网上订餐系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="css/style.css" />
		<style type="">
			.s1{
				color:red;
				font-style:italic;
				font-size:15px;
			}
		</style>
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
						<c1:date/>
			
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								登录主界面
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						伞幺玖网上订餐系统 
						
					</h1>
					<form action="method!login" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									用户名:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username" style="width: 200px;" />
									
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									密码:
								</td>
								<td valign="middle" align="left">
									<input type="password" class="inputgri" name="password" style="width: 200px;" />
								</td>
							</tr>
							
							
						</table>
						
						<span class="s1"></span>
						<p>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="submit" class="button" value="确认 &raquo;" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<a href=".">返回首页</a>
							
						</p>
					</form>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">
					伞幺玖Web开发团队版权所有
				</div>
</div>
		</div>
	</body>
</html>
