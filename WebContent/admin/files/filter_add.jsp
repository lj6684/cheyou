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
<form action="filter_add.action" method="post">
	<s:hidden name="act" value="%{act}"></s:hidden>
	<s:hidden name="filter.id" value="%{filterId}"></s:hidden>
	<s:hidden name="filter.brandId" value="%{brandId}"></s:hidden>
	<s:hidden name="filter.supplyId" value="%{supplyId}"></s:hidden>
	<s:hidden name="filter.styleId" value="%{styleId}"></s:hidden>
<table width="98%" border="0" cellpadding="0" cellspacing="0" class="CContent">
	<tr>
		<th class="tablestyle_title" colspan="2">滤清器</th>
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
		<td style="width: 70%; align: left">供应商名称:&nbsp;<s:property value="supplyName"/></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">空气滤清器:&nbsp;<s:textfield name="filter.air" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">机油滤清器:&nbsp;<s:textfield name="filter.machineOil" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">燃油滤清器:&nbsp;<s:textfield name="filter.fuelOil" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">空调滤(标准):&nbsp;<s:textfield name="filter.airConditionStd" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">空调滤(活性炭):&nbsp;<s:textfield name="filter.airConditionCarbon" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td><input type="submit" value="保存"></input></td>
	</tr>
</table>
</form>
</body>
</html>