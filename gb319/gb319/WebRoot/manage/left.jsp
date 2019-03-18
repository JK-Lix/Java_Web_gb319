<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head><base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>无标题文档</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style></head>

<body>
<table width="173" height="100%" border="0" cellpadding="0" cellspacing="0" style="table-layout:fixed;">
  <tr>
    <td style="width:4px; background-image:url(images/main_16.gif)">&nbsp;</td>
    <td width="169" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="20" background="images/main_11.gif">&nbsp;</td>
      </tr>
      <tr>
        <td>
        <div align="center">
        <br/>
        
       <a href="method!userlist" target="I2">会员管理</a>
        <br/><br/>
        <a href="method!leibielist" target="I2">菜品类别管理</a>
        <br/><br/>
         <a href="method!caipinlist" target="I2">菜品管理</a>
        <br/><br/>
         <a href="method!dingdanlist" target="I2">订单管理</a>
        <br/><br/>
         <a href="method!dingdanlist2" target="I2">配送管理</a>
        <br/><br/>
         <a href="method!cantinglist" target="I2">餐厅信息管理</a>
        <br/><br/>
         
        
     
        </div>
        </td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>