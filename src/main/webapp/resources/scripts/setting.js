/**
 *
 */

var setting = angular.module('setting', []);

setting.controller("settingController", function ($scope, $http) {
    $scope.generate = function(){
        $http.get('/user/id/get', {
        }).then(function (uid) {
            $scope.uid = uid;
        },
        function (data) {
            $scope.uid = "fail";
        });
    }
});