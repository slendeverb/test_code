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
            <div>
                <%
                    if(request.getSession().getAttribute("user")==null){
                        response.sendRedirect(request.getContextPath()+"/login.jsp");
                    }
                    String username=(String) request.getSession().getAttribute("user");
                    Integer lp=null;
                    Integer money=null;
                    String fromPage=null;
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
                    if(lp!=null && money!=null){
                %>
                        <form action="${pageContext.request.contextPath}/ContinueGameServlet" method="post">
                            <button type="submit" id="continue" name="continue">继续</button>
                        </form>
                <%
                    }
                    if(lp!=null && money!=null && fromPage!=null){
                        if(fromPage.equals("blackjack")) {
                            bean.Record record=new Record();
                            record.setUsername(username);
                            record.setLp(lp);
                            record.setMoney(money);
                            record.setDate(date);
                            recordDAO.addRecord(record);

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
                <form action="${pageContext.request.contextPath}/NewGameServlet" method="post">
                    <button type="submit" id="newGame" name="newGame">新游戏</button>
                </form>
                <button id="historyRecord">历史记录</button><br>
                <button id="rules">游戏规则</button>
            </div>

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