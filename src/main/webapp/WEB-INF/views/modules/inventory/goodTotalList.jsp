<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>库存管理</title>
	<meta name="decorator" content="default"/>	
	<script type="text/javascript">
		$(document).ready(function() {

		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/good/inList">入库列表</a></li>
		<li><a href="${ctx}/inventory/good/outList">出库列表</a></li>
		<li class="active"><a href="${ctx}/inventory/good/totalList">统计列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="good" action="${ctx}/inventory/good/totalList" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品名称：</label>
				<form:input path="goodsName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>		
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品名称</th>
				<th>剩余库存</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="good">
			<tr>
				<td>
					${good.goodsName}
				</td>
				<td>
					${good.num}							
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>