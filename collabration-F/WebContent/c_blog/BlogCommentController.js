myApp.controller("BlogCommentController",function($scope,$rootScope,$http,$location)
{
	$scope.blog={"blogid":"","blogname":"","blogcontent":"","username":"","createdate":"","status":"","likes":"","dislike":""};	

	$scope.blogComments;
	
	$scope.blogComment={"blogcommentid":0,"blogcomment":"","username":"","commentdate":"" ,"blogid":0};
	
    function loadAllComments()
    {
    	console.log("I'm in the Comment");
    	$http.get('http://localhost:9092/ConnectPeopleMiddle1/getAllBlogComment/'+$rootScope.blogid)
		.then(function(response)
		{
			$scope.blogComments=response.data;
		});   	
    }
    
    function blogInfo()
    {
    	console.log("I'm in the BlogInfo");
    	$http.get('http://localhost:9092/ConnectPeopleMiddle1/getSingleBlog/'+$rootScope.blogid)
		.then(function(response)
		{
			$scope.blog=response.data;
		});
    }
    
    $scope.addComment=function(blogcomment)
    {console.log(blogcomment)
    	console.log("I'm in  Add Comment");
    	blogcomment.blogid=$rootScope.blogid;
    	blogcomment.username=$rootScope.currentUser.username;
    	console.log(blogcomment)
    	$http.post('http://localhost:9092/ConnectPeopleMiddle1/addBlogComment',blogcomment)
		.then(function(response)
		{
			alert("Blog Comment Added");
			
			 loadAllComments();
			// blogInfo();
		});
    }
    
    $scope.deleteComment=function(blogcommentid)
    {
    	console.log("I'm in Delete Comment");
    	$http.post('http://localhost:9092/ConnectPeopleMiddle1/deleteBlogComment/'+blogcommentid)
		.then(function(response)
		{
			alert("Blog Comment is Deleted");
			 loadAllComments();
			 blogInfo();
		},function(errorresponse)
		{
			alert("Error Occured While Deleting the Command");
		});
    }
    
    loadAllComments();
    blogInfo();
});