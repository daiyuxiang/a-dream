<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>明细管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

			$("#inventoryItemForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					var data = $(form).serialize();
					
					$.ajax({
						url: "${ctx}/inventory/inventoryItem/save",
						type: "post",
						dataType: "json",
						data: data,
						success: function(data){
							if(data.status=="1"){	
								//top.$.jBox.close(true);
								debugger;
								top.window.location.herf = "${ctx}/inventory/inventory/list?id="+data.data.inventoryId;
							}else{
							
							}
						}
					});
				
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
			
		});
		
		function save(){
			$("#inventoryItemForm").submit();
		}	
		
	</script>
</head>
<body>
	<form:form id="inventoryItemForm" modelAttribute="inventoryItem" action="${ctx}/inventory/inventoryItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="inventoryId"/>
		<form:hidden path="goodsType"/>
		<sys:message content="${message}"/>		
		<br>
		<div class="control-group">
			<label class="control-label">产品名称：</label>
			<div class="controls">
				<form:input path="goodsName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品产地：</label>
			<div class="controls">
				<form:input path="goodsArea" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出厂编号：</label>
			<div class="controls">
				<form:input path="factoryNo" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品尺寸：</label>
			<div class="controls">
				<form:input path="goodsSize" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品重量：</label>
			<div class="controls">
				<form:input path="goodsWeight" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">数量：</label>
			<div class="controls">
				<form:input path="num" htmlEscape="false" maxlength="11" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">方向：</label>
			<div class="controls">
				<form:input path="direction" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地点：</label>
			<div class="controls">
				<form:input path="location" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div style="padding-left:260px;">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="save()"/>
		</div>
	</form:form>
</body>
</html>