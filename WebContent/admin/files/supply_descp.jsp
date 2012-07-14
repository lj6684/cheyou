<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<title>描述信息</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<script type="text/javascript" charset="utf-8"
	src="../ueditor/editor_config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="../ueditor/editor_all_min.js"></script>
<link rel="stylesheet" type="text/css"
	href="../ueditor/themes/default/ueditor.css" />
<style type="text/css">
.clear {
	clear: both;
}
</style>
</head>
<body>
	<s:if test="#request.success">
	<script type="text/javascript">
		alert("保存成功");
	</script>
	</s:if>
	<form name="descpForm" action="supply_saveDescp.action" method="post">
		<s:hidden name="id" value="%{id}"></s:hidden>
		<s:hidden name="descp"/>
		<script type="text/plain" id="myeditor" >
			<s:property value="descp" escape="false"/>
		</script>
		<br/>
		<div align="center">
		<input type="button" onclick="save();" value="保存"/>&nbsp;&nbsp;<input type="button" onclick="back();" value="返回"/>
		</div>
	</form>
	<script type="text/javascript">
		var editor_a = new baidu.editor.ui.Editor();
	
        //渲染编辑器
        editor_a.render('myeditor');

        function back() {
			window.location.href = 'supply_view.action?id=<s:property value="id"/>';
        }

        function save() {
            document.descpForm.descp.value = editor_a.getContent();
            //alert(editor_a.getContent());
            document.descpForm.submit();
        }
    </script>

</html>