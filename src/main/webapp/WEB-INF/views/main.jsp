<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Main</title>

<link rel="stylesheet" type="text/css"
	href="/resources/styles/common/normalize.css" />
<link rel="stylesheet" type="text/css"
	href="/resources/styles/common/bootstrap.min.css" />
<link rel="stylesheet/less" type="text/css"
	href="/resources/styles/common/common.less" />
<link rel="stylesheet/less" type="text/css"
	href="/resources/styles/main.less" />
</head>
<body>
	<div class="main container">
		<div class="header row col-md-12">
			<table>
				<thead>
					<tr>
	                    <th>
	                        <a class="btn btn-default" href="#">home</a>
	                    </th>
	                    <th>
	                        <a class="btn btn-default pull-right" href="/article/write">write</a>
	                    </th>
	                    <th>
	                        <a class="btn btn-default pull-right" href="/user/logout">logout</a>
	                    </th>
	                    <th>
	                        <a class="btn btn-default pull-right" href="/user/setting">settings</a>
	                    </th>
		            </tr>					
				</thead>
			</table>
		</div>
		<div class="content row col-md-12">
			<c:forEach var="tag" items="${ tags }">
				<span class="label label-default"
					style="background-color: #${ tag.color }"> <a
					href="/tag?name=${ tag.name }"> ${ tag.name }</a></span>
			</c:forEach>
		</div>
	</div>

	<script type="text/javascript"
		src="/resources/scripts/common/less.min.js"></script>
	<script type="text/javascript"
		src="/resources/scripts/common/jquery-2.1.3.min.js"></script>
	<script type="text/javascript"
		src="/resources/scripts/common/angular.min.js"></script>
	<script type="text/javascript"
		src="/resources/scripts/common/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="/resources/scripts/common/common.js"></script>
	<script type="text/javascript" src="/resources/scripts/main.js"></script>
</body>
</html>