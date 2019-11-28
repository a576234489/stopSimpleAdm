//服务层
app.service('roleService',function($http){
	this.findAll=function(){
		return $http.get('../../../role/findAll');
	}
	this.findAllCotainSuperAdm=function(){
		return $http.get('../../../role/findAllCotainSuperAdm');
	}
	this.findAllT=function(id){
		return $http.get('../../role/findAll');
	}
	this.findById=function(id){
		return $http.get('../../../role/findById?id='+id);
	}
	this.update=function(roleEntity){
		return $http.post('../../role/update',roleEntity);
	}
	this.add=function(roleEntity){
		return $http.post('../../role/add',roleEntity);
	}
	this.del=function(ids){
		return $http.get('../../role/del?ids='+ids);
	}
	this.resourceTree=function(roleId){
		return $http.get('../../role/resourceTree?roleId='+roleId);
	}
	this.grantPermission=function(roleId,resourceIds){
		return $http.get('../../../role/grantPermission?roleId='+roleId+"&resourceIds="+resourceIds);
	}
});
