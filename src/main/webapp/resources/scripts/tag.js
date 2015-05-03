var tag = angular.module('tag', ['ui.bootstrap']);

tag.controller("tagController", function ($scope, $modal, $log, $timeout) {
    $scope.open = function (artId, e) {
        var modalInstance = $modal.open({
            animation: true,
            templateUrl: 'sendModal.html',
            controller: 'ModalInstanceCtrl',
            size: "sm",
            resolve: {
                artId: function () {
                    return artId;
                }
            }
        });

        modalInstance.result.then(function (status) {
            //console.log(status);
            $scope.msg = (status) ? "success" : "fail";

            // open
            $timeout(function () {
                angular.element(".pop-up").trigger("click");
            }, 1);
            // close
            $timeout(function () {
                angular.element(".pop-up").trigger("click");
            }, 1500);
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    }
});

// Please note that $modalInstance represents a modal window (instance) dependency.
// It is not the same as the $modal service used above.
tag.controller('ModalInstanceCtrl', function ($scope, $http, $modalInstance, artId) {
    $scope.ok = function () {
        $http.get('/tag/ajax', {
            params: {
                artId: artId,
                uid: $scope.uid
            }
        }).then(function (response) {
                if (response == "success")
                    $modalInstance.close(true);
                else
                    $modalInstance.close(false);
            },
            function (data) {
                $modalInstance.close(false);
            });
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
});