<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<script>
	var status = '${status}';
</script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/js/editor/themes/default/default.css" />
<script type="text/javascript" charset="UTF-8"
	src="${pageContext.request.contextPath }/resources/js/editor/kindeditor-all.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/js/select2/select2.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/select2/zh-CN.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/address/jsAddress.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/WdtePicker/WdatePicker.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery-2.1.4.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/jquery/jquery.form.min.js"></script>
<!-- 表格 -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.min.css" />
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/dlshouwen.grid.treegrid.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/dlshouwen.grid.v1.2.1/i18n/zh-cn.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/resources/js/customer/equipType/list.js"></script>

<style>
.modal-backdrop {
	z-index: -1024;
}

.modelStyle {
	z-index: 1111;
}
textarea {
width:700px;height:300px;
}

.modal-content {
	margin-top: 10px;
}

.proClass {
	margin-left: 20px !important;
	font-size: 16px;
}

.warningClass {
	margin-left: 20px !important;
	font-size: 16px;
}
</style>
<div class="page-header">

	<button id="btnEdit" type="button"
		onclick="add()"
		class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;添加
	</button>

	<button id="btnEdit" type="button"
		onclick="edit()"
		class="btn btn-success btn-sm">
		<i class="fa fa-pencil-square-o"></i>&nbsp;编辑
	</button>

	<button id="btnDel" type="button"
		onclick="del()"
		class="btn btn-danger btn-sm">
		<i class="fa fa-trash-o"></i>&nbsp;删除
	</button>
</div>

<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<ol class="breadcrumb">	                        	
              <li>
              		<a href="javascript:void(0)" onclick="olClick(0,1,'顶级分类列表')">顶级分类列表</a> 
              </li>
              <li>
             		<a id="navigation2" onclick="" href="javascript:void(0)" ></a>
              </li>
              <li>
              		<a id="navigation3" onclick="" href="javascript:void(0)"></a>
              </li>
               <li>
              		<a id="navigation4" onclick="" href="javascript:void(0)"></a>
              </li>
	        </ol>
			

			<div class="widget-body" style="display: block;">
				<div class="widget-main padding-6 no-padding-left no-padding-right">
					<input id="pageNum" type="hidden" value="${page.pageNum }">
					<input id="pageSize" type="hidden" value="${page.pageSize }">
					<input id="orderByColumn" type="hidden"
						value="${page.orderByColumn }"> <input id="orderByType"
						type="hidden" value="${page.orderByType }">
					<div id="dtGridContainer" class="dlshouwen-grid-container"></div>
					<div id="dtGridToolBarContainer"
						class="dlshouwen-grid-toolbar-container"></div>
				</div>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">

$(function() {
	console.log(parentId);
	var olDates = "${olDate}";
	console.log(olDates);
	if(olDates != null && olDates != ""){
		var temp =[];
		olDates = olDates.split(',olClick');
		var olDate = "";
		for (var i = 0; i < olDates.length; i++) {
			var olDate = olDates[i];
			if(i == 0){
				olDate = olDate.replace("olClick(","").replace(")","").replace("'","").replace("'","");
				temp = olDate.split(",");
				olClickRefresh(temp[0],temp[1],temp[2]);
				if(i == olDates.length-1){
					grade == temp[1];
					parentId = temp[0];
					console.log(temp[0])
				}
			}else{
				olDate = olDate.replace("(","").replace(")","").replace("'","").replace("'","");
				temp = olDate.split(",");
				olClickRefresh(temp[0],temp[1],temp[2]);
				if(i == olDates.length-1){
					grade == temp[1];
					parentId = temp[0];
					console.log(temp[0])
				}
			}
		}
		
	}else{
		grade == 0;
	}
	console.log(grade);
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.parameters = new Object();
    grid.parameters['parentId'] = parentId;
    grid.load(nowPage);
    $("#btnSearch").click(customSearch);
   
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
            customSearch();
        }
    };
    
});
</script>
