<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/admin/manage/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/admin/manage/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/admin/manage/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/admin/manage/icheck/icheck.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/admin/manage/h-ui.admin/skin/default/skin.css"
	id="skin" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/admin/manage/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/admin/css/laydate.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/js/ajaxfileupload.js"></script>

<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/admin/manage/js/jquery.min.js"></script>

<style type="text/css">
.demo1 {
	height: 300px;
}
</style>

<title>查看手机产品</title>

</head>

<%-- <script type="text/javascript" src="${pageContext.request.contextPath}/static/admin/js/jquery-1.9.1.min.js"></script> --%>
<script type="text/javascript">
	$(function() {

		$("#yunapa").change(function() {
			var adPass = $("#yunapa").val();
			$.post("../admin/ajaxpass.html", {
				adPass : adPass
			}, function(data) {
				if (data.is) {

				} else {
					alert(data.msg);
					$("#yunapa").val("");
				}
			}, "json");
		});

	});
</script>

    <style type="text/css">
        .demo1{
            height:300px;
        }
    </style> 

<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 管理员
	<span class="c-gray en">&gt;</span> 修改管理员
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<form method="post" id="dw" enctype="multipart/form-data" class="form form-horizontal">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>原 密 码:</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" id="yunapa">
				
				 <input type="hidden" value="1" name="id">
			</div>
		</div>

		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>新 密 码:</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" id="xingpa" class="input-text" name="password">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span
				class="c-red">*</span>确认密码:</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="password" class="input-text" id="querenpa">
			</div>
		</div>
				<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
		<input type="submit" value="确认修改" id="sub"  class="btn btn-primary radius">
					</div>
		</div>
	</form>
	</div>
	<script type="text/javascript">
		$("#dw").submit(function() {
			var yunapa = $("#yunapa").val();
			var xingpa = $("#xingpa").val();
			var querenpa = $("#querenpa").val();

			if (yunapa == null || yunapa == "") {
				alert("请输入原密码~");
				return false;
			}
			if (xingpa == null || xingpa == "") {
				alert("请输入新密码~");
				return false;
			}
			if (xingpa != querenpa) {
				alert("两次输入的密码不一致~")
				return false;
			}
			$.post("../admin/updadmins.html", $("#dw").serialize(), function() {
				top.location.href = "login.html";
			}, "json");
			return false;
		});
	</script>
</body>
</html>