<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		var tempTr = '<tr style="text-align:center">'+
							'<td><input type="text" name="goodsName" class="input-small"/></td>'+
							'<td><input type="text" name="goodsArea" htmlEscape="false" class="input-small"/></td>'+
							'<td><input type="text" name="factoryNo" htmlEscape="false" class="input-small"/></td>'+
							'<td><input type="text" name="goodsSize" htmlEscape="false" class="input-small"/></td>'+
							'<td><input type="text" name="goodsWeight" htmlEscape="false" class="input-mini"/></td>'+
							'<td><input type="text" name="num" htmlEscape="false" class="input-mini"/></td>'+
							'<td><input type="text" name="price" htmlEscape="false" class="input-mini"/></td>'+
							'<td><input type="text" name="direction" htmlEscape="false" class="input-small"/></td>'+
							'<td><input type="text" name="location" htmlEscape="false" class="input-small"/></td>'+
							'<td>状态</td>'+
							'<td></td>'+
					'</tr>';
	
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
			$('#btnAdd').on('click',function() {
				var temp = tempTr;
				$('#inventoryTable').append(temp)
			});
		});
		
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/out/">出库列表</a></li>
		<li class="active"><a href="${ctx}/inventory/out/form?id=${inventory.id}">出库${not empty inventory.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="inventory" action="${ctx}/inventory/out/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="type" value="1"/>
		<sys:message content="${message}"/>
		<fieldset>
			<table class="table-form">
				<tr>
					<td class="tit">出库单</td>
					<td><form:input path="inventoryNo" htmlEscape="false" maxlength="100" class="input-xlarge "/><span class="help-inline"><font color="red">*</font></span></td>
					<td class="tit">客户</td>
					<td><form:select path="supplierId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${supplierList}" itemLabel="supplierName" itemValue="id" htmlEscape="false"/>
				</form:select></td>
				</tr>
				<tr>
					<td class="tit">出库时间</td>
					<td><input name="inventoryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${inventory.inventoryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></td>
					<td class="tit">总价</td>
					<td><form:input path="totalPrice" htmlEscape="false" class="input-xlarge "/></td>
				</tr>
				<tr>
					<td class="tit">订单号</td>
					<td><form:input path="orderNo" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
					<td class="tit">对方订单号</td>
					<td><form:input path="otherOrderNo" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
				</tr>
				<tr>
					<td class="tit">发票</td>
					<td><form:input path="invoice" htmlEscape="false" maxlength="2" class="input-xlarge "/></td>
					<td class="tit">开票日期</td>
					<td><input name="openDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${inventory.openDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></td>
				</tr>
				<tr>
					<td class="tit" colspan="4" style="text-align:left">出库单明细
						<input id="btnAdd" class="btn btn-primary" type="button" value="新增"/>
					</td>
				</tr>
				
				<tr>
					<table class="table-form" id="inventoryTable">
						<thead>
							<tr>
								<th>产品名称</th>
								<th>产品产地</th>
								<th>出厂编号</th>
								<th>产品尺寸</th>
								<th>产品重量</th>
								<th>数量</th>
								<th>单价</th>
								<th>地点</th>
								<th>方向</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody>
							<tr style="text-align:center;">
								<td><form:input path="totalPrice" htmlEscape="false" class="input-small"/></td>
								<td><form:input path="totalPrice" htmlEscape="false" class="input-small"/></td>
								<td><form:input path="totalPrice" htmlEscape="false" class="input-small"/></td>
								<td><form:input path="totalPrice" htmlEscape="false" class="input-small"/></td>
								<td><form:input path="totalPrice" htmlEscape="false" class="input-mini"/></td>
								<td><form:input path="totalPrice" htmlEscape="false" class="input-mini"/></td>
								<td><form:input path="totalPrice" htmlEscape="false" class="input-mini"/></td>
								<td><form:input path="totalPrice" htmlEscape="false" class="input-small"/></td>
								<td><form:input path="totalPrice" htmlEscape="false" class="input-small"/></td>
								<td>状态</td>
								<td>
									cazuo
								</td>
							</tr>
						</tbody>						
					</table>
				
				</tr>
				
			</table>
		</fieldset>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>