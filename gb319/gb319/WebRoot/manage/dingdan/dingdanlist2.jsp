<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><base href="<%=basePath%>">
<base href="<%=basePath %>"/>
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
.STYLE1 {font-size: 12px}
.STYLE4 {
	font-size: 12px;
	color: #1F4A65;
	font-weight: bold;
}

a:link {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;

}
a:visited {
	font-size: 12px;
	color: #06482a;
	text-decoration: none;
}
a:hover {
	font-size: 12px;
	color: #FF0000;
	text-decoration: underline;
}
a:active {
	font-size: 12px;
	color: #FF0000;
	text-decoration: none;
}
.STYLE7 {font-size: 12}

-->
</style>


</head>

<body>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="img/tab_03.gif" width="15" height="30" /></td>
        <td background="img/tab_05.gif"><img src="img/311.gif" width="16" height="16" /> <span class="STYLE4">${title }</span></td>
        <td width="14"><img src="img/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>

  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="img/tab_20.gif" width="15" height="29" /></td>
        <td background="img/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="100%" height="29">
            <form action="${url }" method="post">

订单号：<input name="orderid" type="text"  value="${orderid }">
              
           

<input type="submit"  value="查询"/>
</form>
            </td>
            
          </tr>
        </table></td>
        <td width="14"><img src="img/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
  
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="img/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6">
        
        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE" >

          <tr>
            <td  height="26" background="img/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">订单号</div></td>
            <td  height="26" background="img/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">收货人姓名</div></td>
            <td  height="26" background="img/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">收货人手机</div></td>
    
            <td  height="26" background="img/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">收货人地址</div></td>
            <td  height="26" background="img/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">生成时间</div></td>
    
            <td  height="26" background="img/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">订单状态</div></td>
            <td  height="26" background="img/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">总价</div></td>
           
			 
			 		
            <td  height="18" background="img/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">操作</div></td>

          </tr>
          
          <c:forEach items="${list}"  var="bean">
          <tr>
           
           
            
             <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">
            ${bean.orderid }&nbsp;
            </div></td>
             <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">
            ${bean.sjname }&nbsp;
            </div></td>
             <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">
            ${bean.phone }&nbsp;
            </div></td>
             <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">
            ${bean.address }&nbsp;
            </div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">
            ${bean.createtime }&nbsp;
            </div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">
            ${bean.status }&nbsp;
            </div></td>
            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">
            ${bean.zongjia }&nbsp;
            </div></td>
           
            
           
             
            
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">
          <a href="${url2 }update3?id=${bean.id }">查看</a> &nbsp; &nbsp; &nbsp;
  	
  	 <a href="method!peisongadd?dingdanid=${bean.id }">录入配送信息</a> &nbsp; &nbsp; &nbsp;
  	
  	
  	 <a href="method!peisonglist?dingdanid=${bean.id }">配送信息查看</a> &nbsp; &nbsp; &nbsp;
  	
  
        
  		  
            </div></td>
            
          </tr>
          </c:forEach>
          
         
          
        </table></td>
        <td width="9" background="img/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="img/tab_20.gif" width="15" height="29" /></td>
        <td background="img/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="21%" height="29">${pagerinfo }</td>
            
          </tr>
        </table></td>
        <td width="14"><img src="img/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
  
  
</table>
</body>
</html>