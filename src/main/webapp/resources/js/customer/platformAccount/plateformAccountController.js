//控制层 
app.controller('plateformAccountController', function($scope, $controller,
		$location, plateformAccountService,roleService) {
	// 初始化平台账号搜索功能得下拉框数据
	$scope.initData = function() {
		roleService.findAllT().success(function(response) {
			
			$("#is_locked").append("<option value='-1' selected = 'selected'>"+"请选择是否锁定"+"</option>");
			$("#is_locked").append("<option value='0' >"+"正常"+"</option>");
			$("#is_locked").append("<option value='1' >"+"锁定"+"</option>");
			$("#role_id").append("<option value='0' selected = 'selected'>"+"请选择角色"+"</option>");
			console.log(11);
			response.forEach(function(value,i){
				$("#role_id").append("<option value="+value.id+">"+value.name+"</option>"); 
        	})
        	layui.form.render('select');
		})
	}
	// 基本资料
	$scope.initInfo = function() {
		roleService.findAllCotainSuperAdm().success(function(response) {
			$scope.roleList = response;
			plateformAccountService.findInfo().success(function(response) {
				$scope.entity = response.data;
				$scope.roleList.forEach(function(value,i){
					if(value.id != $scope.entity.role.id){
						$("#role_id").append("<option value="+value.id+">"+value.name+"</option>"); 
					}else{
						$("#role_id").append("<option selected = 'selected' value="+value.id+">"+value.name+"</option>"); 
					}
            	})
            	layui.form.render('select');				
            	if($scope.entity.userInfo.sex == 1){
            		$("input[name='sex'][title='男']").attr('checked',true)
				}else{
					$("input[name='sex'][title='女']").attr('checked',true)
				}
            	layui.form.render();
			})
		})
	}
	// 更具id查询平台账号
	$scope.findOne = function() {
		var id = $location.search()['id'];
		// 查询除超级管理员之外的所有角色
		roleService.findAll().success(function(response) {
			$scope.roleList = response;
			//当前页为修改页面
			if(id != undefined){
				$scope.isShow = false;
				$("#accountNameId").attr("disabled","disabled");
				// 更具id查询平台账号
				plateformAccountService.findById(id).success(function(response) {
					$scope.entity = response;
					$scope.roleList.forEach(function(value,i){
						if(value.id != $scope.entity.role.id){
							$("#role_id").append("<option value="+value.id+">"+value.name+"</option>"); 
						}else{
							$("#role_id").append("<option selected = 'selected' value="+value.id+">"+value.name+"</option>"); 
						}
						layui.form.render('select');
	            	})
				});
			//当前页为添加页面
			}else{
				$("#role_id").append("<option value='0' selected = 'selected'>"+"请选择角色"+"</option>");
				$scope.roleList.forEach(function(value,i){
					$("#role_id").append("<option value="+value.id+">"+value.name+"</option>"); 
            	})
            	layui.form.render('select');
				$("#accountNameId").attr("disabled",false);
				$scope.isShow = true;
				$scope.entity = {role:{id:0}};
			}
		});
	}
	$scope.edit = function(entity) {
		console.log(entity);
		if(entity.id != null){
			//更新
			plateformAccountService.update(entity).success(function(response) {
				if(response.success){
					layer.alert(response.message, {icon: 1},function () {
                        window.location.reload();
                    });
				}else{
					layer.msg(response.message, {icon: 2});
				}
			});
		}else{
			var reg = new RegExp("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$");
			if(reg.test(entity.accountName)){
				if(entity.password != "" && entity.repassword != "" ){
					if(entity.password.length >= 6 && entity.password.length <= 20){
						if(entity.repassword.trim() == entity.password.trim()){
							if(entity.role.id != 0 && !isNaN(entity.role.id) ){
								//添加
								plateformAccountService.add(entity).success(function(response) {
									if(response.success){
										layer.alert(response.message, {icon: 1},function () {
					                        window.location.reload();
					                    });
									}else{
										layer.msg(response.message, {icon: 2});
									}
								});
							}else{
								layer.msg("角色选择不能为空");
							}
						}else{
							layer.msg("两次输入得密码不一致");
						}
					}else{
						layer.msg("密码长度必须为6~20位");
					}
				}else{
					layer.msg("密码或确认密码不能为空");
				}
			}else{
				layer.msg("用户名格式不正确",{icon: 2});
			}
		}
	}
	$scope.del = function(ids) {
		plateformAccountService.del(ids).success(function(response) {
			if(response.success){
				layer.alert(response.message, {icon: 1},function () {
                    window.location.reload();
                });
			}else{
				layer.msg(response.message, {icon: 2});
			}
		});
	}
	$('#accountNameId').blur( function(){
		
		var reg = new RegExp("^[a-z0-9A-Z]+[- | a-z0-9A-Z . _]+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z]{2,}$");
		console.log($("#accountNameId").val());
		 if(!reg.test($("#accountNameId").val())){
			 $scope.accountNameCheckErr = "账户名格式有误";
		 }else{
			 $scope.accountNameCheckErr = "";
		 }
		 $scope.$apply();
	})
	$scope.lock = function(id){
		plateformAccountService.lock(id).success(function(response) {
			if(response.success){
				layer.alert(response.message, {icon: 1},function () {
                    window.location.reload();
                });
			}else{
				layer.msg(response.message, {icon: 2});
			}
		});
	}
	$scope.unlock = function(id){
		plateformAccountService.unlock(id).success(function(response) {
			if(response.success){
				layer.alert(response.message, {icon: 1},function () {
                    window.location.reload();
                });
			}else{
				layer.msg(response.message, {icon: 2});
			}
		});
	}
	$scope.reset = function(id){
		plateformAccountService.resetPassword(id).success(function(response) {
			if(response.success){
				layer.alert(response.message, {icon: 1},function () {
                    window.location.reload();
                });
			}else{
				layer.msg(response.message, {icon: 2});
			}
		});
	} 
	$scope.updateInfo = function(entity){
		plateformAccountService.updateInfo(entity).success(function(response) {
			if(response.success){
				layer.alert(response.message, {icon: 1},function () {
                    window.location.reload();
                });
			}else{
				layer.msg(response.message, {icon: 2});
			}
		});
	}
	$scope.updatePass = function(entity){
		plateformAccountService.updatePass(entity).success(function(response) {
			if(response.success){
				layer.alert(response.message, {icon: 1},function () {
					window.parent.location=layui.setter.baseUrl + response.data
                });
			}else{
				layer.msg(response.message, {icon: 2});
			}
		});
	}
		
});
