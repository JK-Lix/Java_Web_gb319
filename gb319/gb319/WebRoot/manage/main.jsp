<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>伞幺玖网上订餐系统</title>
</head>
<body>

<div style="font-size: 50px;font-weight: bold;">
<%-- &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
欢迎${user.username }使用本系统
<br/> --%>
<img src="images/beijingtu.jpg" width="1340px" height="585px"   alt="图片加载失败！" />
</div>
</body>
</html>
