<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/Hui-iconfont/1.0.7/iconfont.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/icheck/icheck.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/admin/manage/h-ui.admin/css/style.css" />

<link href="${pageContext.request.contextPath}/static/admin/css/page.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="${pageContext.request.contextPath}/static/admin/manage/js/jquery.min.js"></script> 
<script type="text/javascript" src="${pageContext.request.contextPath}/static/admin/manage/js/layer.js"></script> 

<title>call</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">&#xe67f;</i> 首页 
	<span class="c-gray en">&gt;</span> 文章管理
	<span class="c-gray en">&gt;</span> 查看文章
	<a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a>
</nav>
<div class="page-container">
	<div class="cl pd-5 bg-1 bk-gray mt-20">
		<span class="l">
			<a class="btn btn-primary radius" href="addarticle.html"><i class="Hui-iconfont">&#xe600;</i> 新增文章</a>
		</span> 
		<span class="r">共有数据：<strong>${pageInfo.size }</strong> 条</span> 
	</div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="80">编号</th>
					<th>标题</th>
					<th>添加时间</th>
					<th>点击次数</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pageInfo.list }" var="item">
					<tr class="text-c">
						<td>${item.id }</td>
						<td>${item.title }</td>
						<td><fmt:formatDate value="${item.adddates }" pattern="yyyy-MM-dd HH:dd:ss" /></td>
						<td>${item.clicknum }次</td>
						<td class="td-manage">
							<a style="text-decoration:none" class="ml-5" href="updarticle.html?id=${item.id }" title="编辑"><i class="Hui-iconfont">&#xe6df;</i></a>
							<a href="javascript:del(${item.id })"><i class="Hui-iconfont">&#xe6e2;</i></a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
		
 	<div id="PageNum">
		<section>
			<div class="bd points-goods-list">
				<ul class="pages">
					<li><a href="adminarticle.html?page=1&rows=${pageInfo.pageSize}">首页</a></li>
					<li class="prev"><a href="adminarticle.html?page=${pageInfo.prePage}&rows=${pageInfo.pageSize}">上一页</a></li>
					<c:forEach items="${pageInfo.navigatepageNums}" var="nav">
						<c:if test="${nav == pageInfo.pageNum}">
							<li><a href="adminarticle.html?page=${nav}&rows=${pageInfo.pageSize}" class="active">${nav}</a></li>
						</c:if>
						<c:if test="${nav != pageInfo.pageNum}">
							<li><a href="adminarticle.html?page=${nav}&rows=${pageInfo.pageSize}">${nav}</a></li>
						</c:if>
					</c:forEach>
					<li class="next"><a href="adminarticle.html?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}">下一页</a></li>
					<li><a href="adminarticle.html?page=${pageInfo.pages }&rows=${pageInfo.pageSize}">末页</a></li>
				</ul>
			</div>
		</section>
	</div> 
</div>
<script type="text/javascript">
	function del(id) {
		if (confirm("确定要删除吗？")) {
			$.post("delarticle.html", {
				id : id
			}, function() {
				window.location = window.location;
			}, "json");
		}
	}
</script>
</body>
</html>