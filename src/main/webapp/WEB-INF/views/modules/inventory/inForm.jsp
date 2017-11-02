<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入库管理</title>
	<meta name="decorator" content="default"/>
	
	<link href="${ctxStatic}/jqGrid/4.6/css/ui.jqgrid.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/jqGrid/4.7/js/jquery.jqGrid.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jqGrid/4.7/js/jquery.jqGrid.extend.js" type="text/javascript"></script>

	<script type="text/javascript">
		$(document).ready(function() {
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
			
			$('#dataGrid').dataGrid({
				url:'${ctx}/inventory/inventoryItem/list?inventoryId=${inventory.id}',
				// 设置数据表格列
				columnModel: [
					{header:'产品名称', name:'goodsName', index:'goodsName', width:100, frozen:true , formatter: function(val, obj, row, act){
						return '<a href="${ctx}/sys/user/form?id='+row.id+'" class="btnList" data-title="编辑用户">'+val+'</a>';
					}},
					{header:'产品产地', name:'goodsArea', index:'goodsArea', width:100},
					{header:'出厂编号', name:'factoryNo', index:'factoryNo', width:100},
					{header:'产品尺寸', name:'goodsSize', index:'goodsSize', width:100, sortable:false},
					{header:'产品重量', name:'goodsWeight', index:'goodsWeight', width:50, sortable:false},
					{header:'数量', name:'num', index:'num', width:50, sortable:false},
					{header:'单价', name:'price', index:'price', width:50, sortable:false},
					{header:'方向', name:'direction', index:'direction', width:100, sortable:false},
					{header:'地点', name:'location', index:'location', width:100, sortable:false},
					{header:'状态', name:'goodsType', index:'goodsType', width:50, fixed:true, align:"center", formatter: function(val, obj, row, act){
						return getDictLabel(${fns:getDictListJson('del_flag')}, val, '未知', true);
					}},
					{header:'操作', name:'actions', width:50, fixed:true, sortable:false, fixed:true, formatter: function(val, obj, row, act){
						var actions = [];
						actions.push('<a href="javascript:void(0);" class="btnList" title="编辑入库明细" onclick="openItem('+row.id+')">编辑</a>&nbsp;');
						actions.push('<a href="javascript:void(0);" class="btnList" title="删除入库明细" onclick="deleteItem('+row.id+')">删除</a>&nbsp;');
						return actions.join('');
					}}
				],
				ajaxSuccess: function(data){ // 加载成功后执行方法
					
				}
			});
			
			$(window).resize(function(){
				//grid.jqGrid('setGridWidth',$(window).width()-100);
				//grid.jqGrid('setGridHeight',$(window).height()/2-100);
			}).resize();
		});
		
		function openItem(id) {		
			var title = "";
			var url = "";
			if(id!=null) {
				title = "修改明细";
				url = "iframe:${ctx}/inventory/inventoryItem/form?id="+id+"&inventoryId=${inventory.id}";
			} else {
				title = "新增明细";
				url  = "iframe:${ctx}/inventory/inventoryItem/form?&inventoryId=${inventory.id}";
			}
			
			top.$.jBox(url, {
			    title: title,
			    width: 600,
			    height: 600,
			    buttons: { '取消': true }
			});
		}
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/in/">入库列表</a></li>
		<li class="active"><a href="${ctx}/inventory/in/form?id=${inventory.id}">入库${not empty inventory.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="inventory" action="${ctx}/inventory/in/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<form:hidden path="type" value="1"/>
		<sys:message content="${message}"/>
		<fieldset>
			<table class="table-form">
				<tr>
					<td class="tit">入库单</td>
					<td><form:input path="inventoryNo" htmlEscape="false" maxlength="100" class="input-xlarge "/><span class="help-inline"><font color="red">*</font></span></td>
					<td class="tit">供应商</td>
					<td><form:select path="supplierId" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${supplierList}" itemLabel="supplierName" itemValue="id" htmlEscape="false"/>
				</form:select></td>
				</tr>
				<tr>
					<td class="tit">入库时间</td>
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
					<td class="tit">到票日期</td>
					<td><input name="arrivalDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${inventory.arrivalDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></td>
				</tr>
			</table>
			
					
		</fieldset>
		
		<div style="padding:10px 10px">
			<input id="btnAddItem" class="btn btn-primary" type="button" value="新增" onclick="openItem()"/>&nbsp;
		</div>
		
		<table id="dataGrid"></table>
		<div class="pagination" id="dataGridPage"></div>	
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="button" value="保 存" onclick="save()"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>