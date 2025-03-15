<%@ page import="java.sql.Timestamp" %>
<%@ page import="dao.RecordDAO" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.Date" %>
<%@ page import="bean.Record" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
    <head>
        <title>BlackJack</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/index.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/BlackJack.css">
    </head>

    <body>
        <header>
            <h2>BlackJack</h2>
        </header>

        <main>
            <div class="container">
                <%
                    // 判断有没有登录，没有就跳转到登陆界面
                    if(request.getSession().getAttribute("user")==null){
                        response.sendRedirect(request.getContextPath()+"/login.jsp");
                    }
                    // 获取存储历史记录的信息
                    String username=(String) request.getSession().getAttribute("user");
                    Integer lp=null;
                    Integer money=null;
                    String fromPage=null; // 只有点击“保存并退出”才会在Cookie中加入fromPage项，用于判断是否需要写入数据库
                    Timestamp date=new Timestamp(new Date().getTime());
                    RecordDAO recordDAO=new RecordDAO();
                    Cookie[] cookies=request.getCookies();
                    if(cookies!=null){
                        for(Cookie cookie:cookies){
                            if("lp".equals(cookie.getName())) {
                                lp = Integer.valueOf(cookie.getValue());
                            }
                            if("money".equals(cookie.getName())) {
                                money = Integer.valueOf(cookie.getValue());
                            }
                            if("fromPage".equals(cookie.getName())) {
                                fromPage = cookie.getValue();
                            }
                        }
                    }
                    // 如果Cookie中有LP和Money的记录，就显示“继续”按钮
                    if(lp!=null && money!=null){
                %>
                        <form action="${pageContext.request.contextPath}/ContinueGameServlet" method="post" class="continueGame-form">
                            <button type="submit" id="continue" name="continue">继续</button>
                        </form>
                <%
                    }
                    // 存储历史记录到数据库
                    if(lp!=null && money!=null && fromPage!=null){
                        if(fromPage.equals("blackjack")) {
                            bean.Record record=new Record();
                            record.setUsername(username);
                            record.setLp(lp);
                            record.setMoney(money);
                            record.setDate(date);
                            recordDAO.addRecord(record);
                            // 删除fromPage项
                            if(cookies != null) {
                                for(Cookie cookie : cookies) {
                                    if("fromPage".equals(cookie.getName())) {
                                        cookie.setMaxAge(0);
                                        cookie.setPath("/");
                                        cookie.setValue("");
                                        response.addCookie(cookie);
                                    }
                                }
                            }
                        }
                    }
                    // 从数据库中读取历史记录，存到Cookie中
                    if(username!=null){
                        ResultSet rs=recordDAO.getRecords(username);
                        StringBuilder result=new StringBuilder();
                        if(rs!=null){
                            try {
                                while (rs.next()) {
                                    result.append(rs.getString("username"));
                                    result.append("#");
                                    result.append(rs.getInt("lp"));
                                    result.append("#");
                                    result.append(rs.getInt("money"));
                                    result.append("#");
                                    Timestamp time=rs.getTimestamp("time");
                                    String[] timeSplit=time.toString().split(" ");
                                    String dateString=timeSplit[0];
                                    String timeString=timeSplit[1];
                                    result.append(dateString);
                                    result.append("#");
                                    result.append(timeString);
                                    result.append("&");
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            Cookie cookie=new Cookie("result",result.toString());
                            cookie.setMaxAge(60*60*24);
                            response.addCookie(cookie);
                        }
                    }
                %>
                <form action="${pageContext.request.contextPath}/NewGameServlet" method="post" class="newGame-form">
                    <button type="submit" id="newGame" name="newGame">新游戏</button>
                </form>
                <div id="container-box">
                    <button id="historyRecord">历史记录</button>
                    <button id="rules">游戏规则</button>
                </div>
                <form action="${pageContext.request.contextPath}/Logout" method="post" class="logout-form">
                    <button type="submit" id="logout" name="logout">退出登录</button>
                </form>
            </div>
            <%--用于显示历史记录、游戏规则等--%>
            <div class="mask hide">
                <div class="prompt" id="prompt">

                </div>
            </div>

            <script type="text/javascript" src="js/cookie.js"></script>
            <script type="text/javascript" src="js/config.js"></script>
            <script type="text/javascript" src="js/util.js"></script>
            <script type="text/javascript" src="js/interface.js"></script>
            <script type="text/javascript" src="js/game.js"></script>
            <script type="text/javascript" src="js/index.js"></script>
        </main>
    </body>
</html>