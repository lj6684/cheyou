<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>马勒MAHLE三滤型号对照表 机滤|空滤|汽滤|空调滤芯MAHLE_车主网</title>
<meta name="keywords" content="博世,博世三滤,博世机滤,博世空滤 博世三滤型号对照表"/>
<meta name="description" content="车主网提供博世三滤型号对照表,博世三滤查询"/>
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<!-- jQuery -->
<link href="../css/ui-lightness/jquery-ui-autocomplete.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-autocomplete.js"></script>
<script type="text/javascript">
	// Ajax请求AutoComplet数据
	$(function() {
		$.getJSON("../query_init.action",
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

	function clearSuggest() {
		if($("#queryStr").val() == "请输入您的车型，如速腾") {
			$("#queryStr").val("");
			$("#queryStr").css("color", "black");
		}
	}
</script>
</head>
<body>
<div class="wrap">
	<div id="topbar"/></div>
	<div id="logobar"><a href="./"><img src="../images/mahle.gif" alt="马勒logo" border="0"/></a><a href="../"><img src="../images/logo_fenzhan.gif" alt="点击回首页" border="0"/></a></div>
	<!--search begin-->
	<form action="query_query.action" method="post" onsubmit="return checkInput();" id="form1">
	<div id="search_begin">
				
		<div class="accessory_item">
			<ul>
				<li id="nav1"><a href="/mahle/" class="current">马勒三滤<span></span></a></li>
				<li id="nav2"><a href="#">机油<span></span></a></li>
				<li id="nav2"><a href="#">火花塞<span></span></a></li>
				<li id="nav2"><a href="#">雨刷<span></span></a></li>
				<li id="nav2"><a href="#">刹车片<span></span></a></li>
			</ul>
			<div class="clear"></div>
		</div>
				
		<div class="brand_item">
			<s:set name="supplies" value="#{'2':'原厂号', '1':'博世BOSCH', '4':'马勒MAHLE', '3':'索菲玛SOFIMA', '5':'曼牌MANN', '6':'豹王'}"></s:set>
			<ul>
			<s:if test="supplyItem">
				<s:checkboxlist list="#supplies" name="supplyItem" theme="simple"></s:checkboxlist>
			</s:if>
			<s:else>
				<s:checkboxlist list="#supplies" name="supplyItem" theme="simple" value="{'2', '4'}"></s:checkboxlist>
			</s:else>
			</ul>
			<div class="clear"></div>
		</div>

		<div class="searchbar">
			<s:if test="queryStr != null">
				<input class="input_type" type="text" size="35" name="queryStr" id="queryStr" value="<s:property value='queryStr'/>"/>
			</s:if>
			<s:else>
				<input class="input_suggest" type="text" size="35" name="queryStr" id="queryStr" value="请输入您的车型，如速腾" onclick="clearSuggest();"/>
			</s:else>
			<input class="submit_btn" type="submit" value="">
			<div class="clear"></div>
		</div>
	</div>
	</form>
	
	<s:if test="#request.styles != null">
	<!--search result-->
	<div id="search_result">
		<!--new search_result area--->
		<s:iterator value="#request.styles" id="style" status="st">
		<table cellspacing="0" class="bigtable">
			<tr>
				<td class="brand"><img src="../<s:property value='brandImg'/>"/></td>
				<td class="title"><s:property value='styleName'/>&nbsp;<s:property value='styleOutter'/></td>
				<td class="others">发动机型号：<span class="fdj"><s:property value='styleMotor'/></span></td>
			</tr>
		</table>
		<table cellspacing="0" class="detailtb">
			<tr>
				<th width="80px" style="text-align:left;text-indent:8px;">品牌</th>
				<th width="130px">机油滤芯</th>
				<th width="130px">空气滤芯</th>
				<th width="130px">燃油滤芯</th>
				<th width="130px">空调滤(标准)</th>
				<th width="130px">空调滤(活性碳)</th>
				<th width="60px">&nbsp;</th>
			</tr>
			<s:iterator value="#request.orderSupplies" id="sp" status="sts">
			<tr>
				<td style="text-align:left;text-indent:4px;"><img src="../<s:property value='#request.filters[styleFullName][name].supplyImg'/>" align="absmiddle" alt="<s:property value='name'/>" border="0"></td>
				<td><s:if test="#request.filters[styleFullName][name].machineOil != null && #request.filters[styleFullName][name].machineOil != ''"><s:property value="#request.filters[styleFullName][name].machineOil"/></s:if><s:else>&nbsp;</s:else></td>
				<td><s:if test="#request.filters[styleFullName][name].air != null && #request.filters[styleFullName][name].air != ''"><s:property value="#request.filters[styleFullName][name].air"/></s:if><s:else>&nbsp;</s:else></td>
				<td><s:if test="#request.filters[styleFullName][name].fuelOil != null && #request.filters[styleFullName][name].fuelOil != ''"><s:property value="#request.filters[styleFullName][name].fuelOil"/></s:if><s:else>&nbsp;</s:else></td>
				<td><s:if test="#request.filters[styleFullName][name].airConditionStd != null && #request.filters[styleFullName][name].airConditionStd != ''"><s:property value="#request.filters[styleFullName][name].airConditionStd"/></s:if><s:else>&nbsp;</s:else></td>
				<td><s:if test="#request.filters[styleFullName][name].airConditionCarbon != null && #request.filters[styleFullName][name].airConditionCarbon != ''"><s:property value="#request.filters[styleFullName][name].airConditionCarbon"/></s:if><s:else>&nbsp;</s:else></td>
				<td class="td_rgt"><a href="../sanlv/<s:property value="#request.filters[styleFullName][name].filterId"/>/">详情>></a></td>
			</tr>
			</s:iterator>
		</table>

		<!--hr with many results, if only one item need remove hr-->
		<s:if test="!#st.last">
			<hr/>
		</s:if>
		</s:iterator>
	</div>
	</s:if>
	<div id="footer"/>车主网致力于为车主免费提供<b>汽车三滤 火花塞 雨刷片 刹车片</b>等易损件配件自助查询服务!<br/>
		车主网提供马勒三滤型号对照表[机滤 空滤 汽滤 空调滤芯]，仅供参考 <a href="../sitemap.html">三滤型号大全</a><br/>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F8923cc0b97ccc7b4825046ef57ccdbf3' type='text/javascript'%3E%3C/script%3E"));
</script>
</div>
</div>
</body>
</html>
