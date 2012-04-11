<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车主网</title>
<meta name="keywords" content=""/>
<meta name="description" content=""/>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/func.js"></script>
<!-- jQuery -->
<link href="css/ui-lightness/jquery-ui-autocomplete.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-autocomplete.js"></script>
<script type="text/javascript">
	// Ajax请求AutoComplet数据
	$(function() {
		$.getJSON("query_init.action",
			null,
			function(data) {
				$("#queryStr").autocomplete({
					source: data
				});			
			});
	});

	// 检查表单输入，不允许查询条件为空
	function checkInput () {
		var queryStr = $("#queryStr").val();
		if(queryStr == "") {
			return false;
		}
		var checked = false;
		$(":checkbox[name='supplyItem']").each(function () {
			if($(this).is(":checked")) {
				checked = true;
				return;
			}
		})

		return checked;
	}
</script>
</head>
<body>
<div class="wrap">
	<div id="topbar"/></div>
	<div id="logobar"><a href="index.jsp"><img src="images/logo.gif" border="0"/></a></div>
	<!--search begin-->
	<form action="query_query.action" method="post" onsubmit="return checkInput();" id="form1">
	<div id="search_begin">
		<div class="accessory_item">
			<ul>
				<li id="nav1"><a href="index.html" class="current">三滤+空调滤<span></span></a></li>
				<li id="nav2"><a href="#">机油<span></span></a></li>
				<li id="nav2"><a href="#">火花塞<span></span></a></li>
				<li id="nav2"><a href="#">雨刷<span></span></a></li>
				<li id="nav2"><a href="#">刹车片<span></span></a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<div class="brand_item">
			<s:set name="supplies" value="#{'2':'原厂', '1':'博世BOSCH', '4':'马勒MAHLE', '3':'索菲玛SOFIMA', '5':'曼牌MANN', '6':'箭牌'}"></s:set>
			<ul>
			<s:if test="supplyItem">
				<s:checkboxlist list="#supplies" name="supplyItem" theme="simple"></s:checkboxlist>
			</s:if>
			<s:else>
				<s:checkboxlist list="#supplies" name="supplyItem" theme="simple" value="{'1', '2', '3', '4'}"></s:checkboxlist>
			</s:else>
			</ul>
			<div class="clear"></div>
		</div>

		<div class="searchbar">
			<input class="input_type" type="text" size="35" name="queryStr" id="queryStr" value="<s:property value='queryStr'/>"/>
			<input class="submit_btn" type="submit" value="">
			<div class="clear"></div>
		</div>
	</div>
	</form>
	<s:if test="#request.styles != null">
	<!--search result-->
	<div id="search_result">
		<table cellspacing="0" id="bigtable">
			<tr>
				<th width="40px">品牌</th>
				<th width="120px">车型/参数</th>
				<th width="140px">发动机型号</th>
				<th width="100px">配件品牌</th>
				<th width="100px">机油滤芯</th>
				<th width="100px">空气滤芯</th>
				<th width="100px">燃油滤芯</th>
				<th width="100px">空调滤芯</th>
				<th width="100px">车友验证</th>
			</tr>
			<s:iterator value="#request.styles" id="style" status="st">
				<tr>
					<td><img src="<s:property value='brandImg'/>"/></td>
					<td><s:property value="styleName"/></td>
					<td>XXX</td>
					<td colspan="6">
						<table cellspacing="0" id="smalltable">
							<s:iterator value="#request.filters[styleName]" id="supply" status="sts">
							<tr>
								<td class="td_lft"><img src="<s:property value='value.supplyImg'/>" align="middle" alt="<s:property value='key'/>" border="0"></td>
								<td><s:if test="value.machineOil != null"><s:property value="value.machineOil"/></s:if><s:else>-</s:else></td>
								<td><s:if test="value.air != null"><s:property value="value.air"/></s:if><s:else>-</s:else></td>
								<td><s:if test="value.fuelOil != null"><s:property value="value.fuelOil"/></s:if><s:else>-</s:else></td>
								<td>
									<s:if test="value.airConditionStd != null"><s:property value="value.airConditionStd"/></s:if><s:else>-</s:else>
									<!--  
									<s:if test="value.airConditionCarbon != null"><s:property value="value.airConditionCarbon"/></s:if><s:else>-</s:else>
									-->
								</td>
								<td>&nbsp;</td>
							</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
			</s:iterator>
		</table>
	</div>
	</s:if>
	<div id="footer"/>Copyright &copy; 2012 Chezhu5.com <a href="#">提意见</a></div>
</div>
</body>
</html>