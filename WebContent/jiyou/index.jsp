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
<link href="../css/style.css" rel="stylesheet" type="text/css" />
<!-- jQuery -->
<link href="../css/ui-lightness/jquery-ui-autocomplete.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui-autocomplete.js"></script>
<script type="text/javascript">
	// Ajax请求AutoComplet数据
	$(function() {
		$.getJSON("query_init.action",
			null,
			function(data) {	
				$("#engineOilQueryStr").autocomplete({
					source: data
				});	
			});
	});

	// 检查表单输入，不允许查询条件为空
	function checkInput () {		
		var inputObj = $("#engineOilQueryStr");;
		var checkObj = $(":checkbox[name='engineOilSupplyItem']");;
		
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
		var inputObj = $("#engineOilQueryStr");
		
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
	<div id="logobar"><a href="../"><img src="../images/logo.gif" alt="车主网" border="0"/></a></div>
	<!--search begin-->
	<div id="search_begin">
		<div class="accessory_item">
			<ul>
				<li class="nav"><a href="../">三滤+空调滤</a></li>
				<li class="nav"><a href="../huohuasai/">火花塞</a></li>
				<li class="nav"><a href="#" class="current">机油</a></li>
				<li class="nav"><a href="../yushua/">雨刷</a></li>
				<li class="nav"><a href="../shachepian/">刹车片</a></li>
			</ul>
			<div class="clear"></div>
		</div>
		<form action="query_engineOil.action" method="post" onsubmit="return checkInput();" id="form">
		<div id="spark-tab">
			<div class="brand_item" align="center">
				&nbsp;
				<div class="clear"></div>
			</div>

			<div class="searchbar" align="center">
				即将上线...
				<div class="clear"></div>
			</div>
		</div>
		</form>
	</div>
	
	<div align="center">
		<div id="search_suggest">请输入输入具体车型信息便于准确定位，例如 速腾1.6L</div>
	</div>
	
	<s:if test="#request.styles != null">
	<!--search result-->
	<div id="search_result">
		<!--new search_result area--->
	</div>
	</s:if>
	<div id="footer">车主网致力于为车主免费提供<b>汽车三滤 火花塞 雨刷片 刹车片</b>等易损件配件自助查询服务!<br/>Copyrights &copy; 2012 Chezhu5.com 车主网 京ICP备1201703 <a href="sitemap.html">三滤型号大全</a><br/>
<script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F8923cc0b97ccc7b4825046ef57ccdbf3' type='text/javascript'%3E%3C/script%3E"));
</script>
	</div>
</div>
</body>
</html>