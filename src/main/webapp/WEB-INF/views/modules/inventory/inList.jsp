<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库管理</title>
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
		<li class="active"><a href="${ctx}/inventory/in/">入库列表</a></li>
		<li><a href="${ctx}/inventory/in/form">入库添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="inventory" action="${ctx}/inventory/in/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>入库单：</label>
				<form:input path="inventoryNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>供应商：</label>
				<form:select path="supplierId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${supplierList}" itemLabel="supplierName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>入库日期：</label>		
				<input id="inventoryDateBegin"  name="inventoryDateBegin"  type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
				value="<fmt:formatDate value="${inventory.inventoryDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
				<input id="inventoryDateEnd" name="inventoryDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${shop.inventoryDateEnd}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
			<li><label>总价：</label>
				<form:input path="totalPrice" htmlEscape="false" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>入库单</th>
				<th>入库日期</th>
				<th>总价</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="inventory">
			<tr>
				<td>
					${inventory.inventoryNo}
				</td>
				<td>
					<fmt:formatDate value="${inventory.inventoryDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${inventory.totalPrice}
				</td>
				<td>
    				<a href="${ctx}/inventory/in/form?id=${inventory.id}">修改</a>
					<a href="${ctx}/inventory/in/delete?id=${inventory.id}" onclick="return confirmx('确认要删除该入库单吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>