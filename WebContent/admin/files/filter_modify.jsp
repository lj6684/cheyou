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
	<s:hidden name="brandId"></s:hidden>
	<s:hidden name="supplyId"></s:hidden>
<table width="98%" border="0" cellpadding="0" cellspacing="0" id="datatable">
	<tr>
		<th class="tablestyle_title" colspan="4">滤清器</th>
	</tr>
	<tr style="height: 50px">
		<td style="width:10px; align:left">&nbsp;</td>
		<td style="width:100px; align:right;">汽车品牌:</td>
		<td style="width:400px; align:left;"><s:property value="brandName"/></td>
		<td style="width:auto; align:left;">&nbsp;</td>
	</tr>
	<tr style="height: 50px">
		<td style="width:10px; align:left">&nbsp;</td>
		<td style="width:100px; align:right;">汽车型号:</td>
		<td style="width:400px; align:left;"><s:property value="styleFullName"/></td>
		<td style="width:auto; align:left;">&nbsp;</td>
	</tr>
	<tr style="height: 50px">
		<td style="width:10px; align:left">&nbsp;</td>
		<td style="width:100px; align:right;">供应商名称:</td>
		<td style="width:400px; align:left;"><s:property value="supplyName"/></td>
		<td style="width:auto; align:left;">&nbsp;</td>
	</tr>
	<tr style="height: 50px">
		<td style="width:10px; align:left">&nbsp;</td>
		<td style="width:100px; align:right;">空气滤清器:</td>
		<td style="width:400px; align:left;"><s:textfield size="50" name="filter.air" theme="simple"></s:textfield></td>
		<td style="width:auto; align:left;">
			<s:if test="hasType0">
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=0&act=update">修改描述</s:a>&nbsp;&nbsp;
				<s:a href="#">删除描述</s:a>
			</s:if>
			<s:else>
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=0&act=add">添加描述</s:a>
			</s:else>
		</td>
	</tr>
	<tr style="height: 50px">
		<td style="width:10px; align:left">&nbsp;</td>
		<td style="width:100px; align:right;">机油滤清器:</td>
		<td style="width:400px; align:left;"><s:textfield size="50" name="filter.machineOil" theme="simple"></s:textfield></td>
		<td style="width:auto; align:left;">
			<s:if test="hasType1">
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=1&act=update">修改描述</s:a>&nbsp;&nbsp;
				<s:a href="#">删除描述</s:a>
			</s:if>
			<s:else>
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=1&act=add">添加描述</s:a>
			</s:else>
		</td>
	</tr>
	<tr style="height: 50px">
		<td style="width:10px; align:left">&nbsp;</td>
		<td style="width:100px; align:right;">燃油滤清器:</td>
		<td style="width:400px; align:left;"><s:textfield size="50" name="filter.fuelOil" theme="simple"></s:textfield></td>
		<td style="width:auto; align:left;">
			<s:if test="hasType2">
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=2&act=update">修改描述</s:a>&nbsp;&nbsp;
				<s:a href="#">删除描述</s:a>
			</s:if>
			<s:else>
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=2&act=add">添加描述</s:a>
			</s:else>
		</td>
	</tr>
	<tr style="height: 50px">
		<td style="width:10px; align:left">&nbsp;</td>
		<td style="width:100px; align:right;">空调滤(标准):</td>
		<td style="width:400px; align:left;"><s:textfield size="50" name="filter.airConditionStd" theme="simple"></s:textfield></td>
		<td style="width:auto; align:left;">
			<s:if test="hasType3">
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=3&act=update">修改描述</s:a>&nbsp;&nbsp;
				<s:a href="#">删除描述</s:a>
			</s:if>
			<s:else>
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=3&act=add">添加描述</s:a>
			</s:else>
		</td>
	</tr>
	<tr style="height: 50px">
		<td style="width:10px; align:left">&nbsp;</td>
		<td style="width:100px; align:right;">空调滤(活性炭):</td>
		<td style="width:400px; align:left;"><s:textfield size="50" name="filter.airConditionCarbon" theme="simple"></s:textfield></td>
		<td style="width:auto; align:left;">
		<s:if test="hasType4">
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=4&act=update">修改描述</s:a>&nbsp;&nbsp;
				<s:a href="#">删除描述</s:a>
			</s:if>
			<s:else>
				<s:a href="filter_showDescp.action?brandId=%{brandId}&supplyId=%{supplyId}&styleId=%{styleId}&filterId=%{filterId}&type=4&act=add">添加描述</s:a>
			</s:else>
		</td>
	</tr>
	<tr style="height: 50px">
		<td>&nbsp;</td>
		<td colspan="2"><input type="submit" value="保存"></input></td>
		<td>&nbsp;</td>
	</tr>
</table>
</form>
</body>
</html>