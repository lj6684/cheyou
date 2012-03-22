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
<body>
<form action="styleFilter_query.action" method="post">
<table width="98%" border="0" cellpadding="0" cellspacing="0"
	class="CContent">
	<tr>
		<th class="tablestyle_title" colspan="2">滤清器数据管理</th>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">汽车品牌:&nbsp;<s:select name="brandId" list="#request.brands" listKey="id" listValue="name" value="brandId" theme="simple" onchange="change();"></s:select>&nbsp;<span class="red">*</span></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">供应商名称:&nbsp;<s:select name="supplyId" list="#request.supplies" listKey="id" listValue="name" value="supplyId" theme="simple" onchange="change();"></s:select>&nbsp;<span class="red">*</span></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td><input type="submit" value="查询"></input></td>
	</tr>
</table>
</form>
<p></p>
<table width="98%" border="0" cellpadding="4" cellspacing="1"
	bgcolor="#464646" class="newfont03">
	<tr class="CTitle">
		<td height="22" colspan="8" align="center" style="font-size: 16px">车型列表</td>
	</tr>
	<tr bgcolor="#EEEEEE">
		<td width="3%" align="center" height="30">No.</td>
		<td width="30%">车型描述</td>
		<td width="10%">空气滤清器</td>
		<td width="10%">机油滤清器</td>
		<td width="10%">燃油滤清器</td>
		<td width="10%">空调滤(标准型)</td>
		<td width="10%">空调滤(活性炭)</td>
		<td>操作</td>
	</tr>
	<s:iterator value="#request.styleFilters" id="styleFilter" status="st">
	<tr bgcolor="#FFFFFF">
		<td height="20"><s:property value="#st.count"></s:property></td>
		<td><s:property value="styleName"/></td>
		<td><s:property value="air"/></td>
		<td><s:property value="machineOil"/></td>
		<td><s:property value="fuelOil"/></td>
		<td><s:property value="airConditionStd"/></td>
		<td><s:property value="airConditionCarbon"/></td>
		<td><a href="style_view.action?id=<s:property value="id"/>">修改</a>&nbsp;|&nbsp;<a href="style_delete.action?id=<s:property value="id"/>">删除</a></td>
	</tr>
	</s:iterator>
</table>

<script type="text/javascript">
function change() {
	var form = document.forms[0];
	form.action = "styleFilter_change.action";
	form.submit();
}
</script>
</body>
</html>