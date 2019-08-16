myApp.controller("Friendcontroller",function($scope,$rootScope,$http,$location)
{
	$scope.friend={"friendid":0,"friendname":"","username":"","status":""};
	
	$scope.showFriendList;
	
	$scope.pendingFriendList;
	
	$scope.suggestedFriendList;
	
	function showFriendList()
	{
		console.log("I'm in Show Friend List");

		$http.get('http://localhost:9092/ConnectPeopleMiddle1/showFriendList/'+$rootScope.currentUser.username)
		.then(function(response)
		{
			$scope.friendList=response.data;
		},function(errorresponse)
		{
			alert("Error Occured");
		});
	}
	
	function showPendingFriendList()
	{
		console.log("I'm in Pending Friend List");
		$http.get('http://localhost:9092/ConnectPeopleMiddle1/showPendingRequest/'+$rootScope.currentUser.username)
		.then(function(response)
		{
			$scope.pendingFriendlist=response.data;
			console.log($scope.pendingFriendlist)
		},function(errerresponse)
		{
			alert("Error Occured");
		});
	}
	
	function showSuggestedFriendList()
	{
		console.log("I'm in Suggested Friend List");
		$http.get('http://localhost:9092/ConnectPeopleMiddle1/showSuggestedFriend/'+$rootScope.currentUser.username)
		.then(function(response)
		{
			$scope.suggestedFriendlist=response.data;
		},function(errerresponse)
		{
			alert("Error Occured");
		});
	}
	
	$scope.sendFriendRequest=function(friendname)
	{
		console.log("I'm in Send Friend Request");
		
		$scope.friendusername=$rootscope.currentUser.username;
		$scope.friend.friendusername=friendname;
		
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/sendFriendRequest/'+friendid)
		.then(function(response)
		{
			alert("Friend Request Send");
			showFriendList();
			showPendingFriendList();
			showSuggestedFriendList();
		},function(errerresponse)
		{
			alert("Error Occured");
		});
	}
	
	$scope.accept=function(friendid)
	{
		console.log("I'm in Accept Friend request");
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/acceptFriendRequest/'+friendid)
		.then(function(response)
		{
			alert("Friend Request is Accepted");
			showFriendList();
			showPendingFriendList();
			showSuggestedFriendList();
		},function(errerresponse)
		{
			alert("Error Occured");
		});
	}
	
	$scope.unFriend=function(friendid)
	{
		console.log("I'm in UnFriend Request");
		$http.get('http://localhost:9092/ConnectPeopleMiddle1/deleteFriendRequest/'+friendid)
		.then(function(response)
		{
			alert("Friend Request is Deleted");
			showFriendList();
			showPendingFriendList();
			showSuggestedFriendList();
		},function(errerresponse)
		{
			alert("Error Occured");
		});
	}
	showFriendList();
	showPendingFriendList();
	showSuggestedFriendList();
});

