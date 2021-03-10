<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询商品列表</title>
    <script src="../js/jquery-1.11.0.min.js"></script>
    <script>
        function updateAll() {
            $("#f").attr("action","${pageContext.request.contextPath }/items/updateAll.action");
            $("#f").submit();
        }
    </script>
</head>
<body>
<form id="f" action="${pageContext.request.contextPath }/items/findItemsList.action" method="post">
    查询条件：
    <table width="100%" border=1>
        <tr>
            <td>
                <input type="text" placehodler="请输入" name="itemsCustom.name"/>
                <input type="submit" value="查询"/>
                <input type="button" value="批量修改" onclick="updateAll()"/>
            </td>
        </tr>
    </table>
    商品列表：
    <table width="100%" border=1>
        <tr>
            <td><input type="checkbox" /></td>
            <td>商品名称</td>
            <td>商品价格</td>
            <td>生产日期</td>
            <td>商品描述</td>
        </tr>
        <c:forEach items="${itemsList}" var="item" varStatus="sta">
            <tr>
                <td>
                    <input type="checkbox" name="itemsCustomList[${sta.index}].id" value="${item.id}" />
                </td>
                <td>
                    <input type="text" name="itemsCustomList[${sta.index}].name" value="${item.name }"/>
                </td>
                <td>
                    <input type="text" name="itemsCustomList[${sta.index}].price" value="${item.price }"/>
                </td>
                <td>
                    <input type="text" name="itemsCustomList[${sta.index}].createtime"
                           value="<fmt:formatDate value='${item.createtime}' pattern='yyyy-MM-dd HH:mm:ss'/> "/>
                </td>
                <td>
                    <input type="text" name="itemsCustomList[${sta.index}].detail" value="${item.detail }"/>
                </td>
            </tr>
        </c:forEach>

    </table>
</form>
</body>

</html>