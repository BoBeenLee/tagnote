<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Facebook Logout</title>
</head>
<body>
	<script type="text/javascript" src="/resources/scripts/common/facebook.js"></script>
	<script type="text/javascript">
	function proccessFacebookSuccess(response) {
		 FB.logout();
		 window.location = "/user/login";
	}
	function proccessFacebookError(response){
		 window.location = "/user/login";
	}
	</script></body>
</html>