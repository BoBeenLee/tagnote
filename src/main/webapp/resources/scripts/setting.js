var setting = angular.module('setting', []);

setting.controller("settingController", function ($scope, $http) {
    $scope.generate = function(){
        $http.get('/user/id/get').then(function (response) {
//        	console.log(response);
            $scope.uid = response.data.value;
        },
        function (data) {
            $scope.uid = "fail";
        });
    }
});