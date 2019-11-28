//控制层 
app.controller('packingLotController', function($scope, $controller,
		$location, packingLotService) {
	// 更具id查询停车场
	$scope.findOne = function() {
		var id = $location.search()['id'];
		console.log(11);
		packingLotService.findAllPaymentWay().success(function(response) {
			response.forEach(function(value,i){
				$("#payment_way_select").append("<option value="+value.id+">"+value.paymentName+"</option>"); 
        	})
        	console.log(layui.form);
        	layui.formSelects.render('payment_way');
		})	
		packingLotService.findAllParkingLotType().success(function(response) {
			response.forEach(function(value,i){
				$("#parking_lot_type_select").append("<option value="+value.id+">"+value.type+"</option>"); 
        	})
        	layui.formSelects.render('parking_lot_type');
		})
		//当前页为修改页面
		if(id != undefined){
			// 更具id查询平台账号
			packingLotService.findById(id).success(function(response) {
				console.log(1122)
				$scope.entity = response;
				addressInitUpdate('provice', 'city', 'area', response.province, response.city, response.area);
				//重新渲染省市区下拉框
				//$("dd").find("option[text='pxx']").attr("selected","selected")
				layui.form.render("select");
				//formSelect多选下拉框回显
//				var parking_lot_type_array = [];
//				response.parkingLotTypes.forEach(function(value,i){
//					parking_lot_type_array.push(value.id)
//            	})
//            	layui.formSelects.value('parking_lot_type', parking_lot_type_array);
				layui.formSelects.config('payment_way', {
			            searchUrl: 'http://192.168.168.45:8080/stopSimpleAdm/parkingLot/findHavingPaymentWay?id='+id,
			            success: function(id, url, searchVal, result){
			            }
			    });
				layui.formSelects.config('parking_lot_type', {
		            searchUrl: 'http://192.168.168.45:8080/stopSimpleAdm/parkingLot/findHavingParkingLotType?id='+id,
		            success: function(id, url, searchVal, result){
		            }
				});
//				var payment_way_entitys_array = [];
//				response.paymentWayEntitys.forEach(function(value,i){
//					payment_way_entitys_array.push(value.id)
//            	})
//            	layui.formSelects.value('payment_way', payment_way_entitys_array);
            	//复选框回显
            	if(response.isChargingPile==1){
            		$('.is_chargingpile_input').val(1);
            		var isChargingPile_t = $("input[name='isChargingpile2']");
            		isChargingPile_t[0].checked=true;
            		layui.form.render("checkbox");
            		$('.charging_pile_total').show();
            	}else{
            		$('.is_chargingpile_input').val(2);
            		$('.charging_pile_total').hide();
            	}
            	//复选框回显
            	if(response.isChargingPile==1){
            		$('.is_longrent_input').val(1);
            		var isLongrent_t = $("input[name='isLongrent2']");
            		isLongrent_t[0].checked=true;
            		layui.form.render("checkbox");
            	}else{
            		$('.is_longrent_input').val(2);
            	}
            	//图片回显
            	var imageUrl = response.imageUrls;
            	var imageUrls = imageUrl.split(",");
            	imageUrls.forEach(function(value,i){
            		formdata.append(i,value);
            	})
            	eachFileArray();//遍历图片 显示到页面上
			});
		//当前页为添加页面
		}else{
		}
	}
	$scope.edit = function(packingLotEntity) {
		console.log(packingLotEntity);
		if(packingLotEntity.id != null){
			//更新
			console.log(packingLotEntity);
			if(packingLotEntity.province == "请选择省" ||packingLotEntity.city == "请选择市" || packingLotEntity.area == "请选择区/县"){
				layer.msg("省市区选择不能为空", {icon: 2});
			}else{
				packingLotService.update(packingLotEntity).success(function(response) {
					if(response.success){
						layer.alert(response.message, {icon: 1},function () {
	                        window.location.reload();
	                    });
					}else{
						layer.msg(response.message, {icon: 2});
					}
				});
			}
		}else{
			if(packingLotEntity.province == "请选择省" ||packingLotEntity.city == "请选择市" || packingLotEntity.area == "请选择区/县"){
				layer.msg("省市区选择不能为空", {icon: 2});
			}else{
				packingLotService.add(packingLotEntity).success(function(response) {
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
		packingLotService.del(ids).success(function(response) {
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
