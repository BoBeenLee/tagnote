var article = angular.module('article', [ 'ui.bootstrap', 'ngTagsInput' ]);

article.controller("articleController", function($scope, $http) {
	$scope.tags = [];
	
	$scope.init = function(tags) {
		for(var idx in tags)
			$scope.tags.push({text: tags[idx]});
		console.log(tags);
	}

	$scope.getTag = function(val) {
		// $scope.tags =
		return $http.get('http://maps.googleapis.com/maps/api/geocode/json', {
			params : {
				address : val,
				sensor : false
			}
		}).then(function(response) {
			return response.data.results.map(function(item) {
				return item.formatted_address;
			});
		});
	}

	$scope.getTags = function(tags) {
		var list = [];

		for ( var idx in tags)
			list.push(tags[idx].text);
		return list;
	}
});