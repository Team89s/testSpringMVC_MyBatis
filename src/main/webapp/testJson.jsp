<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>测试json数据</title>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
    <script>
        //测试：请求是json，响应json
        function fun1() {
            $.ajax({
                type:"post",
                url:"/testJson.action",
                contentType:"application/json;charset=utf-8",
                data:'{"name":"小黄鸭","price":"19.9"}',  /* json数据格式 */
                success:function(content){
                    //响应回来的json数据对象.key，从而获取value
                    console.log(content.name+" : "+content.price);
                }
            });
        }

        //测试：请求是key/value，响应json
        function fun2() {
            $.ajax({
                type:"post",
                url:"/testKey.action",
                data:"name=小黄鸡&price=11.11",
                /*dataType:"json",*/
                success:function (content) {
                    console.log(content.name+"\n"+content.price);
                }
            });
        }

        //解析集合数据
        $.ajax({
            type:"get",
            url:"/testList.action",
            success:function (data) {
                //data 集合
                for (var i = 0; i < data.length; i++) {
                    // data[i] 商品信息
                    console.log(data[i].name+ ":" +data[i].price);
                }
            }
        });

        //测试：请求是集合json，响应集合json
        function fun3() {
            $.ajax({
                type:"post",
                url:"/testJsonList.action",
                contentType:"application/json;charset=utf-8",
                data:'[{"name":"小黄鸭","price":"19.9"},{"name":"小黄鸡","price":"29.9"}]',  /* json数据格式 */
                success:function(content){
                    //content 集合
                    for (var i = 0; i < content.length; i++) {
                        // content[i] 商品信息
                        console.log(content[i].name+" : "+content[i].price);
                    }
                }
            });
        }

    </script>
</head>
<body>
    <input type="button" value="请求是json，响应json" onclick="fun1()" />
    <input type="button" value="请求是key/value，响应json" onclick="fun2()" />
    <input type="button" value="请求是集合json，响应集合json" onclick="fun3()" />
</body>
</html>
