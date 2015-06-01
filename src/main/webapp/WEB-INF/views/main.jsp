<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html ng-app="main">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>

<link rel="stylesheet" type="text/css" href="/resources/styles/common/normalize.css" />
<link rel="stylesheet" type="text/css" href="/resources/styles/common/bootstrap.min.css" />
<link rel="stylesheet/less" type="text/css" href="/resources/styles/common/common.less" />
<link rel="stylesheet/less" type="text/css" href="/resources/styles/main.less" />
</head>
<body>
	<div ng-controller="mainController" class="main container" ng-init='init(${ tags })'>
		<div class="header row col-md-12">
			<table>
				<thead>
					<tr>
	                    <th>
	                        <a class="btn btn-default" ng-click="open()" href="#"><img src="/resources/img/common-search.png"></a>
	                    </th>
	                    <th>
	                        <a class="btn btn-default pull-right" href="/article/write"><img src="/resources/img/common-editor-pencil.png"></a>
	                    </th>
	                    <th>
							<a class="btn btn-default pull-right" href="/user/setting"><img src="/resources/img/common-settings-gear.png"></a>	                        
	                    </th>
	                    <th>
	                        <a class="btn btn-default pull-right" href="/user/logout"><img src="/resources/img/common-logout-signout-exit.png"></a>
	                    </th>
		            </tr>					
				</thead>
			</table>
		</div>
		<div class="content row col-md-12">
			<span ng-repeat="tag in tags" class="label label-default" 
				style="background-color: {{ '#' + tag.color }}"> <a
				href="/tag?name={{ tag.name }}"> {{ tag.name }}</a></span>
		</div>
	</div>

	<script type="text/javascript" src="/resources/scripts/common/less.min.js"></script>
	<script type="text/javascript" src="/resources/scripts/common/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="/resources/scripts/common/angular.min.js"></script>
	<script type="text/javascript" src="/resources/scripts/common/ui-bootstrap-tpls-0.12.1.min.js"></script>
	<script type="text/javascript" src="/resources/scripts/common/bootstrap.min.js"></script>
	<script type="text/javascript" src="/resources/scripts/common/common.js"></script>
	<script type="text/javascript" src="/resources/scripts/main.js"></script>
	
	<!-- modal -->
	<script type="text/ng-template" id="searchModal.html">
    <div class="search-modal">
        <div class="modal-header">
            <h3 class="modal-title">Search</h3>
        </div>
        <div class="modal-body">
            <form>
                <div class="form-group">
                    <div class="input-group">
                        <div class="input-group-addon">
                        </div>
                        <input ng-model="name" type="text" class="form-control" placeholder="Tag Name">
                    </div>
                </div>
                <div class="form-group">
                    <button class="btn btn-primary" ng-click="search()">Search</button>
                    <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
                </div>
            </form>
        </div>
    </div>
	</script>
</body>
</html>