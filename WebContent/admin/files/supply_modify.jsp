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
<script type="text/javascript" src="../../js/jquery-1.7.2.min.js"></script>
<SCRIPT type="text/javascript">
$(function() {
	$("#logoCheckbox").click(function () {
		if($(this).is(":checked")) {
			$("#fileItem").show();
		} else {
			$("#fileItem").hide();
		}
	});
});
</SCRIPT>
</head>
<body>
<form action="supply_save.action" method="post" enctype="multipart/form-data">
	<input type="hidden" name="id" value="<s:property value="id"/>"/>
<table width="98%" border="0" cellpadding="0" cellspacing="0"
	class="CContent">
	<tr>
		<th class="tablestyle_title" colspan="2">修改供货商信息</th>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">供货商序号:&nbsp;<input type="text"
			name="orderIndex" style="width: 150px" value="<s:property value="orderIndex"/>"/>&nbsp;<span class="red">*</span>
		</td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">供货商名称:&nbsp;<input type="text"
			name="supplyName" style="width: 150px" value="<s:property value="supplyName"/>"/>&nbsp;<span class="red">*</span>
		&nbsp;&nbsp;<img src="../../<s:property value='supplyImg'/>"/></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left"><input type="checkbox" name="updateImg" value="1" id="logoCheckbox"/>&nbsp;更换LOGO&nbsp;&nbsp;<span style="display:none" id="fileItem"><input type="file" name="img" /></span></td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left">
		<a href="supply_showDescp.action?id=<s:property value="id"/>"/>修改描述</a>
		</td>
	</tr>
	<tr style="height: 50px">
		<td style="width: 1%; align: left">&nbsp;</td>
		<td style="width: 70%; align: left"><input type="submit" value="保存"></input></td>
	</tr>
</table>
</form>
</body>
</html>