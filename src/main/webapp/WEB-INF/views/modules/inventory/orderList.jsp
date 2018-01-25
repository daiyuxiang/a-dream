<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
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
		<li class="active"><a href="${ctx}/inventory/order/">订单列表</a></li>
		<li><a href="${ctx}/inventory/order/form">订单添加</a></li>
	</ul>
	
	
	<form:form id="searchForm" modelAttribute="order" action="${ctx}/inventory/order/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品名称：</label>
				<form:input path="goodName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>订单号：</label>
				<form:input path="orderNo" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>交付单位：</label>
				<form:select path="customerId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${customerList}" itemLabel="supplierName" itemValue="id" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<c:if test="${not empty remindOrderNo}">
		<span class="help-inline"><font color="red">订单号${remindOrderNo}的订单即将到货，请处理。</font></span>		
	</c:if>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>订单号</th>
				<th>产品品牌</th>				
				<th>产品名称</th>
				<th>下达单位</th>
				<th>预计到货时间</th>
				<th>运输方式</th>
				<th>数量</th>
				<th>单价</th>
				<th>交付单位</th>	
				<th>交付单位的货期</th>	
				<th>提醒时间</th>	
				<th>订单状态</th>		
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="order">
			<tr>
				<td><a href="${ctx}/inventory/order/form?id=${order.id}">
					${order.orderNo}
				</a></td>
				<td>
					${order.goodBrandName}
				</td>
				<td>
					${order.goodName}
				</td>
				<td>
					${order.supplierName}
				</td>
				<td>
					<fmt:formatDate value="${order.arrivalDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${order.shippingType}
				</td>
				<td>
					${order.num}
				</td>
				<td>
					${order.price}
				</td>
				<td>
					${order.customerName}		
				</td>
				<td>
					<fmt:formatDate value="${order.productDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${order.reminderDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<c:if test="${order.orderStatus == '0'}">
						未处理
					</c:if>
					<c:if test="${order.orderStatus == '1'}">
						处理完成
					</c:if>
				</td>
				<td>
					<c:if test="${order.orderStatus == '0'}">
				    	<a href="${ctx}/inventory/order/updateStatus?id=${order.id}" onclick="return confirmx('确认要处理该订单吗？', this.href)">处理订单</a>
					</c:if>
				    <a href="${ctx}/inventory/order/form?id=${order.id}">修改</a>
					<a href="${ctx}/inventory/order/delete?id=${order.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>