<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>火花塞管理</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<form action="sparkPlug_add.action" method="post">
	<s:hidden name="act" value="%{act}"></s:hidden>
	<s:hidden name="sparkPlug.Id" value="%{sparkPlugId}"></s:hidden>
	<s:hidden name="sparkPlug.brandId" value="%{brandId}"></s:hidden>
	<s:hidden name="sparkPlug.supplyId" value="%{supplyId}"></s:hidden>
	<s:hidden name="sparkPlug.styleId" value="%{styleId}"></s:hidden>
<table width="98%" border="0" cellpadding="0" cellspacing="0" class="CContent">
	<tr>
		<th class="tablestyle_title" colspan="2">火花塞</th>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">汽车品牌:&nbsp;<s:property value="brandName"/></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">汽车型号:&nbsp;<s:property value="styleName"/></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">排量:&nbsp;<s:textfield name="sparkPlug.outputVolumn" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">供应商名称:&nbsp;<s:property value="supplyName"/></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">发动机:&nbsp;<s:textfield name="sparkPlug.motor" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">出厂年份:&nbsp;<s:textfield name="sparkPlug.year" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">备注:&nbsp;<s:textfield name="sparkPlug.remark" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">迅能编号:&nbsp;<s:textfield name="sparkPlug.xunSn" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">迅能类型:&nbsp;<s:textfield name="sparkPlug.xunType" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">超能编号:&nbsp;<s:textfield name="sparkPlug.chaoSn" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">超能类型:&nbsp;<s:textfield name="sparkPlug.chaoType" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">瑞能编号:&nbsp;<s:textfield name="sparkPlug.ruiSn" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">瑞能类型:&nbsp;<s:textfield name="sparkPlug.ruiType" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td><input type="submit" value="保存"></input></td>
	</tr>
</table>
</form>
</body>
</html>