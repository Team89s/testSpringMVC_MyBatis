<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
<title>修改用户信息</title>
</head>
<body>
	<form id="userForm" action="${pageContext.request.contextPath}/user" method="post">
		<!-- 设置隐藏域，指定_method为PUT方式 -->
		<input type="hidden" name="_method" value="PUT"/>
		<input type="hidden" name="id" value="${user.id}"/> 
		修改用户信息：
		<table width="100%" border=1>
			<tr>
				<td>用户姓名</td>
				<td><input type="text" name="username" value="${user.username}" /></td>
			</tr>
			<tr>
				<td>用户性别</td>
				<td>
					<input type="radio" name="sex" value="1" 
						<c:if test="${user.sex eq 1}">checked</c:if> class="boy"/>男
					<input type="radio" name="sex" value="2" 
						<c:if test="${user.sex eq 2}">checked</c:if> class="girl"/>女
				</td>
			</tr>
			<tr>
				<td>用户生日</td>
				<td><input type="date" name="birthday"
					value="<fmt:formatDate value='${user.birthday}' pattern='yyyy-MM-dd'/>" />
				</td>
			</tr>
			<tr>
				<td>用户地址</td>
				<td>
					<textarea rows="3" cols="30" name="address">${user.address}</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="提交" />
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
function showPreview(source){
	var file = source.files[0];
	if(window.FileReader){
		var fr = new FileReader();
		fr.onloadend = function(e){
			$(".pic").css({"width":"64px","height":"auto"});
			$(".pic").attr("src",e.target.result);
		}
		fr.readAsDataURL(file);
	}
}
</script>
</html>