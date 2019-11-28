//控制层 
app.controller('menuController', function($scope, $controller,
		$location, menuService) {
	// 更具id查询角色
	$scope.findOne = function() {
		var id = $location.search()['id'];
		$scope.entity = {};
		$scope.typeList = [{id:-1,name:"请选择标签"},{id:0,name:"菜单"},{id:1,name:"按钮"}];
		//当前页为修改页面
		if(id != undefined){
			// 更具id查询平台账号
			menuService.findById(id).success(function(response) {
				$scope.entity = response;
			});
		//当前页为添加页面
		}else{
			$scope.entity.type = -1;
		}
	}
	//菜单编辑
	$scope.edit = function(entity,treeGrid) {
		console.log(entity);
		if(entity.id != null){
			//更新
			menuService.update(entity).success(function(response) {
				if(response.success){
					layer.msg(response.message,{icon: 1},function () {
                      //  window.location.reload();
                        treeGrid.reload('LAY-app-menu-list');
                    });
				}else{
					layer.msg(response.message, {icon: 2});
				}
			});
		}else{
			//添加
			menuService.add(entity).success(function(response) {
				if(response.success){
					layer.alert(response.message, {icon: 1},function () {
                        window.location.reload();
                    });
				}else{
					layer.msg(response.message, {icon: 2});
				}
			});
							
		}
	}
	$scope.del = function(ids) {
		menuService.del(ids).success(function(response) {
			if(response.success){
				layer.alert(response.message, {icon: 1},function () {
                    window.location.reload();
                });
			}else{
				layer.msg(response.message, {icon: 2});
			}
		});
	}
});
