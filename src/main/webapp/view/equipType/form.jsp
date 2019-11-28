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
		<c:if test="${empty equipTypeEntity}">
		装备分类添加
		</c:if>
		<c:if test="${!empty equipTypeEntity}">
		装备分类修改
		</c:if>
</h1>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="equipTypeFrom" name="equipTypeFrom" class="form-horizontal"
			role="form" method="post" enctype="multipart/form-data">
			<c:if test="${!empty equipTypeEntity}">
				<input type="hidden" id="pageNum" name="pageNum"
					value="${page.pageNum }">
				<input type="hidden" id="pageSize" name="pageSize"
					value="${page.pageSize }">
				<input type="hidden" id="orderByColumn" name="orderByColumn"
					value="${page.orderByColumn }">
				<input type="hidden" id="orderByType" name="orderByType"
					value="${page.orderByType }">
				<input type="hidden" name="id"  value="${equipTypeEntity.id }">	
				<input type="hidden" name="equipParamEntity.id"  value="${equipTypeEntity.equipParamEntity.id }">	
			</c:if>		
			<c:if test="${grade==1 }">
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">装备种类</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="name"
								value="${equipTypeEntity.name}" />
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${grade==2 }">
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">装备类型</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="name"
								value="${equipTypeEntity.name}" />
						</div>
					</div>
				</div>
			</c:if>
			<c:if test="${grade==3 }">
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">装备规格</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="name"
								value="${equipTypeEntity.name}" />
						</div>
					</div>
				</div>
			</c:if> 
			<c:if test="${grade==4 }">
				<input type="hidden" id="isFoue" name="isFour"
						value="1">
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">装备型号</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="name"
								value="${equipTypeEntity.name}" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">保养周期</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="equipParamEntity.maintenanceCycle"
								value="${equipTypeEntity.equipParamEntity.maintenanceCycle}" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="name">生产厂家</label>
					<div class="col-sm-10">
						<div class="clearfix">
							<input  class="form-control" type="text" name="equipParamEntity.manufacturer"
								value="${equipTypeEntity.equipParamEntity.manufacturer}" />
						</div>
					</div>
				</div>
				<div class="form-group">
						<label class="control-label col-sm-1 no-padding-right"
							for="accountName">装备照片</label>
						<div class="col-sm-10">
							<div class="clearfix">
								<input class="form-control" type="file" id = "filename" name="filename" value="${equipTypeEntity.equipParamEntity.modelImg}"/>
							</div>
						</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="accountName">装备属性</label>
					<div class="col-sm-10 box">
						<div id="editor"></div>
						<textarea name="equipParamEntity.param" id="param" class="displays">${equipTypeEntity.equipParamEntity.param }</textarea>
						<%-- <input type="hidden" name="newImg" id="newImg"
							value="${discover.newImg }" /> --%>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="accountName">保养手册</label>
					<div class="col-sm-10 box">
						<div id="editor"></div>
						<textarea name="equipParamEntity.maintenanceManual" id="maintenanceManual" class="displays">${equipTypeEntity.equipParamEntity.maintenanceManual }</textarea>
						<%-- <input type="hidden" name="newImg" id="newImg"
							value="${discover.newImg }" /> --%>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-1 no-padding-right"
						for="accountName">操作说明</label>
					<div class="col-sm-10 box">
						<div id="editor"></div>
						<textarea name="equipParamEntity.operationInstructions" id="operationInstructions" class="displays">${equipTypeEntity.equipParamEntity.operationInstructions }</textarea>
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
	param = KindEditor.create('textarea[name="equipParamEntity.param"]', {
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
	maintenanceManual = KindEditor.create('textarea[name="equipParamEntity.maintenanceManual"]', {
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
	operationInstructions = KindEditor.create('textarea[name="equipParamEntity.operationInstructions"]', {
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
var equipTypes = '${equipTypes}';
var equipTs = JSON.parse(equipTypes)
console.log(equipTs);
var equipTd = '${equipTypeEntity.parentId}';

equipType();

function equipType(){
	var options = ""; 
	for (var i = 0; i < equipTs.length; i++) {
		var equipT = equipTs[i];
		if(equipTd != null && equipTd == equipT.id){
			options += '<option value="'+equipT.id+'" selected>'+equipT.name+'</option>';
		}else{
			options += '<option value="'+equipT.id+'">'+equipT.name+'</option>';
		}
	}
	$(".posi").append(options)
}

function commit() {
	console.log(parentId);
//	webside.common.commit('equipTypeFrom', '/equipType/edit.html?parentId='+parentId,
	//		'/equipType/listUI.html?parentId='+parentId+"&tag=1");
	$("#equipTypeFrom").ajaxForm({
		type : 'POST',
		url : 'equipType/edit.html?parentId='+parentId,
		dataType : 'json',
		timeout : 10000,//超时时间设置为10秒；
		success : function(data) {
			console.log(data);
			if (data.success) {
				layer.msg(data.message);
				webside.common.loadPage('/equipType/listUI.html?parentId='+parentId+"&tag=1")
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
	webside.common.loadPage('/equipType/listUI.html?parentId='+parentId+"&tag=1");
}

</script>