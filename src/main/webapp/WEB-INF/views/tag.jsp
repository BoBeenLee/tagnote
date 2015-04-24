<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html ng-app="tag">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Tag</title>

    <link rel="stylesheet" type="text/css" href="/resources/styles/common/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/common/bootstrap.min.css"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/common/common.less"/>
    <link rel="stylesheet/less" type="text/css" href="/resources/styles/tag.less"/>
</head>
<body>
<div class="tag container">
    <div class="main panel panel-default">
        <div class="panel-heading">#<span>Tag</span></div>
        <div class="panel-body">
            <accordion close-others="true">
                <c:forEach var="article" items="${ articles.content }">
                <accordion-group>
                    <accordion-heading>
                        <span>   ${ article.subject }</span>
                    </accordion-heading>
                    <div class="tag-label row">
                        <div class="col-md-9">
                        	<c:forEach var="tagArticle" items="${ article.tagArticles }">
                           	 	<span class="label label-default" style="background-color: #${ tagArticle.tag.color }">${ tagArticle.tag.name }</span>
                            </c:forEach>
                        </div>
                        <div class="col-md-3 text-right">
                            <a href="#remove"><span class="label label-danger">Remove</span></a>
                            <a href="#send"><span class="label label-primary">Send</span></a>
                            <a href="#modify"><span class="label label-success">Modify</span></a>
                        </div>
                    </div>
                    <p class="row col-md-12">
                        ${ article.content }
                    </p>
                </accordion-group>
                </c:forEach>
                <!-- 
                <accordion-group>
                    <accordion-heading>
                        <span>test1</span>
                    </accordion-heading>
                    <div class="tag-label row">
                        <div class="col-md-9">
                            <span class="label label-default">Label</span>
                            <span class="label label-default">Label</span>
                        </div>
                        <div class="col-md-3 text-right">
                            <a href="#remove"><span class="label label-danger">Remove</span></a>
                            <a href="#send"><span class="label label-primary">Send</span></a>
                            <a href="#modify"><span class="label label-success">Modify</span></a>
                        </div>
                    </div>
                    <p class="row col-md-12">
                        testtesttest
                    </p>
                </accordion-group>
                 -->
            </accordion>
        </div>
    </div>
</div>

<script type="text/javascript" src="/resources/scripts/common/less.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/jquery-2.1.3.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/angular.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/ui-bootstrap-tpls-0.12.1.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/scripts/common/common.js"></script>
<script type="text/javascript" src="/resources/scripts/tag.js"></script>
</body>
</html>