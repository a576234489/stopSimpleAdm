<%@ page language="java" contentType="text/html; charset=UTF-8" 
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<link rel="stylesheet"
	href="${ctx }/resources/js/select2/select2.min.css" />
<script type="text/javascript"
	src="${ctx }/resources/js/select2/select2.min.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/select2/zh-CN.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/address/jsAddress.js"></script>
<script type="text/javascript"
	src="${ctx }/resources/js/WdtePicker/WdatePicker.js"></script>
<style>
/* .display {
	display: none
} */
.displays {
	display: block;
	width: 1005px;
	height: 400px;
}
#btn-scroll-up{
	background-color: #F4F5F3;
}
a{font-size:12px;color:blue}
a:hover{color:red}
</style>
<h1>
		<c:if test="${empty truckTypeEntity}">
		消防车分类添加
		</c:if>
		<c:if test="${!empty truckTypeEntity}">
		消防车分类修改
		</c:if>
</h1>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="truckTypeFrom" name="truckTypeFrom" class="form-horizontal"
			role="form" method="post" enctype="multipart/form-data">
			<c:if test="${!empty truckTypeEntity}">
				<input type="hidden" id="pageNum" name="pageNum"
					value="${page.pageNum }">
				<input type="hidden" id="pageSize" name="pageSize"
					value="${page.pageSize }">
				<input type="hidden" id="orderByColumn" name="orderByColumn"
					value="${page.orderByColumn }">
				<input type="hidden" id="orderByType" name="orderByType"
					value="${page.orderByType }">
				<input type="hidden" name="id"  value="${truckTypeEntity.id }">	
				<input type="hidden" name="truckParamEntity.id"  value="${truckTypeEntity.truckParamEntity.id }">	
			</c:if>		
			<c:if test="${grade==1 }">
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">车辆种类</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="name"
								value="${truckTypeEntity.name}" />
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${grade==2 }">
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">车辆类型</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="name"
								value="${truckTypeEntity.name}" />
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${grade==3 }">
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">车辆规格</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="name"
								value="${truckTypeEntity.name}" />
						</div>
					</div>
				</div>
			</c:if> 
			<c:if test="${grade==4 }">
				<input type="hidden" id="isFoue" name="isFour"
						value="1">
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">型号</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="name"
								value="${truckTypeEntity.name}" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">生产厂家</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="truckParamEntity.manufacturer"
								value="${truckTypeEntity.truckParamEntity.manufacturer}" />
						</div>
					</div>
				</div>
				<div class="form-group">
						<label class="control-label col-sm-1 no-padding-right"
							for="accountName">车辆照片</label>
						<div class="col-sm-10">
							<div class="clearfix">
								<input class="form-control" type="file" id = "filename" name="filename" value="${truckTypeEntity.truckParamEntity.modelImg}"/>
							</div>
						</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="accountName">车辆属性</label>
					<div class="col-sm-10 box">
						<div id="editor"></div>
						<textarea name="truckParamEntity.param" id="param" class="displays">${truckTypeEntity.truckParamEntity.param }</textarea>
						<%-- <input type="hidden" name="newImg" id="newImg"
							value="${discover.newImg }" /> --%>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="accountName">保养手册</label>
					<div class="col-sm-10 box">
						<div id="editor"></div>
						<textarea name="truckParamEntity.maintenanceManual" id="maintenanceManual" class="displays">${truckTypeEntity.truckParamEntity.maintenanceManual }</textarea>
						<%-- <input type="hidden" name="newImg" id="newImg"
							value="${discover.newImg }" /> --%>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="accountName">操作说明</label>
					<div class="col-sm-10 box">
						<div id="editor"></div>
						<textarea name="truckParamEntity.operationInstructions" id="operationInstructions" class="displays">${truckTypeEntity.truckParamEntity.operationInstructions }</textarea>
						<%-- <input type="hidden" name="newImg" id="newImg"
							value="${discover.newImg }" /> --%>
					</div>
				</div>
			</c:if> 
		</form>
		<div class="hr hr-dotted"></div>
	</div>
</div>
<div class="center">
	<button id="btnAdd" type="button" onclick="commit()"
		class="btn btn-success btn-sm">
		<i class="fa fa-user-plus"></i>&nbsp; 保存
	</button>
	<button id="btn" type="button"
		onclick="back()"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script>
var editor;
$(function() {
	param = KindEditor.create('textarea[name="truckParamEntity.param"]', {
		resizeType : 0,
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		//allowFileManager : true, //允许对上传图片进行管理
		items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print',
				'template', 'code', 'cut', 'copy', 'paste', 'plainpaste',
				'wordpaste', '|', 'justifyleft', 'justifycenter',
				'justifyright', 'justifyfull', 'insertorderedlist',
				'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', 'clearhtml', 'quickformat', 'selectall',
				'|', 'fullscreen', '/', 'formatblock', 'fontname',
				'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight',
				'removeformat', '|', 'image', 'media', 'insertfile',
				'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
				'anchor', 'link', 'unlink', '|', 'about' ],
		allowFileManager : true,
		uploadJson : 'discover/upload.html',
		filePostName : 'branndUrl',
		//	fileManagerJson : '../resources/js/editor/file_manager_json.jsp',
		afterBlur : function() {
			this.sync();
		}
	});
	maintenanceManual = KindEditor.create('textarea[name="truckParamEntity.maintenanceManual"]', {
		resizeType : 0,
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		//allowFileManager : true, //允许对上传图片进行管理
		items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print',
				'template', 'code', 'cut', 'copy', 'paste', 'plainpaste',
				'wordpaste', '|', 'justifyleft', 'justifycenter',
				'justifyright', 'justifyfull', 'insertorderedlist',
				'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', 'clearhtml', 'quickformat', 'selectall',
				'|', 'fullscreen', '/', 'formatblock', 'fontname',
				'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight',
				'removeformat', '|', 'image', 'media', 'insertfile',
				'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
				'anchor', 'link', 'unlink', '|', 'about' ],
		allowFileManager : true,
		uploadJson : 'discover/upload.html',
		filePostName : 'branndUrl',
		//	fileManagerJson : '../resources/js/editor/file_manager_json.jsp',
		afterBlur : function() {
			this.sync();
		}
	});
	operationInstructions = KindEditor.create('textarea[name="truckParamEntity.operationInstructions"]', {
		resizeType : 0,
		allowPreviewEmoticons : false,
		allowImageUpload : true,
		//allowFileManager : true, //允许对上传图片进行管理
		items : [ 'source', '|', 'undo', 'redo', '|', 'preview', 'print',
				'template', 'code', 'cut', 'copy', 'paste', 'plainpaste',
				'wordpaste', '|', 'justifyleft', 'justifycenter',
				'justifyright', 'justifyfull', 'insertorderedlist',
				'insertunorderedlist', 'indent', 'outdent', 'subscript',
				'superscript', 'clearhtml', 'quickformat', 'selectall',
				'|', 'fullscreen', '/', 'formatblock', 'fontname',
				'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				'italic', 'underline', 'strikethrough', 'lineheight',
				'removeformat', '|', 'image', 'media', 'insertfile',
				'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
				'anchor', 'link', 'unlink', '|', 'about' ],
		allowFileManager : true,
		uploadJson : 'discover/upload.html',
		filePostName : 'branndUrl',
		//	fileManagerJson : '../resources/js/editor/file_manager_json.jsp',
		afterBlur : function() {
			this.sync();
		}
	});
});
var truckTypes = '${truckTypes}';
var truckTs = JSON.parse(truckTypes)
console.log(truckTs);
var truckTd = '${truckTypeEntity.parentId}';

truckType();

function truckType(){
	var options = ""; 
	for (var i = 0; i < truckTs.length; i++) {
		var truckT = truckTs[i];
		if(truckTd != null && truckTd == truckT.id){
			options += '<option value="'+truckT.id+'" selected>'+truckT.name+'</option>';
		}else{
			options += '<option value="'+truckT.id+'">'+truckT.name+'</option>';
		}
	}
	$(".posi").append(options)
}

function commit() {
//	webside.common.commit('truckTypeFrom', '/truckType/edit.html?parentId='+parentId,
	//		'/truckType/listUI.html?parentId='+parentId+"&tag=1");
	$("#truckTypeFrom").ajaxForm({
		type : 'POST',
		url : 'truckType/edit.html?parentId='+parentId,
		dataType : 'json',
		timeout : 10000,//超时时间设置为10秒；
		success : function(data) {
			console.log(data);
			if (data.success) {
				layer.msg(data.message);
				webside.common.loadPage('/truckType/listUI.html?parentId='+parentId+"&tag=1")
			}else{
				layer.msg(data.message);
			}
		},
		error : function(e) {
			layer.msg(e.message);
		}
	}).submit();
}
function back() {
	webside.common.loadPage('/truckType/listUI.html?parentId='+parentId+"&tag=1");
}
</script>