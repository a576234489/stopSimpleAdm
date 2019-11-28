 //控制层 
 app.controller('indexController' ,function($scope,$controller,indexService){
	 $scope.initSideMenu = function(){
		 indexService.initSideMenu().success(function(response){
				$scope.menuList = response.data.list;
			});
		}
});	
