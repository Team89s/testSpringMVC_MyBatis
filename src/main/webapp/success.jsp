<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>登录成功页面</title>
</head>
<body>
    <%--  当前资源必须登录后访问 --%>
    <a href="hello">访问Hello World</a>
    ${hello}
    <% System.out.println("success.jsp 执行了"); %>
</body>
</html>
