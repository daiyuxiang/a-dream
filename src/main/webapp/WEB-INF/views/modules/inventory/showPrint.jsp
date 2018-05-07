<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title></title>

<style>
* {
	margin: 0px;
	padding: 0px;
}

body {
	font: 14px "微软雅黑", "Helvetica Neue", Arial, Helvetica, sans-serif;
	color: #3d3d3d;
}

h1, h2, h3, h4, h5, h6 {
	font-weight: normal;
	font-size: 14px;
}

input, .radio {
	vertical-align: middle;
}

img {
	border: none;
	vertical-align: middle;
	margin: 0px;
	padding: 0px;
}

li ul, li ol {
	margin: 0;
}

ul {
	list-style-type: none;
}

ol {
	list-style-type: none;
}

table {
	width: 100%;
	border: 0;
	margin: 0;
	border-collapse: collapse;
	border-spacing: 0;
}

a {
	text-decoration: none;
	color: #333;
	cursor: pointer;
}

.clearfix:after {
	content: ".";
	display: block;
	height: 0;
	clear: both;
	visibility: hidden;
}

.clearfix {
	*zoom: 1;
}

.wrapper {
	margin: 0 auto;
	min-width: 800px;
	text-align: center;
	position: relative;
}

.name {
	font-size: 18px;
	margin-top: 20px;
}

.title {
	font-size: 24px;
	margin-top: 5px;
	margin-bottom: 10px
}

.info1 {
	text-align: left;
	float: left;
}

.number {
	float: right;
	margin-top: 10px
}

.table-box {
	margin-top: 10px;
}

.table-box td {
	border: 1px solid #000;
	padding: 3px 0;
}

.info2 li {
	float: left;
	width: 33.3%;
	text-align: left;
	margin-top: 20px;
}

.info3 {
	text-align: left;
	margin-top: 10px;
}

.info3 span:nth-child(1), .info3 span:nth-child(2), .info3 span:nth-child(3)
	{
	margin-right: 20px;
}

.info3 span:nth-child(4) {
	margin-left: 40px;
}

.info3 span:nth-child(5) {
	float: right;
}
</style>
<meta name="decorator" content="default" />

<link href="${ctxStatic}/jqGrid/4.6/css/ui.jqgrid.css" type="text/css"
	rel="stylesheet" />
<script src="${ctxStatic}/jqGrid/4.7/js/jquery.jqGrid.js"
	type="text/javascript"></script>
<script src="${ctxStatic}/jqGrid/4.7/js/jquery.jqGrid.extend.js"
	type="text/javascript"></script>
<script src="${ctxStatic}/jqprint/jquery.jqprint-0.3.js"
	type="text/javascript"></script>
<link href="${ctxStatic}/common/print.css" type="text/css" rel="stylesheet" media="print"/>


<script type="text/javascript">
	$(document).ready(function() {
		$(window).resize(function() {
			//grid.jqGrid('setGridWidth',$(window).width()-100);
			//grid.jqGrid('setGridHeight',$(window).height()/2-100);
		}).resize();

	});
	
	
	function confirmPrint() {
		//$("#printDiv").jqprint();
		
		$("#printDiv").jqprint({
			     debug: false, //如果是true则可以显示iframe查看效果（iframe默认高和宽都很小，可以再源码中调大），默认是false
			     importCSS: true, //true表示引进原来的页面的css，默认是true。（如果是true，先会找$("link[media=print]")，若没有会去找$("link")中的css文件）
			     printContainer: true, //表示如果原来选择的对象必须被纳入打印（注意：设置为false可能会打破你的CSS规则）。
			     operaSupport: true//表示如果插件也必须支持歌opera浏览器，在这种情况下，它提供了建立一个临时的打印选项卡。默认是true
			});
	}
</script>
</head>
<body>
	<div class="wrapper" id="printDiv">
		<div class="name">${company.name}</div>
		<div class="title">出库单</div>
		<div class="date">出货日期 <fmt:formatDate value="${inventory.inventoryDate}" pattern="yyyy-MM-dd"/></div>
		<div class="clearfix">
			<ul class="info1">
				<li>订单号: ${inventory.orderNo}</li>
				<li>收货人: </li>
			</ul>
			<div class="number">出库单号:${inventory.inventoryNo}</div>
		</div>
		<table class="table-box">
			<tr>
				<td>序号</td>
				<td>货码</td>
				<td>货物名称/型号规格</td>
				<td>数量</td>
				<td>含税单价</td>
				<td>含税总额</td>
				<td>税率</td>
				<td>去税总额</td>
				<td>税额</td>
			</tr>
			
			<c:forEach items="${inventoryItemList}" var="inventoryItem" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td></td>
					<td>${inventoryItem.goodsName}</td>
					<td>${inventoryItem.num}</td>
					<td>${inventoryItem.price}</td>
					<td>${inventoryItem.num*inventoryItem.price}</td>
					<td>16%</td>
					<td>${inventoryItem.num*inventoryItem.price/1.16}</td>
					<td>税额</td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td>合计</td>
				<td colspan="3"></td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
		</table>
		<ul class="info2 clearfix">
			<li>出货人:</li>
			<li>地址:${company.address}</li>
			<li>电话:${company.phone}</li>
		</ul>
		<div class="info3 clearfix">
			<span>第一联:财务</span> <span>第二联:客户</span> <span>第三联:客户</span> <span>收获送货签收人:</span>
			<span>打印日期:${printDate}</span>
		</div>
	</div>
	
	<input class="btn btn-primary" type="button" value="确认打印" onclick="confirmPrint()"/>&nbsp;
	<input class="btn btn-primary" type="button" value="返回" onclick="history.go(-1)"/>&nbsp;
	
</body>
</html>