//服务层
app.service('packingLotServerService',function($http){
	this.add=function(packingLotServerEntity){
		return $http.post('../../parkingLotServer/add',packingLotServerEntity);
	}
	this.findById=function(id){
		return $http.get('../../../parkingLotServer/findById?id='+id);
	}
	this.update=function(packingLotServerEntity){
		return $http.post('../../parkingLotServer/update',packingLotServerEntity);
	}
	this.del=function(ids){
		return $http.get('../../parkingLotServer/del?ids='+ids);
	}
});
