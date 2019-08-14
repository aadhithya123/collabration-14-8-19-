myApp.controller("ForumController",function($scope,$rootScope,$http,$location,$cookieStore)
{
	$scope.forum={"forumid":0,"username":"","createdate":"","forumcontains":""}
	$scope.user={"userid":"","username":"","password":"","emailid":"","useraddress":"","userphone":"","role":"","status":"","isonline":""};
	
	$scope.add=function(forum)
	{
		console.log($rootScope.currentUser)
		console.log("I'm in Forum");
     	

	   $scope.user=$cookieStore.get("userDetails")
	   $scope.forum.username=$scope.user.username
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/addForum',forum)
		.this(function(response)
		{
			alert("Forum is the Added");
			showAllForum();
		},function(errorresponse)
		{
			alert("Problem Occured While Adding Forum");
			showAllForum();
		});
		
	}
	
	
	
	
	
	
	
	function showAllForum()
	{
		console.log("I'm in ShowAllForum");
		$http.get('http://localhost:9092/ConnectPeopleMiddle1/showAllForum')
		.then(function(response)
		{
			$scope.forumdetails=response.data;
		},function(errorresponse)
		{
			alert("Error Occuring While Showing the Forum");
		});
	}
	
	$scope.delete=function(forumid)
	{
		console.log("I'm in Delete Forum");
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/deleteForum/'+forumid)
		.then(function(response)
		{
			alert("!!!Forum Successfully Deleted");
			showAllForum();
			
		},function(errorresponse)
		{
			console.log(errorresponse)
			alert("Error Ocurred While Deleting the Forum");
			
		});
	}
	showAllForum();
});