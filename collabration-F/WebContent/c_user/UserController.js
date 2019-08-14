myApp.controller("UserController",function($scope,$http,$rootScope,$location,$cookieStore)
		
	{
	
	$scope.userDetails={"userid":"","username":"","password":"","emailid":"","useraddress":"","userphone":"","role":"","status":"","isonline":""};
	
	$rootScope.userDetails1={"userid":"","username":"","password":"","emailid":"","useraddress":"","userphone":"","role":"","status":"","isonline":""};
	
	
	
  //===============================================REGISTER===================================================================================
	
	
	
	$scope.register=function()
	
	{
		 
		console.log("I'm in Register page");
	   
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/addUser',$scope.userDetails)
		.then(function(response)
		{
			alert("User Added");
		}, function(errorresponse)
		{
			alert("Problem Occured");
			console.log($scope.userDetails);
		});
	}
	
	
  //===============================================CHECK USER=================================================================================
	
	
	$scope.cheakUser=function(user)
	
	{
		console.log("I'm in checkUser");
		
   
		
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/checkUser',user)
		.then(function(response)
		{
			$rootScope.currentUser=response.data;
			
			console.log($rootScope.currentUser);
			$http.put('UserDetails',response.data);
			$cookieStore.put("userDetails",user);
			$location.path("/userHome");
		},function(errorresponse)
		{
			alert("User Name and Password is Not Match");
		});
	}
	
	
	 //===============================================EDIT USER===============================================================================
	
	
	$scope.editUser=function(currentuser)
	{
		console.log("I'm in Edit User");
	
		console.log($rootScope.currentUser.userid);
	
		$http.get('http://localhost:9092/ConnectPeopleMiddle1/get/'+$rootScope.currentUser.userid)
		.then(function(response)
		{
			$rootScope.UserDetails=response.data;
			$location.path("/UpdateProfile");
		},function(errorresponse)
		{
			alert("Problem Occured");
		});
		
	}
	
	
	 //===============================================UPDATE PROFILE==========================================================================
	
	
	
	$scope.updateprofile=function()
	{
		$rootScope.userDetails1.username=$rootScope.currentUser.username;
		
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/updateUser/',$rootScope.currentUser)
		.then(function(response)
		{
			alert(response.data);
		},function(errorresponse)
		{
			alert(errorresponse.data);
			alert(errorresponse.statusText);
		});
	}
	
	
    //=======================================================-LOGOUT==========================================================================
	
	
	$scope.logout=function()
	{
		console.log("I'm in Logout Function")
		
		$cookieStore.remove('UserDetails');
		delete $rootScope.currentUser;
		
//		alert.log("User has Logged Out");
		$location.path("/Login");
	}
//	$scope.refreshpage=function()
//	{
//		$rootScope.currentUser=$cookieStore.get("userDetails");
//	 
//	}
//	refreshpage();
});
  