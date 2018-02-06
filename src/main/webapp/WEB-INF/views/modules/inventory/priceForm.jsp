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
					$('#validateFlag').val('1');
					
					var data = $(form).serialize();
				
					$.ajax({
						url: "${ctx}/inventory/inventoryItem/saveIn",
						type: "post",
						dataType: "json",
						data: data,
						success: function(data){
							if(data.status=="1"){	
								top.$.jBox.close(true);
							}
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
							console.log(XMLHttpRequest.status);
							console.log(XMLHttpRequest.readyState);
							console.log(textStatus);
						}
						
					});
				
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {					
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
			
		});
		
	</script>
</head>
<body>
	<input style="display:none;" id="validateFlag" value="0"/>
	<form:form id="inventoryItemForm" modelAttribute="inventoryItem" action="${ctx}/inventory/inventoryItem/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="inventoryId"/>
		<form:hidden path="goodsType"/>
		
		<sys:message content="${message}"/>		
		<br>		
		<div class="control-group">
			<label class="control-label">单价：</label>
			<div class="controls">
				<form:input path="price" htmlEscape="false" maxlength="9" class="input-large required number"/>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		
		<div style="display:none;">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
		</div> 
	</form:form>
</body>
</html>