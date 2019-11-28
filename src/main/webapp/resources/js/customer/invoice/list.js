var dtGridColumns;
if (status == 0) {
	dtGridColumns = [ {
		id : 'id',
		title : '发票编号',
		type : 'number',
		columnClass : 'text-center',
		hide : true,
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'orderIds',
		title : '订单编号',
		type : 'number',
		columnClass : 'text-center',
		hideType : 'xs',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'amount',
		title : '金额',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'title',
		title : '发票抬头',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'issue',
		title : '开具类型',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'type',
		title : '发票类型',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'recipientName',
		title : '收件人名称',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'receviceAddress',
		title : '收件地址',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'recevicePhone',
		title : '收件人电话',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'taxCode',
		title : '税务号',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'bankName',
		title : '开户银行',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'bankAccount',
		title : '银行账户',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg',
	}, {
		id : 'registerAddress',
		title : '公司注册地址',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg',
	}, {
		id : 'fixedPhone',
		title : '注册固定电话',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'emails',
		title : '电子邮箱',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg'
	}, {
		id : 'createTime',
		title : '创建时间',
		type : 'date',
		format : 'yyyy-MM-dd hh:mm:ss',
		otype : 'string',
		oformat : 'yyyy-MM-dd hh:mm:ss',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg'
	} ];
} else if (status == 1) {
	dtGridColumns = [ {
		id : 'id',
		title : '发票编号',
		type : 'number',
		columnClass : 'text-center',
		hide : true,
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'orderIds',
		title : '订单编号',
		type : 'number',
		columnClass : 'text-center',
		hideType : 'xs',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'amount',
		title : '金额',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'title',
		title : '发票抬头',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'issue',
		title : '开具类型',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'type',
		title : '发票类型',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'recipientName',
		title : '收件人名称',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'receviceAddress',
		title : '收件地址',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'recevicePhone',
		title : '收件人电话',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'expressNumber',
		title : '快递单号',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'taxCode',
		title : '税务号',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'bankName',
		title : '开户银行',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'bankAccount',
		title : '银行账户',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg',
	}, {
		id : 'registerAddress',
		title : '公司注册地址',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg',
	}, {
		id : 'fixedPhone',
		title : '注册固定电话',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'emails',
		title : '电子邮箱',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'createTime',
		title : '创建时间',
		type : 'date',
		format : 'yyyy-MM-dd hh:mm:ss',
		otype : 'string',
		oformat : 'yyyy-MM-dd hh:mm:ss',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg'
	} ];
} else if (status == 2) {
	dtGridColumns = [ {
		id : 'id',
		title : '发票编号',
		type : 'number',
		columnClass : 'text-center',
		hide : true,
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'orderIds',
		title : '订单编号',
		type : 'number',
		columnClass : 'text-center',
		hideType : 'xs',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'amount',
		title : '金额',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'title',
		title : '发票抬头',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'issue',
		title : '开具类型',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'type',
		title : '发票类型',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'recipientName',
		title : '收件人名称',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'receviceAddress',
		title : '收件地址',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'recevicePhone',
		title : '收件人电话',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'expressNumber',
		title : '快递单号',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'taxCode',
		title : '税务号',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'bankName',
		title : '开户银行',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'bankAccount',
		title : '银行账户',
		type : 'number',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg',
	}, {
		id : 'registerAddress',
		title : '公司注册地址',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg',
	}, {
		id : 'fixedPhone',
		title : '注册固定电话',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'emails',
		title : '电子邮箱',
		type : 'string',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header'
	}, {
		id : 'createTime',
		title : '创建时间',
		type : 'date',
		format : 'yyyy-MM-dd hh:mm:ss',
		otype : 'string',
		oformat : 'yyyy-MM-dd hh:mm:ss',
		columnClass : 'text-center',
		headerClass : 'dlshouwen-grid-header',
		hideType : 'sm|xs|md|lg'
	} ];
}

// 动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
	lang : 'zh-cn',
	ajaxLoad : true,
	check : true,
	checkWidth : '37px',
	extraWidth : '37px',
	loadURL : sys.rootPath + '/invoice/list.html',
	columns : dtGridColumns,
	gridContainer : 'dtGridContainer',
	toolbarContainer : 'dtGridToolBarContainer',
	tools : 'refresh|print|export[excel,csv,pdf,txt]',
	exportFileName : '购物车',
	pageSize : pageSize,
	pageSizeLimit : [ 10, 20, 30 ]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {

	grid.parameters = new Object();
	grid.parameters['status'] = status;
	if (status == 15) {
		grid.parameters['workerId'] = id;
	}
	if (null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
		grid.sortParameter.columnId = $("#orderByColumn").val();
		grid.sortParameter.sortType = $("#orderByType").val();
	}
	grid.load();
	$("#btnSearch").click(customSearch);

	// 注册回车键事件
	document.onkeypress = function(e) {
		var ev = document.all ? window.event : e;
		if (ev.keyCode == 13) {
			customSearch();
		}
	};

});

/**
 * 自定义查询 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
	grid.parameters = new Object();
	grid.parameters['status'] = status;
	grid.parameters['recevicePhone'] = $("#searchKey").val();
	grid.refresh(true);
}

/**
 * 对账确认
 */
function confirmSure() {
	var rows = grid.getCheckedRecords();
	if (rows.length == 1) {
		var index;
		$.ajax({
			type : "POST",
			url : sys.rootPath + '/order/confirmSure.html',
			data : {
				"id" : rows[0].id
			},
			dataType : "json",
			beforeSend : function() {
				index = layer.load();
			},
			success : function(resultdata) {
				layer.close(index);
				if (resultdata.success) {
					layer.msg(resultdata.message, {
						icon : 1
					});
					webside.common.loadPage("/order/listConfirm.html");
				} else {
					layer.msg(resultdata.message, {
						icon : 5
					});
				}
			},
			error : function(data, errorMsg) {
				layer.close(index);
				layer.msg(data.responseText, {
					icon : 2
				});
			}
		});
	} else {
		layer.msg("你没有选择行或选择了多行数据", {
			icon : 0
		});
	}
}

/**
 * 待处理业务编辑
 */
function editItem() {
	// 当前页码
	var nowPage = grid.pager.nowPage;
	// 获取每页显示的记录数(即: select框中的10,20,30)
	var pageSize = grid.pager.pageSize;
	// 获取排序字段
	var columnId = grid.sortParameter.columnId;
	// 获取排序方式 [0-不排序，1-正序，2-倒序]
	var sortType = grid.sortParameter.sortType;
	// 获取选择的行
	var rows = grid.getCheckedRecords();
	if (rows.length == 1) {
		webside.common.loadPage("/order/editItemUI.html" + '?id=' + rows[0].id
				+ '&number=' + rows[0].number + "&status=" + status + "&page="
				+ nowPage + "&rows=" + pageSize + "&sidx=" + columnId
				+ "&sord=" + sortType);
	} else {
		layer.msg("你没有选择行或选择了多行数据", {
			icon : 0
		});
	}
}

/**
 * 待处理业务完成确认
 */
function completeSure() {
	var rows = grid.getCheckedRecords();
	if (rows.length == 1) {
		var index;
		var url;
		if (status == 0) {
			url = "/invoice/listUndel.html";
		} else if (status == 1) {
			url = "/invoice/listOpened.html";
		}
		$.ajax({
			type : "POST",
			url : sys.rootPath + '/invoice/completeSure.html',
			data : {
				"id" : rows[0].id,
				status : status
			},
			dataType : "json",
			beforeSend : function() {
				index = layer.load();
			},
			success : function(resultdata) {
				layer.close(index);
				if (resultdata.success) {
					layer.msg(resultdata.message, {
						icon : 1
					});
					webside.common.loadPage(url);
				} else {
					layer.msg(resultdata.message, {
						icon : 5
					});
				}
			},
			error : function(data, errorMsg) {
				layer.close(index);
				layer.msg(data.responseText, {
					icon : 2
				});
			}
		});
	} else {
		layer.msg("你没有选择行或选择了多行数据", {
			icon : 0
		});
	}
}
