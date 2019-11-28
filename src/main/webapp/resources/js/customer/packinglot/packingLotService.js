//服务层
app.service('packingLotService',function($http){
	this.add=function(packingLotEntity){
		return $http.post('../../parkingLot/add',packingLotEntity);
	}
	this.findById=function(id){
		return $http.get('../../../parkingLot/findById?id='+id);
	}
	this.update=function(packingLotEntity){
		return $http.post('../../parkingLot/update',packingLotEntity);
	}
	this.del=function(ids){
		return $http.get('../../parkingLot/del?ids='+ids);
	}
	this.findAll=function(){
		return $http.get('../../../parkingLot/listAll');
	}
	this.findAllT=function(){
		return $http.get('../../parkingLot/listAll');
	}
	this.findAllPaymentWay=function(){
		return $http.get('../../../parkingLot/findAllPaymentWay');
	}
	this.findAllParkingLotType=function(){
		return $http.get('../../../parkingLot/findAllParkingLotType');
	}
});
