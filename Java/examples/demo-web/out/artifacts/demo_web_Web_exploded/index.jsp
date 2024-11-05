<%@ page import="beans.TestBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>DEMO</title>
    </head>

    <body>
        <%
            String name=null;
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("testName")) {
                    System.out.println(cookie.getValue());
                    name=cookie.getValue();
                }
            }
            if(name==null){
                name="default";
            }
            Cookie testName = new Cookie("testName",name);
            response.addCookie(testName);
        %>
        <input type="text" name="inputTest" value=<%=name%>>
        <button type="button" name="button" onclick="setName()">保存</button><br>

        <script>
            function setName(){
                let input=document.getElementsByName("inputTest");
                if(input[0].value!==""){
                    sessionStorage.setItem("testName",input[0].value);
                }
            }
        </script>
    </body>
</html>