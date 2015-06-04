var article = angular.module('article', [ 'ui.bootstrap', 'ngTagsInput', 'angularFileUpload']);

article.controller("articleController", function($scope, $http, FileUploader) {
	$scope.tags = [];
	
	$scope.init = function(tags) {
		for(var idx in tags)
			$scope.tags.push({text: tags[idx]});
		//console.log(tags);
	}

	$scope.getTag = function(val) {
		// $scope.tags =
		return $http.get('/tag/ajax', {
			params : {
				name : val
			}
		}).then(function(response) {
			return response.data.map(function(item) {
				return item.name;
			});
		});
	}

	$scope.getTags = function(tags) {
		var list = [];

		for ( var idx in tags)
			list.push(tags[idx].text);
		return list;
	}

	// process upload
	var uploader = $scope.uploader = new FileUploader({
		url: '/article/upload'
	});
	$scope.files = [];

	// FILTERS
	uploader.filters.push({
		name: 'customFilter',
		fn: function(item /*{File|FileLikeObject}*/, options) {
			return this.queue.length < 10;
		}
	});

	uploader.onSuccessItem = function(fileItem, response, status, headers) {
		$scope.files.push(response.data);
		//console.info('onSuccessItem', fileItem, response, status, headers);
	};
	uploader.onErrorItem = function(fileItem, response, status, headers) {
		console.info('onErrorItem', fileItem, response, status, headers);
	};
	uploader.onCancelItem = function(fileItem, response, status, headers) {
		console.info('onCancelItem', fileItem, response, status, headers);
	};
	uploader.onCompleteItem = function(fileItem, response, status, headers) {
		console.info('onCompleteItem', fileItem, response, status, headers);
	};
});