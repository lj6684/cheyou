<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>汽车品牌管理</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="brand_save.action" method="post">
	<input type="hidden" name="id" value="<s:property value="id"/>"/>
<table width="98%" border="0" cellpadding="0" cellspacing="0"
	class="CContent">
	<tr>
		<th class="tablestyle_title" colspan="2">修改品牌信息</th>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">品牌名称:&nbsp;<input type="text"
			name="brandName" style="width: 150px" value="<s:property value="brandName"/>"/>&nbsp;<span class="red">*</span>&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="submit" value="保存"></input></td>
	</tr>
</table>
</form>
</body>
</html>