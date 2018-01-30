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
		<li class="active"><a href="javascript:void(0);">库存列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="good" action="${ctx}/inventory/good/list" method="post" class="breadcrumb form-search">
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
				value="<fmt:formatDate value="${good.inventoryDateBegin}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				　--　
				<input id="inventoryDateEnd" name="inventoryDateEnd" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate" style="width:163px;"
					value="<fmt:formatDate value="${good.inventoryDateEnd}" pattern="yyyy-MM-dd"/>"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
			</li>
		
		
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th></th>
				<th>入库单</th>
				<th>入库日期</th>
				<th>供应商</th>
				<th>产品名称</th>
				<th>产品产地</th>
				<th>出厂编号</th>
				<th>产品尺寸</th>
				<th>产品重量</th>
				<th>入库数量</th>
				<th>入库单价</th>
				<th>出库数量</th>
				<th>出库单价</th>
				<th>方向</th>
				<th>地点</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="good">
			<tr>
				<td>
					<input type="checkbox" name="inventoryItemId" value="${good.inventoryItemId}"/>
				</td>
				<td>
					${good.inventoryNo}
				</td>
				<td>
					<fmt:formatDate value="${good.inventoryDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${good.supplierName}
				</td>	
				<td>
					${good.goodsName}
				</td>
				<td>
					${good.goodsArea}
				</td>
				<td>
					${good.factoryNo}
				</td>
				<td>
					${good.goodsSize}
				</td>
				<td>
					${good.goodsWeight}
				</td>
				<td>
					${good.num}
				</td>
				<td>
					${good.price}
				</td>
				<td>
					<input type="text" id="num-${good.inventoryItemId}" value="${good.num}"/>
				</td>
				<td>
					<input type="text" id="price-${good.inventoryItemId}" value="${good.price}"/>
				</td>
				<td>
					${good.direction}
				</td>
				<td>
					${good.location}
				</td>
				<td>
					入库									
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>