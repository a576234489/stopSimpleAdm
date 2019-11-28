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
<h1>
		<c:if test="${empty staffEntity}">
		员工添加
		</c:if>
		<c:if test="${!empty staffEntity}">
		员工修改
		</c:if>
</h1>
<div class="row" style="margin-top: 5px;">
	<div class="col-xs-12">
		<form id="staffFrom" name="brigFrom" class="form-horizontal"
			role="form" method="post">
			<c:if test="${!empty staffEntity}">
				<input type="hidden" id="pageNum" name="pageNum"
					value="${page.pageNum }">
				<input type="hidden" id="pageSize" name="pageSize"
					value="${page.pageSize }">
				<input type="hidden" id="orderByColumn" name="orderByColumn"
					value="${page.orderByColumn }">
				<input type="hidden" id="orderByType" name="orderByType"
					value="${page.orderByType }">
				<input type="hidden" name="id"  value="${staffEntity.id }">	
			</c:if>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">账号名称</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="userName"
							value="${staffEntity.userName}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">姓名</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.name"
							value="${staffEntity.starffinfo.name}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">名族</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.nation"
							value="${staffEntity.starffinfo.nation}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">婚姻状况</label>
				<div class="col-sm-10 noform">
					<select id="starffinfo.maritalStatus" class="form-control  depa" name="starffinfo.c">
						<option value="1" <c:if test="${staffEntity.starffinfo.maritalStatus==1 }">selected </c:if> >已婚</option>
						<option value="2" <c:if test="${staffEntity.starffinfo.maritalStatus==2 }">selected </c:if> >未婚</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">省份证号码</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.idCard"
							value="${staffEntity.starffinfo.idCard}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">性别</label>
				<div class="col-sm-10 noform">
					<select id="starffinfo.sex" class="form-control  depa" name="starffinfo.sex">
						<option value="1" <c:if test="${staffEntity.starffinfo.sex==1 }">selected </c:if> >男</option>
						<option value="2" <c:if test="${staffEntity.starffinfo.sex==2 }">selected </c:if> >女</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">出生年月</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input type="text" name="starffinfo.dateBirth" value="${staffEntity.starffinfo.dateBirth}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">年龄</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.age"
							value="${staffEntity.starffinfo.age}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">籍贯</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.nativePlace"
							value="${staffEntity.starffinfo.nativePlace}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">身高</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.stature"
							value="${staffEntity.starffinfo.stature}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">体重</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.weight"
							value="${staffEntity.starffinfo.weight}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">身份证地址</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.idCardAddress"
							value="${staffEntity.starffinfo.idCardAddress}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">联系电话</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.tel"
							value="${staffEntity.starffinfo.tel}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">办公室电话</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.officeTel"
							value="${staffEntity.starffinfo.officeTel}" />
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">消防队名称</label>
				<div class="col-sm-10 noform">
					<select class="form-control  brig" name="starffinfo.fireBrigadeSetId">
						<option style='display: none'></option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">职位名称</label>
				<div class="col-sm-10 noform">
					<select class="form-control  posi" name="starffinfo.positionId">
						<option style='display: none'></option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-sm-1 no-padding-right"
					for="name">员工住址</label>
				<div class="col-sm-10">
					<div class="clearfix">
						<input  class="form-control" type="text" name="starffinfo.address"
							value="${staffEntity.starffinfo.address}" />
					</div>
				</div>
			</div>
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
		onclick="webside.common.loadPage('/staff/listUI.html?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }')"
		class="btn btn-info btn-sm">
		<i class="fa fa-undo"></i>&nbsp;返回
	</button>
</div>
<script>
var list = '${list}';
var brigs = JSON.parse(list);
console.log(22);
console.log(brigs);
var pid = '${staffEntity.starffinfo.fireBrigadeSetId}';

var positions = '${positions}';
if(positions!=''){
	var posis = JSON.parse(positions)
	var posiId = '${staffEntity.starffinfo.positionId}';
	posi();
}


brig();



var province = '${brigade.province }';
var city = '${brigade.city}';
var direct = '${brigade.area}';
addressInit('provice', 'city', 'area', province, city, direct);
function brig(){
	console.log(brigs);
	var options = "";
	for (var i = 0; i < brigs.length; i++) {
		var brig = brigs[i];
		if(pid != null && pid == brig.id){
			options += '<option value="'+brig.id+'" selected>'+brig.fireBrigadeName+'</option>';
		}else{
			options += '<option value="'+brig.id+'">'+brig.fireBrigadeName+'</option>';
		}
		
		if(brig.subBrig !=null){
			var spa = "&nbsp;&nbsp;&nbsp;&nbsp;"
			options += brigSubDeal(brig.subBrig,spa);
		}
	}
	console.log(options);
	$(".brig").append(options)
}

function brigSubDeal(subBrigs,spa){
	var options = "";
	for (var i = 0; i < subBrigs.length; i++) {
		var brig = subBrigs[i];
		if(pid != null && pid == brig.id){
			options += '<option value="'+brig.id+'" selected>'+spa+brig.fireBrigadeName+'</option>';
		}else{
			options += '<option value="'+brig.id+'">'+spa+brig.fireBrigadeName+'</option>';
		}
		
		if(brig.subBrig !=null){
			options += brigSubDeal(brig.subBrig,spa+"&nbsp;&nbsp;&nbsp;&nbsp;");
		}
	}
	return options;
}
function posi(){
	console.log(posis);
	var options = "";
	for (var i = 0; i < posis.length; i++) {
		var position = posis[i];
		if(posiId != null && posiId == position.id){
			options += '<option value="'+position.id+'" selected>'+position.name+'</option>';
		}else{
			options += '<option value="'+position.id+'">'+position.name+'</option>';
		}
		
		if(position.subPosi !=null){
			var spa = "&nbsp;&nbsp;&nbsp;&nbsp;"
			options += posiSubDeal(position.subPosi,spa);
		}
	}
	console.log(options);
	$(".posi").append(options)
}

function posiSubDeal(subPosi,spa){
	var options = "";
	for (var i = 0; i < subPosi.length; i++) {
		var posi = subPosi[i];
		if(posiId != null && posiId == posi.id){
			options += '<option value="'+posi.id+'" selected>'+spa+posi.name+'</option>';
		}else{
			options += '<option value="'+posi.id+'">'+spa+posi.name+'</option>';
		}
		
		if(posi.subPosi !=null){
			options += posiSubDeal(posi.subPosi,spa+"&nbsp;&nbsp;&nbsp;&nbsp;");
		}
	}
	return options;
}

function commit() {
	webside.common.commit('staffFrom', '/staff/edit.html',
			'/staff/listUI.html');
}
</script>