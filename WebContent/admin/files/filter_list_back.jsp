<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>滤清器数据管理</title>
<link href="../css/css.css" rel="stylesheet" type="text/css" />
<link href="../css/style.css" rel="stylesheet" type="text/css" />
</head>
<s:head/>
<body>
<a href="#">添加滤清器</a>&nbsp;&nbsp;<a href="#">查询滤清器</a>
<form action="filter_add.action" method="post">
<table width="98%" border="0" cellpadding="0" cellspacing="0"
	class="CContent">
	<tr>
		<th class="tablestyle_title" colspan="3">添加滤清器</th>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align:left">&nbsp;</td>
		<td style="width:10%; align:left">汽车品牌</td>
		<td style="width:87%; align:left"><s:select name="brandId" list="#request.brands" listKey="id" listValue="name" value="brandId" onchange="changeBrand();" theme="simple"></s:select>&nbsp;<span class="red">*</span></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width:10%; align:left">汽车型号</td>
		<td style="align: left"><s:select name="styleId" list="#request.styles" listKey="id" listValue="name" value="styleId" onchange="changeStyleOrSupply();" theme="simple"></s:select>&nbsp;<span class="red">*</span></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width:10%; align:left">供应商名称</td>
		<td style="align: left"><s:select name="supplyId" list="#request.supplies" listKey="id" listValue="name" value="supplyId" theme="simple" onchange="changeStyleOrSupply();" theme="simple"></s:select>&nbsp;<span class="red">*</span></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width:10%; align:left">空气滤清器</td>
		<td style="align: left"><s:textfield name="filter.air" cssStyle="width:150px" theme="simple" theme="simple"></s:textfield></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width:10%; align:left">机油滤清器</td>
		<td style="align: left"><s:textfield name="filter.machineOil" cssStyle="width:150px" theme="simple" theme="simple"></s:textfield></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width:10%; align:left">燃油滤清器</td>
		<td style="align: left"><s:textfield name="filter.fuelOil" cssStyle="width:150px" theme="simple" theme="simple"></s:textfield></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width:10%; align:left">空调滤(标准型)</td>
		<td style="align: left"><s:textfield name="filter.airConditionStd" cssStyle="width:150px" theme="simple" theme="simple"></s:textfield></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width:10%; align:left">空调滤(活性炭)</td>
		<td style="align: left"><s:textfield name="filter.airConditionCarbon" cssStyle="width:150px" theme="simple" theme="simple"></s:textfield></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td colspan="2" style="align: center"><input type="submit" value="保存"></input></td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
<p></p>

<script type="text/javascript">
function changeBrand() {
	var form = document.forms[0];
	form.action = "filter_changeBrand.action";
	form.submit();
}

function changeStyleOrSupply() {
	var form = document.forms[0];
	form.action = "filter_changeStyleOrSupply.action";
	form.submit();
}
</script>
</body>
</html>