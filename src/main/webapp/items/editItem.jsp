<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>修改商品信息</title>
</head>
<body>

<%-- 展示校验错误信息 --%>
<c:forEach items="${allErrors}" var="error">
    <font color="red">${error.defaultMessage}</font><br/>
</c:forEach>

<%-- enctype="multipart/form-data" --%>
<form id="itemForm" action="${pageContext.request.contextPath}/items/updateItems.action"
      method="post" enctype="multipart/form-data" >
    <input type="hidden" name="id" value="${items.id}"/>
    修改商品信息：
    <table width="100%" border=1>
        <tr>
            <td>商品名称</td>
            <td><input type="text" name="name" value="${items.name}"/></td>
        </tr>
        <tr>
            <td>商品价格</td>
            <td><input type="text" name="price" value="${items.price}"/></td>
        </tr>
        <tr>
            <td>商品生产日期</td>
            <td><input type="text" name="createtime"
                       value="<fmt:formatDate value="${items.createtime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
        </tr>
        <tr>
            <td>商品图片</td>
            <td>
                <img src="${item.pic}" width=100 height=100 id="itemPic"/>
                <br/>
                <input type="file" name="pictureFile" onchange="showPreview(this)"/>
            </td>
        </tr>
        <tr>
            <td>商品简介</td>
            <td>
                <textarea rows="3" cols="30" name="detail">${items.detail }</textarea>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="提交"/>
            </td>
        </tr>
    </table>

</form>
</body>
<%-- 实时显示上传的图片信息 --%>
<script type="text/javascript">
    function showPreview(source){
        var file = source.files[0];
        if(window.FileReader){
            var fr = new FileReader();
            fr.onloadend = function(e){
                document.getElementById("itemPic").src=e.target.result;
            }
            fr.readAsDataURL(file);
        }
    }
</script>
</html>