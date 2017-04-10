<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
   String base = pageContext.getServletContext().getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
	window.UEDITOR_SERVER_URL = '<%=base%>';
</script>	
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/css/laydate.css"/>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/admin/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>

    <style type="text/css">
        .demo1{
            height:300px;
        }
    </style> 
    
   
<title>添加</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 文章管理
	<span class="c-gray en">&gt;</span> 修改文章
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<form action="updarticles.html" method="post" enctype="multipart/form-data" class="form form-horizontal" id="banneradd">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>标题：</label>
			<div class="formControls col-xs-8 col-sm-9">
			<input type="hidden" value="${findid.id }" name="id"/>
				<input type="text" class="input-text"  name="title" value="${findid.title }"/>
			</div>
		</div>

		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>详情：</label>
			<div class="formControls col-xs-8 col-sm-9">
			<script id="introduction" name="contents" style="width:100%;height:500px;" type="text/plain">${findid.contents}</script>
			</div>
		</div>
		

		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="$('#banneradd').submit()" class="btn btn-primary radius" type="button"><i class="Hui-iconfont">&#xe632;</i> 保存</button>
			</div>
		</div>
	</form>
</div>


<!-- 百度文本编辑器   引用文件 -->
    <link href="<%=base%>/static/ue/themes/default/css/ueditor.css" type="text/css" rel="stylesheet">
	<script src="<%=base%>/static/ue/ueditor.config.js" type="text/javascript"></script>
	<script src="<%=base%>/static/ue/ueditor.all.js" type="text/javascript"></script>
	<script type="text/javascript" src="<%=base%>/static/ue/lang/zh-cn/zh-cn.js"></script>
 
  <!-- 百度文本编辑器   js -->
   <script type="text/javascript">
	   $(function(){
			var ue = UE.getEditor('introduction',{
				//关闭字数统计  
		        wordCount:false,  
		        toolbars:[['fullscreen', 'source', '|', 'undo', 'redo', '|',
		                   'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
		                   'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
		                   'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
		                   'directionalityltr', 'directionalityrtl', 'indent', '|',
		                   'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
		                   'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
		                   'insertimage', 'emotion', 'insertvideo',  'attachment', 'map',    'template', 'background', '|',
		                   'horizontal', 'date', 'time', 'spechars',  'wordimage', '|',
		                   'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
		                   'print', 'preview', 'searchreplace', 'help'
						]], 
		        //关闭elementPath  
		        elementPathEnabled:false,
			});
		});
		
		var currentBrowserId;
		function browserImage(targetId){
			currentBrowserId = targetId;
			var weboxTemp = $.webox({
				height:600,
				width:1024,
				bgvisibel:true,
				title:'图片管理',
				iframe:'<%=base%>/admin/image/imgbox.html?'+Math.random()
			});
		}
		
		function setImagepath(imgPath){
			$('#'+currentBrowserId).val(imgPath);
		}
		
		function closeFrame(){
			$('#locked .span').click();
		}
   </script>
   
   <script type="text/javascript" src="${pageContext.request.contextPath}/static/admin/js/laydate.dev.js"></script>
    <script type="text/javascript">
        laydate({
            elem: '#J-xl'
        });

        document.getElementById('J-xl-2-btn').onclick = function(){
            laydate({
                elem: '#J-xl-2'
            });
        }

        laydate({
            elem: '#J-xl-3'
        });

        laydate({
            elem: document.getElementById('J-xl-4')
        });
    </script>
</body>
</html>