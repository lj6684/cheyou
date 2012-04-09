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
<form action="supply_add.action" method="post" enctype="multipart/form-data">
<table width="98%" border="0" cellpadding="0" cellspacing="0"
	class="CContent">
	<tr>
		<th class="tablestyle_title" colspan="3">添加供货商品牌</th>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 100px; align: left">供货商品牌名称:
		</td>
		<td style="align:left"><input type="text"
			name="supplyName" style="width: 150px"></input>&nbsp;<span class="red">*</span></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 100px; align: left">供货商图片:</td>
		<td style="align: left">&nbsp;<input type="file"
			name="img"></input>
		</td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td colspan="2" style="align:center"><input type="submit" value="添加"/>
		</td>
	</tr>
</table>
</form>
<p></p>
<table width="98%" border="0" cellpadding="4" cellspacing="1"
	bgcolor="#464646" class="newfont03">
	<tr class="CTitle">
		<td height="22" colspan="3" align="center" style="font-size: 16px">已登记供货商品牌列表</td>
	</tr>
	<tr bgcolor="#EEEEEE">
		<td width="3%" align="center" height="30">No.</td>
		<td width="20%">品牌名称</td>
		<td>操作</td>
	</tr>
	<s:iterator value="#request.supplies" id="supply" status="st">
	<tr bgcolor="#FFFFFF">
		<td height="20"><s:property value="#st.count"></s:property></td>
		<td><img src="../../<s:property value="img"/>"/> <s:property value="name"/></td>
		<td><a href="supply_view.action?id=<s:property value="id"/>">修改</a>&nbsp;|&nbsp;<a href="supply_delete.action?id=<s:property value="id"/>">删除</a></td>
	</tr>
	</s:iterator>
</table>
</body>
</html>