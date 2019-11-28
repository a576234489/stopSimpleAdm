//服务层
app.service('menuService',function($http){
	this.add=function(menuEntity){
		return $http.post('../../menu/add',menuEntity);
	}
	this.findById=function(id){
		return $http.get('../../../menu/findById?id='+id);
	}
	this.update=function(userEntity){
		return $http.post('../../menu/update',userEntity);
	}
	this.del=function(ids){
		return $http.get('../../menu/del?ids='+ids);
	}
});
