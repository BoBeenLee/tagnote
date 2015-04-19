var article = angular.module('article', ['ui.bootstrap', 'ngTagsInput']);

article.controller("articleController", function ($scope, $http) {

    $scope.getTag = function (val) {
        return $http.get('http://maps.googleapis.com/maps/api/geocode/json', {
            params: {
                address: val,
                sensor: false
            }
        }).then(function (response) {
            return response.data.results.map(function (item) {
                return item.formatted_address;
            });
        });
    }
});