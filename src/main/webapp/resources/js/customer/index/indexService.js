//服务层
app.service('indexService',function($http){
	this.initSideMenu = function(){
		return $http.get("../index/data");
	}
});
