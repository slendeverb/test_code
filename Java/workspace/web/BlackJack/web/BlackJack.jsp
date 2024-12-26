<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>BlackJack</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/BlackJack.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/img.css">
    </head>
    <body>
        <header>
            <h2>BlackJack</h2>
        </header>

        <main>
            <div id="playerA">
                <div class="wrapper">
                    <ul class="banker">

                    </ul>
                </div>
                <span class="count countBanker">0</span>
            </div>

            <div id="table">
                <div class="wrapper">
                    <span class="role">庄家      生命值：<span id="bankerLP">50</span>LP      筹码：<span id="bankerChips">50</span>$</span>
                    <div id="selector" data-show="start rules exit" data-hide="drawCard stopCardAt stopCard">
                        <button id="drawCard" class="hide">叫牌</button>
                        <button id="start">开始</button>
                        <button id="rules">游戏规则</button>
                        <span id="stopCardAt" class="hide">停牌于17</span>
                        <button id="stopCard" class="hide">停牌</button>
                        <button id="exit" name="exit">保存并退出</button>
                    </div>
                    <span class="role">玩家      生命值：<span id="playerLP">100</span>LP      筹码：<span id="playerChips">100</span>$</span>
                </div>
            </div>

            <div id="playerB">
                <div class="wrapper">
                    <ul class="player">

                    </ul>
                </div>
                <span class="count countPlayer">0</span>
            </div>
        </main>

        <div class="mask hide">
            <div class="prompt" id="prompt">

            </div>
        </div>

        <script type="text/javascript" src="js/cookie.js"></script>
        <script type="text/javascript" src="js/config.js"></script>
        <script type="text/javascript" src="js/util.js"></script>
        <script type="text/javascript" src="js/interface.js"></script>
        <script type="text/javascript" src="js/game.js"></script>
    </body>
</html>
