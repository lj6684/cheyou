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
<form action="spark_add.action" method="post">
	<s:hidden name="act" value="%{act}"></s:hidden>
	<s:hidden name="spark.id" value="%{sparkId}"></s:hidden>
	<s:hidden name="spark.brandId" value="%{brandId}"></s:hidden>
	<s:hidden name="spark.supplyId" value="%{supplyId}"></s:hidden>
	<s:hidden name="spark.styleId" value="%{styleId}"></s:hidden>
	<s:hidden name="brandId"></s:hidden>
	<s:hidden name="supplyId"></s:hidden>
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
		<td style="width: 70%; align: left">排量:&nbsp;<s:textfield name="spark.outputVolumn" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">供应商名称:&nbsp;<s:property value="supplyName"/></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">发动机:&nbsp;<s:textfield name="spark.motor" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">出厂年份:&nbsp;<s:textfield name="spark.year" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">备注:&nbsp;<s:textfield name="spark.remark" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">迅能编号:&nbsp;<s:textfield name="spark.xunSn" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">迅能类型:&nbsp;<s:textfield name="spark.xunType" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">超能编号:&nbsp;<s:textfield name="spark.chaoSn" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">超能类型:&nbsp;<s:textfield name="spark.chaoType" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">瑞能编号:&nbsp;<s:textfield name="spark.ruiSn" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">瑞能类型:&nbsp;<s:textfield name="spark.ruiType" theme="simple"></s:textfield> </td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td><input type="submit" value="保存"></input></td>
	</tr>
</table>
</form>
</body>
</html>