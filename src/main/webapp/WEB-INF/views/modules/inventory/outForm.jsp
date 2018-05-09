<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>出库管理</title>
	<meta name="decorator" content="default"/>
	
	<link href="${ctxStatic}/jqGrid/4.6/css/ui.jqgrid.css" type="text/css" rel="stylesheet" />
	<script src="${ctxStatic}/jqGrid/4.7/js/jquery.jqGrid.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jqGrid/4.7/js/jquery.jqGrid.extend.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jqprint/jquery.jqprint-0.3.js" type="text/javascript"></script>
	
	<script type="text/javascript">
		$(document).ready(function() {
			if('${inventory.totalPrice}'=='') {
				$('#totalPrice').val('0');
			}
			$('#inventoryNo').attr('disabled','disabled');
			$('#totalPrice').attr('disabled','disabled');	
			
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
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
						
			$('#dataGrid').dataGrid({
				url:'${ctx}/inventory/inventoryItem/list?inventoryId=${inventory.id}',
				// 设置数据表格列
				columnModel: [
					{header:'产品名称', name:'goodsName', index:'goodsName', width:200},
					{header:'产品品牌', name:'brandName', index:'brandName', width:100},
					{header:'数量', name:'num', index:'num', width:50, sortable:false},
					{header:'单价', name:'price', index:'price', width:50, sortable:false},
					{header:'合计', name:'', index:'', width:50, sortable:false,formatter: function(val, obj, row, act){
						return row.price * row.num;
					}},
					{header:'状态', name:'goodsType', index:'goodsType', width:50, fixed:true, align:"center", formatter: function(val, obj, row, act){
						return getDictLabel(${fns:getDictListJson('goods_type')}, val, '未知', true);
					}},
					{header:'操作', name:'actions', width:200, fixed:true, sortable:false, fixed:true, formatter: function(val, obj, row, act){
						var actions = [];
						
						if(row.goodsType=='2') {
							actions.push('<a href="javascript:void(0);" class="btnList" title="删除出库明细" onclick="deleteItem(\''+row.id+'\')">删除</a>&nbsp;');
						}
						
						actions.push('<a href="javascript:void(0);" class="btnList" title="详细" onclick="itemInfo(\''+row.id+'\')">详细</a>&nbsp;');

						return actions.join('');
					}}
				],
				ajaxSuccess: function(data){ // 加载成功后执行方法
					
				}
				
			});
			
			
		});
		
		function selectItem(id) {			
			if('${inventory.id}'=='') {
				alertx("不能新增明细");
				return;
			}
			
			var title = "选择明细";
			var url = "iframe:${ctx}/inventory/good/selectIn";
			
			top.$.jBox.open(url,title,$(top.document).width()-260,$(top.document).height()-260,{
				buttons:{"选择":"ok", "取消":true}, bottomText:"",submit:function(v, h, f){
					if (v=="ok"){
						var $iframe = $(h.find("iframe")[0]);						
						var outItemList = [];
						var inventoryId = "${inventory.id}";
						
						$iframe.contents().find("input[name='inventoryItemId']:checked").each(function() {   
							var id = $(this).val();
							var num  = $iframe.contents().find('#num-'+id).val();
							var price  = $iframe.contents().find('#price-'+id).val();

							var outItem = new Object(); 
							outItem.oldId = id;
							outItem.num = num;
							outItem.price = price;
							outItem.inventoryId = inventoryId;
							outItemList.push(outItem);
						});   
						
						if(outItemList.length == 0){
							alertx("请选择要出库的明细");
							return false;
						}				
						
						$.ajax({
							url: "${ctx}/inventory/inventoryItem/saveOut",
							type: "post",
					        contentType: "application/json",  
							dataType: "json",
							data: JSON.stringify(outItemList),
							success: function(data){
								if(data.status=="1"){	
									location.reload();
									//$('#dataGrid').trigger("reloadGrid");	
									return true;
								}
							},
							error: function(XMLHttpRequest, textStatus, errorThrown) {
								console.log(XMLHttpRequest.status);
								console.log(XMLHttpRequest.readyState);
								console.log(textStatus);
							}
							
						});
						
					} 
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function itemInfo(id) {	
			var title = "明细详情";
			var url = "iframe:${ctx}/inventory/inventoryItem/form?id="+id+"&inventoryId=${inventory.id}";
			
			top.$.jBox.open(url,title,600,600,{
				buttons:{"取消":true}, bottomText:"",submit:function(v, h, f){
					
				}, loaded:function(h){
					$(".jbox-content", top.document).css("overflow-y","hidden");
				}
			});
		}
		
		function deleteItem(id) {
			confirmx('确认要删除该出库明细吗？',function() {

				$.ajax({		 
					url: "${ctx}/inventory/inventoryItem/deleteOut",
					type: "post",
					dataType: "json",
					data: {"id":id},
					success: function(data){
						if(data.status=="1"){	
							location.reload();
							//$('#dataGrid').trigger("reloadGrid");	
						}
					}
				});
				
			});
		} 
		
		function showPrint() {
			window.location.href = "${ctx}/inventory/out/showPrint?id=${inventory.id}";
		}
		
		function showPrint2() {
			window.location.href = "${ctx}/inventory/out/showPrint2?id=${inventory.id}";
		}
		
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/inventory/out/">出库列表</a></li>
		<li class="active"><a href="${ctx}/inventory/out/form?id=${inventory.id}">出库${not empty inventory.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="inventory" action="${ctx}/inventory/out/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<fieldset>
			<table class="table-form">
				<tr>
					<td class="tit">出库单</td>
					<td><form:input path="inventoryNo" htmlEscape="false" maxlength="100" class="input-xlarge"/></td>
					<td class="tit">客户</td>
					<td><form:select path="supplierId" class="input-large required">
					<form:option value="" label=""/>
					<form:options items="${supplierList}" itemLabel="supplierName" itemValue="id" htmlEscape="false"/>
				</form:select><span class="help-inline"><font color="red">*</font></span></td>
				</tr>
				<tr>
					<td class="tit">出库时间</td>
					<td><input name="inventoryDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate required"
					value="<fmt:formatDate value="${inventory.inventoryDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
					<span class="help-inline"><font color="red">*</font></span>
					</td>
					<td class="tit">总价</td>
					<td><form:input path="totalPrice" htmlEscape="false" class="input-xlarge"/></td>
				</tr>
				<tr>
					<td class="tit">订单号</td>
					<td><form:input path="orderNo" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
					<td class="tit">对方订单号</td>
					<td><form:input path="otherOrderNo" htmlEscape="false" maxlength="100" class="input-xlarge "/></td>
				</tr>
				<tr>
					<td class="tit">发票</td>
					<td><form:input path="invoice" htmlEscape="false" maxlength="50" class="input-xlarge "/></td>
					<td class="tit">开票日期</td>
					<td><input name="openDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${inventory.openDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/></td>
				</tr>
			</table>
			
		</fieldset>
		
		<div style="padding:10px 10px">
			<input class="btn btn-primary" type="button" value="新增" onclick="selectItem()"/>&nbsp;
			<input class="btn btn-primary" type="button" value="打印预览(有价格)" onclick="showPrint()"/>&nbsp;
			<input class="btn btn-primary" type="button" value="打印预览(无价格)" onclick="showPrint2()"/>&nbsp;
			
		</div>
		
		<table id="dataGrid"></table>
		<div class="pagination" id="dataGridPage"></div>			
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>

</body>
</html>