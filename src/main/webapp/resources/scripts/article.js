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
		url: '/file/upload'
	});
	$scope.files = [];
	$scope.fileIdx = 0;

	// FILTERS
	uploader.filters.push({
		name: 'customFilter',
		fn: function(item /*{File|FileLikeObject}*/, options) {
			return this.queue.length < 10;
		}
	});

	uploader.onSuccessItem = function(fileItem, response, status, headers) {
		fileItem.file.idx = response.value;
		$scope.files.push(response.value);
		//console.log("data : " + response.value);
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

	$scope.upload = function(item){
		item.upload();
	}

	$scope.remove = function(item){
		var index = $scope.files.indexOf(item.file.idx);
		var fileId = item.file.idx;

		if (index > -1)
			$scope.files.splice(index, 1);
		item.remove();

		$http.get('/file/remove', {
			params : {
				fileId : fileId
			}
		}).then(function(response) {
			console.log("remove : " + response.data);
		});
	}
});