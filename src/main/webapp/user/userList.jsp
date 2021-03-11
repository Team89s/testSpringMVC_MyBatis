<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询用户列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
</head>
<body>
	<input type="button" value="添加" onclick="window.location.href='${pageContext.request.contextPath}/user/userRegist.jsp'" /> 
	<br/>用户列表：
	<table width="100%" border=1>
		<tr align="center">
			<td>用户名称</td>
			<td>用户性别</td>
			<td>用户生日</td>
			<td>用户地址</td>
			<td colspan="2">操作</td>
		</tr>
		<c:forEach items="${users}" var="user">
			<tr align="center">
				<td>${user.username}</td>
				<td>
					<c:if test="${user.sex eq 1}">
						男
					</c:if>
					<c:if test="${user.sex eq 2}">
						女
					</c:if>
				</td>
				<td><fmt:formatDate value="${user.birthday}" pattern="yyyy-MM-dd" /></td>
				<td>${user.address}</td>
				<td>
					<form action="${pageContext.request.contextPath}/user/${user.id}" method="get">
						<input type="submit" value="修改">
					</form>
				</td>
				<td>
					<form action="${pageContext.request.contextPath}/user/${user.id}" method="post">
						<%-- 通过_method参数，将post方式转成DELETE --%>
						<input type="hidden" name="_method" value="DELETE"/>
						<input type="submit" value="删除">
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>

</html>