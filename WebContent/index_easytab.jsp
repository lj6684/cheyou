<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>车主网-汽车三滤 火花塞大全 雨刷型号等汽车易损件查询</title>
<meta name="keywords" content="三滤大全,三滤,火花塞,雨刷"/>
<meta name="description" content="车主网提供车主三滤型号查询、机油型号查询、火花塞型号查询等汽车易损件查询"/>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<style>
/* Example Styles for Demo */
.etabs {
	margin: 0px;
	padding: 0px;
}

.tab {
	display: inline-block;
	zoom: 1;
	*display: inline;
	background: #eee;
	border: solid 1px #999;
	border-bottom: none;
	-moz-border-radius: 4px 4px 0px 0px;
	-webkit-border-radius: 4px 4px 0px 0px;
}

.tab a {
	font-size: 14px;
	line-height: 2em;
	display: block;
	padding: 0px 20px;
	outline: none;
	color: #222A2E;
	text-decoration: none;
}

.tab a:hover {
	
}

.tab.active {
	background: #D9D9D9;
	padding-top: 6px;
	position: relative;
	top: 1px;
	border-color: #666;
}

.tab a.active {
	*font-weight: bold;
}

.tab-container .panel-container {
	background: #fff;
	border: solid #666 1px;
	padding: 10px;
	-moz-border-radius: 0px 4px 4px 4px;
	-webkit-border-radius: 0px 4px 4px 4px;
}

.panel-container {
	margin-bottom: 10px;
}

#filter-tab, #spark-tab, #engineoil-tab, #wipers-tab, #brakepads-tab {background:white;margin:0;padding:0;}
</style>
<!-- jQuery -->
<link href="css/ui-lightness/jquery-ui-autocomplete.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/jquery-ui-autocomplete.js"></script>
<script type="text/javascript" src="js/jquery.hashchange.min.js"></script>
<script type="text/javascript" src="js/jquery.easytabs.min.js"></script>
<script type="text/javascript">
	// Ajax请求AutoComplet数据
	$(function() {
		// 设置默认tab标签页
		//$("#tab-container").easytabs();
		var resultType = "<s:property value='#request.resultType'/>";
		if(resultType == "" || resultType == "filter") {
			$("#tab-container").easytabs("select", "#filter-tab");
		} else if(resultType == "spark") {
			$("#tab-container").easytabs("select", "#spark-tab");
		}
		
		$.getJSON("query_init.action",
			null,
			function(data) {
				$("#filterQueryStr").autocomplete({
					source: data
				});		
				$("#sparkQueryStr").autocomplete({
					source: data
				});	
			});
	});

	// 检查表单输入，不允许查询条件为空
	function checkInput () {		
		var inputObj;
		var checkObj;
		if($("#filter-tab").is(":visible")) {
			inputObj = $("#filterQueryStr");
			checkObj = $(":checkbox[name='filterSupplyItem']");
		} else if($("#spark-tab").is(":visible")) {
			inputObj = $("#sparkQueryStr");
			checkObj = $(":checkbox[name='sparkSupplyItem']");
		} else if($("#engineoil-tab").is(":visible")) {
			// todo
		} else if($("#wipers-tab").is(":visible")) {
			// todo
		} else if($("#brakepads-tab").is(":visible")) {
			// todo
		}
		
		// 判断查询条件不允许为空
		if(inputObj.val() == "") {
			return false;
		}
		// 判断供应商列表不允许为空
		var checked = false;
		checkObj.each(function () {
			if($(this).is(":checked")) {
				checked = true;
				return;
			}
		});
		// 判断不允许直接输入厂商名字进行查询
		var inputValue = inputObj.val();
		var reg = /大众|斯柯达|现代|别克|本田|丰田|福特|海马|起亚|尼桑|日产尼桑|雪佛兰|雪铁龙/;
		if(reg.test(inputValue)) {
			$("#search_suggest").show();
			return false;
		}
		

		return checked;
	}

	// 清除提示信息
	function clearSuggest() {
		var inputObj;
		if($("#filter-tab").is(":visible")) {
			inputObj = $("#filterQueryStr");
		} else if($("#spark-tab").is(":visible")) {
			inputObj = $("#sparkQueryStr");
		} else if($("#engineoil-tab").is(":visible")) {
			// todo
		} else if($("#wipers-tab").is(":visible")) {
			// todo
		} else if($("#brakepads-tab").is(":visible")) {
			// todo
		}
		
		if(inputObj.val() == "请输入您的车型，如速腾") {
			inputObj.val("");
			inputObj.css("color", "black");
		}
	}
</script>
</head>
<body>
<div class="wrap">
	<div id="topbar"/></div>
	<div id="logobar"><a href="./"><img src="images/logo.gif" alt="车主网" border="0"/></a></div>
	<!--search begin-->
	<div id="search_begin">
		<div id="tab-container" class="tab-container" align="center">
			<ul class="etabs">
				<li class="tab"><a href="#filter-tab">三滤+空调滤</a></li>
				<li class="tab"><a href="#spark-tab">火花塞</a></li>
				<li class="tab"><a href="#engineoil-tab">机油</a></li>
				<li class="tab"><a href="#wipers-tab">雨刷</a></li>
				<li class="tab"><a href="#brakepads-tab">刹车片</a></li>
			</ul>
			<div class="clear"></div>
			<div id="filter-tab" style="display:none;">
				<form action="query_queryFilter.action" method="post" onsubmit="return checkInput();" id="form1">		
				<div class="brand_item">
					<s:set name="filterSupplies" value="#{'2':'原厂号', '1':'博世BOSCH', '4':'马勒MAHLE', '3':'索菲玛SOFIMA', '5':'曼牌MANN', '6':'豹王'}"></s:set>
					<ul>
					<s:if test="filterSupplyItem">
						<s:checkboxlist list="#filterSupplies" name="filterSupplyItem" theme="simple"></s:checkboxlist>
					</s:if>
					<s:else>
						<s:checkboxlist list="#filterSupplies" name="filterSupplyItem" theme="simple" value="{'1', '2', '3', '4', '5'}"></s:checkboxlist>
					</s:else>
					</ul>
					<div class="clear"></div>
				</div>

				<div class="searchbar">
					<s:if test="filterQueryStr != null">
						<input class="input_type" type="text" size="35" name="filterQueryStr" id="filterQueryStr" value="<s:property value='filterQueryStr'/>"/>
					</s:if>
					<s:else>
						<input class="input_suggest" type="text" size="35" name="filterQueryStr" id="filterQueryStr" value="请输入您的车型，如速腾" onclick="clearSuggest();"/>
					</s:else>
					<input class="submit_btn" type="submit" value="">
					<div class="clear"></div>
				</div>
				</form>
			</div>
			<div id="spark-tab" style="display:none;">
				<form action="query_querySpark.action" method="post" onsubmit="return checkInput();" id="form2">		
				<div class="brand_item">
					<s:set name="sparkSupplies" value="#{'1':'博世BOSCH', '7':'NGK', '8':'电装DENSO'}"></s:set>
					<ul>
					<s:if test="sparkSupplyItem">
						<s:checkboxlist list="#sparkSupplies" name="sparkSupplyItem" theme="simple"></s:checkboxlist>
					</s:if>
					<s:else>
						<s:checkboxlist list="#sparkSupplies" name="sparkSupplyItem" theme="simple" value="{'1', '7', '8'}"></s:checkboxlist>
					</s:else>
					</ul>
					<div class="clear"></div>
				</div>

				<div class="searchbar">
					<s:if test="sparkQueryStr != null">
						<input class="input_type" type="text" size="35" name="sparkQueryStr" id="sparkQueryStr" value="<s:property value='sparkQueryStr'/>"/>
					</s:if>
					<s:else>
						<input class="input_suggest" type="text" size="35" name="sparkQueryStr" id="sparkQueryStr" value="请输入您的车型，如速腾" onclick="clearSuggest();"/>
					</s:else>
					<input class="submit_btn" type="submit" value="">
					<div class="clear"></div>
				</div>
				</form>
			</div>
			<div id="engineoil-tab" style="display:none;">
				<div class="brand_item">&nbsp;</div>
				<div class="searchbar">即将上线...</div>
			</div>
			<div id="wipers-tab" style="display:none;">
				<div class="brand_item">&nbsp;</div>
				<div class="searchbar">即将上线...</div>
			</div>
			<div id="brakepads-tab" style="display:none;">
				<div class="brand_item">&nbsp;</div>
				<div class="searchbar">即将上线...</div>
			</div>
		</div>
	</div>
	
	<div align="center">
		<div id="search_suggest">请输入输入具体车型信息便于准确定位，例如 速腾1.6L</div>
	</div>
	
	<s:if test="#request.styles != null">
	<!--search result-->
	<div id="search_result">
		<!--new search_result area--->
		<s:if test='#request.resultType == "filter"'>
			<!-- 三滤结果数据 -->
			<s:iterator value="#request.styles" id="style" status="st">
			<table cellspacing="0" class="bigtable">
				<tr>
					<td class="brand"><img src="<s:property value='brandImg'/>"/></td>
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
				<s:iterator value="#request.orderFilterSupplies" id="sp" status="sts">
				<tr>
					<td style="text-align:left;text-indent:4px;"><img src="<s:property value='#request.filters[styleFullName][name].supplyImg'/>" align="absmiddle" alt="<s:property value='name'/>" border="0"></td>
					<td><s:if test="#request.filters[styleFullName][name].machineOil != null && #request.filters[styleFullName][name].machineOil != ''"><s:property value="#request.filters[styleFullName][name].machineOil"/></s:if><s:else>&nbsp;</s:else></td>
					<td><s:if test="#request.filters[styleFullName][name].air != null && #request.filters[styleFullName][name].air != ''"><s:property value="#request.filters[styleFullName][name].air"/></s:if><s:else>&nbsp;</s:else></td>
					<td><s:if test="#request.filters[styleFullName][name].fuelOil != null && #request.filters[styleFullName][name].fuelOil != ''"><s:property value="#request.filters[styleFullName][name].fuelOil"/></s:if><s:else>&nbsp;</s:else></td>
					<td><s:if test="#request.filters[styleFullName][name].airConditionStd != null && #request.filters[styleFullName][name].airConditionStd != ''"><s:property value="#request.filters[styleFullName][name].airConditionStd"/></s:if><s:else>&nbsp;</s:else></td>
					<td><s:if test="#request.filters[styleFullName][name].airConditionCarbon != null && #request.filters[styleFullName][name].airConditionCarbon != ''"><s:property value="#request.filters[styleFullName][name].airConditionCarbon"/></s:if><s:else>&nbsp;</s:else></td>
					<td class="td_rgt"><a href="sanlv/<s:property value="#request.filters[styleFullName][name].filterId"/>/">详情>></a></td>
				</tr>
				</s:iterator>
			</table>

			<!--hr with many results, if only one item need remove hr-->
			<s:if test="!#st.last">
				<hr/>
			</s:if>
			</s:iterator>
		</s:if>
	
		<s:elseif test='#request.resultType == "spark"'>
			<!-- 火花塞结果数据 -->
			<s:iterator value="#request.styles" id="style" status="st">
			<table cellspacing="0" class="bigtable">
				<tr>
					<td class="brand"><img src="<s:property value='brandImg'/>"/></td>
					<td class="title"><s:property value='styleName'/>&nbsp;<s:property value='styleOutter'/></td>
					<td class="others">发动机型号：<span class="fdj"><s:property value='styleMotor'/></span></td>
				</tr>
			</table>
			<table cellspacing="0" class="detailtb">
				<tr>
					<th width="80px" style="text-align:left;text-indent:8px;">品牌</th>
					<th width="130px">普通火花塞</th>
					<th width="130px">含铂金火花塞</th>
					<th width="130px">含铱金火花塞</th>
					<th width="130px">铂铱合金火花塞</th>
					<th width="60px">&nbsp;</th>
				</tr>
				<s:iterator value="#request.orderSparkSupplies" id="sp" status="sts">
				<tr>
					<td style="text-align:left;text-indent:4px;"><img src="<s:property value='#request.sparks[styleFullName][name].supplyImg'/>" align="absmiddle" alt="<s:property value='name'/>" border="0"></td>
					<td><s:if test="#request.sparks[styleFullName][name].standard != null && #request.sparks[styleFullName][name].standard != ''"><s:property value="#request.sparks[styleFullName][name].standard"/></s:if><s:else>&nbsp;</s:else></td>
					<td><s:if test="#request.sparks[styleFullName][name].platinum != null && #request.sparks[styleFullName][name].platinum != ''"><s:property value="#request.sparks[styleFullName][name].platinum"/></s:if><s:else>&nbsp;</s:else></td>
					<td><s:if test="#request.sparks[styleFullName][name].iridium != null && #request.sparks[styleFullName][name].iridium != ''"><s:property value="#request.sparks[styleFullName][name].iridium"/></s:if><s:else>&nbsp;</s:else></td>
					<td><s:if test="#request.sparks[styleFullName][name].alloy != null && #request.sparks[styleFullName][name].alloy != ''"><s:property value="#request.sparks[styleFullName][name].alloy"/></s:if><s:else>&nbsp;</s:else></td>
					<td class="td_rgt"><a href="spark/<s:property value="#request.sparks[styleFullName][name].sparkId"/>/">详情>></a></td>
				</tr>
				</s:iterator>
			</table>

			<!--hr with many results, if only one item need remove hr-->
			<s:if test="!#st.last">
				<hr/>
			</s:if>
			</s:iterator>
		</s:elseif>
	</div>
	</s:if>
	<div id="footer"/>车主网致力于为车主免费提供<b>汽车三滤 火花塞 雨刷片 刹车片</b>等易损件配件自助查询服务!<br/>Copyrights &copy; 2012 Chezhu5.com 车主网 京ICP备1201703 <a href="sitemap.html">三滤型号大全</a><br/>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F8923cc0b97ccc7b4825046ef57ccdbf3' type='text/javascript'%3E%3C/script%3E"));
</script>
</div>
</div>
</body>
</html>