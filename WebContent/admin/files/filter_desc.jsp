<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" charset="utf-8" src="../ueditor/editor_config.js"></script>

<!--使用版-->
<!--<script type="text/javascript" charset="utf-8" src="../editor_all.js"></script>-->

<!--开发版-->
<script type="text/javascript" charset="utf-8" src="../ueditor/editor_all.js"></script>
<link rel="stylesheet" type="text/css" href="../ueditor/themes/default/ueditor.css"/>
</head>
<body>
	<script type="text/plain" id="myEditor" style="width:1000px">
        <p>这里我可以写一些输入提示</p>
    </script>
    <script type="text/javascript">
        // 自定义的编辑器配置项,此处定义的配置项将覆盖editor_config.js中的同名配置
        var editorOption = {
            //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
            toolbars:[['FullScreen', 'Source', 'Undo', 'Redo','Bold', 'insertunorderedlist', 'insertorderedlist'], 
                      ['link', 'unlink', 'insertimage'],
                      ['preview', 'help']],
            //focus时自动清空初始化时的内容
            autoClearinitialContent:true,
            //关闭字数统计
            wordCount:false,
            //关闭elementPath
            elementPathEnabled:false
            //更多其他参数，请参考editor_config.js中的配置项
        };
        var editor_a = new baidu.editor.ui.Editor(editorOption);
        editor_a.render( 'myEditor' );
    </script>
</body>
</html>