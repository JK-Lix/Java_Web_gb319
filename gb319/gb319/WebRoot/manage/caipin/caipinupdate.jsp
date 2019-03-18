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


<script language="javascript" type="text/javascript">

function checkform()
{
	 
	

	if (document.getElementById('mingchenid').value=="")
	{
		alert("菜品名称不能为空");
		return false;
	}
	
	var v1 = document.getElementById("yuanjiaid").value;
	var v2 = document.getElementById("jiageid").value;
	if(v1==""){
		alert('原价不能为空');
		return false;
	}
	if(v2==""){
		alert('优惠价不能为空');
		return false;
	}
	
	
	
	 if(isNaN(v1)){ //数字 
	 	alert('原价必须是数字');
		return false;
	 }
	 if(isNaN(v2)){ //数字 
	 	alert('优惠价必须是数字');
		return false;
	 }
	 if(v1<0){
		alert('原价不能为负数');
		return false;
	}
	
	 if(v2<0){
		alert('优惠价不能是负数');
		return false;
	}
	
	
	 
	 if(eval(v1)<=eval(v2)){
	 	alert('原价必须大于优惠价');
		return false;
	 }
	
	return true;
	
}


</script>

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
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="img/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6">
        
        <form action="${url }" method="post" onsubmit="return checkform()" enctype="multipart/form-data">
        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE" >

          
          <tr>
            
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
            <div align="center" class="STYLE2 STYLE1">
            菜品名称:
            </div>
            </td>
            <td height="18" bgcolor="#FFFFFF">
            <div align="left" class="STYLE2 STYLE1">
            <input  type="text" name="mingchen"  id='mingchenid'  size="30" value="${bean.mingchen }"  />
            </div>
            </td>
           
          </tr>
          
            
          
            <tr>
          <td height="18" bgcolor="#FFFFFF" class="STYLE2">
            <div align="center" class="STYLE2 STYLE1">
            菜品类别:
            </div>
            </td>
            <td height="18" bgcolor="#FFFFFF">
            <div align="left" class="STYLE2 STYLE1">
          <select name="leibieid">
                    	<c:forEach items="${leibielist}" var="leibie">
                    	<option value="${leibie.id }" <c:if test="${bean.leibie.id==leibie.id }">selected</c:if> >${leibie.lname }</option>
                    	</c:forEach>
                    	
                    	</select>
            </div>
            </td>
           
          </tr>
          
          
          
          <tr>
          <td height="18" bgcolor="#FFFFFF" class="STYLE2">
            <div align="center" class="STYLE2 STYLE1">
            菜品图片:
            </div>
            </td>
            <td height="18" bgcolor="#FFFFFF">
            <div align="left" class="STYLE2 STYLE1">
           <img src="<%=basePath %>uploadfile/${bean.tupian }" width="200" height="200"/> 
            </div>
            </td>
           
          </tr>
          
          
            <tr>
          <td height="18" bgcolor="#FFFFFF" class="STYLE2">
            <div align="center" class="STYLE2 STYLE1">
            重新上传菜品图片（不上传为空）:
            </div>
            </td>
            <td height="18" bgcolor="#FFFFFF">
            <div align="left" class="STYLE2 STYLE1">
            <input  type="file" name="uploadfile"  id='uploadfileid'  size="30"  />
            </div>
            </td>
           
          </tr>
          
            <tr>
          <td height="18" bgcolor="#FFFFFF" class="STYLE2">
            <div align="center" class="STYLE2 STYLE1">
            菜品原价:
            </div>
            </td>
            <td height="18" bgcolor="#FFFFFF">
            <div align="left" class="STYLE2 STYLE1">
            <input  type="text" name="yuanjia"  id='yuanjiaid'  size="30"   value="${bean.yuanjia }"/>
            </div>
            </td>
           
          </tr>
          
            <tr>
          <td height="18" bgcolor="#FFFFFF" class="STYLE2">
            <div align="center" class="STYLE2 STYLE1">
            菜品优惠价:
            </div>
            </td>
            <td height="18" bgcolor="#FFFFFF">
            <div align="left" class="STYLE2 STYLE1">
            <input  type="text" name="jiage"  id='jiageid'  size="30" value="${bean.jiage }" />
            </div>
            </td>
           
          </tr>
          
            <tr>
          <td height="18" bgcolor="#FFFFFF" class="STYLE2">
            <div align="center" class="STYLE2 STYLE1">
            描述:
            </div>
            </td>
            <td height="18" bgcolor="#FFFFFF">
            <div align="left" class="STYLE2 STYLE1">
            <textarea class="input" rows="7" cols="80"  name="miaoshu"  >${bean.miaoshu }</textarea>
            </div>
            </td>
           
          </tr>
          
          
          
          
         
		  <tr>
            
            <td height="18" bgcolor="#FFFFFF" class="STYLE2">
            <div align="center" class="STYLE2 STYLE1">
              操作：
            </div>
            </td>
            <td height="18" bgcolor="#FFFFFF">
            <div align="left" class="STYLE2 STYLE1">
            <input type="submit" value="提交" style="width: 60px" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input  onclick="javascript:history.go(-1);" style="width: 60px" type="button" value="返回" />
            </div>
            </td>
           
          </tr>
		  
         
          
        </table>
        </form>
        </td>
        <td width="9" background="img/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  
  
  
  
</table>
</body>
</html>