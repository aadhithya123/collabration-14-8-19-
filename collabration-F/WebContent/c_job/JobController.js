myApp.controller("JobController",function($scope,$rootScope,$http,$location){
	
	$scope.job={"jobid":0,"description":"","designation":"","companyname":"","location":"","ctc":"","lastdateforapply":"","skills":""};
	$scope.jobs;
	$scope.publish=function()
	{
		console.log("I'm in Publish Function");
		console.log($scope.job);
		
		$http.post('http://localhost:9092/ConnectPeopleMiddle1/addJob/',$scope.job)
		.then(function(response)
		{
			alert("Job Posted");
		},function(errorresponse)
		{
			alert("Error Occured While Posting Job");
		});
	}
	function showJobs()
	{
		console.log("I'm in show Jobs");
	
		$http.get('http://localhost:9092/ConnectPeopleMiddle1/showAllJob/')
		.then(function(response)
		{
			$scope.jobs=response.data;
			
		},function(errorresponse)
		{
			alert("Error Occured While Posting Job");
		});	
	}
	showJobs();
	
});

