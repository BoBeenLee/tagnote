var main = angular.module('main', ['ui.bootstrap']);

main.controller("mainController", function ($scope, $modal, $log) {

    $scope.init = function(tags){
        $scope.tags = tags;
    }

    $scope.open = function () {
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'searchModal.html',
            controller: 'ModalInstanceCtrl',
            size: "sm",
        });

        modalInstance.result.then(function (data) {
            console.log(data);
            $scope.tags = data;
            $scope.$apply();
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    }
});

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.
main.controller('ModalInstanceCtrl', function ($scope, $http, $modalInstance) {
    $scope.search = function () {
        if(!$scope.name)
            $scope.name = '';
        $http.get('/tag/search', {
            params: {
                name: $scope.name
            }
        }).then(function (response) {
                $modalInstance.close(response.data);
            },
            function (data) {
                $modalInstance.close(false);
            });
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});