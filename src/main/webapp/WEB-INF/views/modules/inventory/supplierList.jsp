<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>供应商客户管理</title>
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
		<li class="active"><a href="${ctx}/inventory/supplier/">供应商客户列表</a></li>
		<li><a href="${ctx}/inventory/supplier/form">供应商客户添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="supplier" action="${ctx}/inventory/supplier/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>编码：</label>
				<form:input path="supplierCode" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="supplierName" htmlEscape="false" maxlength="50" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="typeString" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('supplier_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编码</th>
				<th>名称</th>
				<th>电话</th>
				<th>地址</th>
				<th>类型</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="supplier">
			<tr>
				<td><a href="${ctx}/inventory/supplier/form?id=${supplier.id}">
					${supplier.supplierCode}
				</a></td>
				<td>
					${supplier.supplierName}
				</td>
				<td>
					${supplier.tel}
				</td>
				<td>
					${supplier.address}
				</td>
				<td>
					${fns:getDictLabel(supplier.type, 'supplier_type', '')}
				</td>
				<td>
    				<a href="${ctx}/inventory/supplier/form?id=${supplier.id}">修改</a>
					<a href="${ctx}/inventory/supplier/delete?id=${supplier.id}" onclick="return confirmx('确认要删除该记录吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>