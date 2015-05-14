<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html ng-app="article">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Article</title>

    <link rel="stylesheet" type="text/css" href="/resources/styles/common/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/common/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/common/ng-tags-input.min.css"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/common/common.less"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/article.less"/>
</head>
<body>
<div ng-controller="articleController" class="article container" ng-init='init(${ article.jsonTags })'>
    <div class="main panel panel-default">
        <div class="panel-heading">
        	<span><img src="/resources/img/common-editor-pencil.png" /></span>
        	<div class="pull-right"><a href="/tag/list"><span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span></a></div>
        </div>
        <div class="panel-body">
            <form action="/article/write/submit" method="get">
                <!--<pre>Model: {{tags}}</pre>-->
                <div class="form-group">
                    <label>Title</label>
                    <input type="text" name="subject" class="form-control" value="${ article.subject }" />
                </div>
                <div class="form-group">
                    <label>Tag</label>
                    <tags-input ng-model="tags">
                        <auto-complete source="getTag($query)"></auto-complete>
                    </tags-input>
                    <input type="hidden" name="tags" value="{{getTags(tags)}}"/>
                </div>
                <div class="form-group">
                    <label>Content</label>
                    <textarea name="content" class="form-control" rows="5">${ article.content }</textarea>
                </div>
                <div class="form-group pull-right">
                	<c:if test="${ article.artId != 0 }">
	               		<input type="hidden" name="artId" value="${ article.artId }" />
	                </c:if>
	                <c:if test="${ name != null }">
	                	<input type="hidden" name="name" value='<c:out value="${ name }"></c:out>' />
	                </c:if>
                    <input type="submit" class="btn btn-default" value="Write"/>
                    <input type="button" class="btn btn-default" onclick="history.go(-1);" value="Cancel"/>
                </div>
            </form>
        </div>
    </div>
</div>

<script type="text/javascript" src="/resources/scripts/common/less.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/angular.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/ui-bootstrap-tpls-0.12.1.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/ng-tags-input.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/common.js"></script>
<script type="text/javascript" src="/resources/scripts/article.js"></script>
</body>
</html>