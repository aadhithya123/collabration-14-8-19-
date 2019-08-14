myApp.controller("blogController",function($scope,$location,$rootScope,$http,$cookieStore)
	{

	$scope.blog={"blogid":"","blogname":"","blogcontent":"","username":"","createdate":"","status":"","likes":"","dislike":""};
	
	$scope.blogDetail;
	
	$rootScope.blog1={"blogid":"","blogname":"","blogcontent":"","username":"","createdate":"","status":"","likes":"","dislike":""};
	
	$rootScope.blogid;
	
	$scope.addBlog=function()
	{
		console.log("I'm in Add Blog");
		
		$scope.Blog=$rootScope.currentUser.username;
		
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/addBlog',$scope.blog)
		.then(function(response)
		{
			alert("Blog Added");
			showAllBlog();
		},function(errorresponse)
		{
			alert("Error Ocurred While Blog Adding");
		});
	}
	
	$scope.updateBlog=function()
	{
		
		console.log("I'm in Update Blog");
		
		$rootScope.blog1.username=$rootScope.currentUser.username;
		
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/updateBlog/',$rootScope.blogtobeupdated)
		.then(function(response)
		{
			alert("Blog Updated");
			$location.path("/showBlog")
		},function(errorresponse)
		{
			alert("Error Ocurred While Updating the Blog");
		});
	}
	
	$scope.showComment=function(blogid)
	{
		console.log("I'm in Blog Comment");
		$rootScope.blogid=blogid;
		$location.path("/showComments")
	}
	
	$scope.incrementLikes=function(blogid)
	{
		console.log("I'm in Increment Likes");
		
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/incrementLikes/'+blogid)
		.then(function(response)
		{
			alert("Thank You For Incrementing the Blog");
			showAllBlog();
		},function(errorresponse)
		{
			alert("Error Ocurred While incrementing the Blog");
		});
	}
	
	$scope.incrementDisLikes=function(blogid)
	{
		console.log("I'm in Increment DisLikes");
		
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/incrementDisLikes/'+blogid)
		.then(function(response)
		{
			alert("OOP'S We will try to improve.Thanks for your Feedback!!!");
			showAllBlog();
		},function(errorresponse)
		{
			alert("Error Ocurred While incrementing the DisLikes the Blog");
		});
	}
	
	
	$scope.accept=function(blogid)
	{
		console.log("I'm in Accept Method");
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/approveBlog/'+blogid)
		.then(function(response)
		{
			alert("Blog Approved")
		},function(errorresponse)
		{
			alert("Blog Not Approved")
		});
		
	}
	
	$scope.reject=function(blogid)
	{
		console.log("I'm in Reject Method");
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/rejectBlog/'+blogid)
		.then(function(response)
		{
			alert("Blog Rejected")
		},function(errorresponse)
		{
			alert("Blog Not Rejected")
		});
		
	}
	
	$scope.deleteBlog=function(blogid)
	{
		console.log("I'm in Deleting Blog");
		
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/deleteBlog/'+blogid)
		.then(function(response)
		{
			alert("!!!Blogis Successfully Deleted");
			showAllBlog();
		},function(errorresponse)
		{
			alert("Error Ocurred While Deleting the Blog");
			showAllBlog();
		});
	}
	
	$scope.editBlog=function(blog)
	{
		console.log("I'm in Edit Blog");
		$location.path("/editBlog");
		$rootScope.blogtobeupdated=blog;
//		$http.post('http://localhost:9092/ConnectPeopleMiddle1/updateBlog/'+blogid)
//		.then(function(response)
//		{
//			$rootScope.blog1=response.data;
//			console.log(response.data);
//			console.log("--------------");
//			console.log($rootScope.blog1);
//			
//		},function(errorresponse)
//		{
//			alert("Error Ocurred While Deleting the Blog");
//			showAllBlog();
//		});
	}
	
	function showAllBlog()
	{
	console.log("I'm in Show All Blog");
	
	$http.get('http://localhost:9092/ConnectPeopleMiddle1/showAllBlog')
	.then(function(response)
	{
		$scope.blogDetail=response.data;
	},function(errorresponse)
	{
		alert("Error Ocurred While Blog Retrieving the  Blogs");
	});
	}
	showAllBlog();
});