//控制层 
app.controller('packingLotServerController', function($scope, $controller,
		$location,$timeout,packingLotServerService,packingLotService) {
	
	// 更具id查询停车场服务
	$scope.findOne = function() {
		var id = $location.search()['id'];
		//当前页为修改页面
		if(id != undefined){
			console.log("修改")
			packingLotServerService.findById(id).success(function(response) {
				$scope.entity = response;
				packingLotService.findAll().success(function(response) {
					$scope.packingLotList = response;
					$scope.packingLotList.forEach(function(value,i){
						if(value.id != $scope.entity.parkingLotId){
							$("#packing_lot_select").append("<option value="+value.id+">"+value.name+"</option>"); 
						}else{
							$("#packing_lot_select").append("<option selected = 'selected' value="+value.id+">"+value.name+"</option>"); 
						}
						layui.form.render('select');
	            	})
				})
				//图片回显
            	var imgUrl = $scope.entity.imgUrl;
				console.log(imgUrl);
            	formdata.append(0,imgUrl);
            	eachFileArray();//遍历图片 显示到页面上
			})
		//当前页为添加页面
		}else{
			packingLotService.findAll().success(function(response) {
				$scope.packingLotList = response;
				$("#packing_lot_select").append("<option value='0' selected = 'selected'>"+"请选择标签"+"</option>"); 
				$scope.packingLotList.forEach(function(value,i){
					$("#packing_lot_select").append("<option value="+value.id+">"+value.name+"</option>"); 
            	})
            	layui.form.render('select');
			})
		}
	}
	$scope.packingLotInit = function(){
		packingLotService.findAllT().success(function(response) {
			$scope.packingLotList = response;
			$("#packing_lot_first_select").append("<option value='0' selected = 'selected'>"+"请选择标签"+"</option>"); 
			$scope.packingLotList.forEach(function(value,i){
				$("#packing_lot_first_select").append("<option value="+value.id+">"+value.name+"</option>"); 
        	})
        	layui.form.render('select');
		})
	}
	$scope.edit = function(packingLotServerEntity) {
		if(packingLotServerEntity.id != null){
			packingLotServerService.update(packingLotServerEntity).success(function(response) {
				if(response.success){
					layer.alert(response.message, {icon: 1},function () {
                        window.location.reload();
                    });
				}else{
					layer.msg(response.message, {icon: 2});
				}
			});
		}else{
			if(packingLotServerEntity.parkingLotId == 0){
				layer.msg("请先选择停车场", {icon: 2});
			}else{
				packingLotServerService.add(packingLotServerEntity).success(function(response) {
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
	}
	$scope.del = function(ids) {
		packingLotServerService.del(ids).success(function(response) {
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
