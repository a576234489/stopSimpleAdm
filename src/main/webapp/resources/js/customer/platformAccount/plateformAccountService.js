//服务层
app.service('plateformAccountService',function($http){
	this.findById=function(id){
		return $http.get('../../../plateformAccount/findById?id='+id);
	}
	this.update=function(userEntity){
		return $http.post('../../plateformAccount/update',userEntity);
	}
	this.updateInfo=function(userEntity){
		return $http.post('../../plateformAccount/updateInfo',userEntity);
	}
	this.add=function(userEntity){
		return $http.post('../../plateformAccount/add',userEntity);
	}
	this.del=function(ids){
		return $http.get('../../plateformAccount/del?ids='+ids);
	}
	this.lock=function(id){
		return $http.get('../../plateformAccount/lock?id='+id);
	}
	this.unlock=function(id){
		return $http.get('../../plateformAccount/unlock?id='+id);
	}
	this.resetPassword=function(id){
		return $http.get('../../plateformAccount/resetPassword?id='+id);
	}
	this.findInfo=function(){
		return $http.get('../../plateformAccount/findInfo');
	}
	this.updatePass=function(userEntity){
		return $http.get('../../plateformAccount/updatePass?oldPassword='+userEntity.oldPassword+'&password='+userEntity.password);
	}
});
