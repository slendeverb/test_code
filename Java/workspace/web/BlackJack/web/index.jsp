<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <title>Black Jack</title>
        <link rel="stylesheet" type="text/css" href="css/reset.css">
        <link rel="stylesheet" type="text/css" href="css/index.css">
        <link rel="stylesheet" type="text/css" href="css/img.css">
    </head>
    <body>
        <header>
            <h2>BlackJack</h2>
        </header>

        <main>
            <!--庄家 -->
            <div id="playerA">
                <div class="wrap">
                    <ul class="banker">

                    </ul>
                </div>
                <span class="count countB">0</span>
            </div>
            <!-- 牌桌 -->
            <div id="roundtable">
                <div class="wrap">
                    <span class="role">庄家      余额：<span id="bankerBalance">50</span>$</span>
                    <div id="selector" data-show="begin rules" data-hide="addCard wager stopCard">
                        <button id="addCard" class="hide">要牌</button>
                        <button id="begin">开始</button>
                        <button id="rules">游戏规则</button>
                        <span id="wager" class="hide">赌注 10$</span>
                        <button id="stopCard" class="hide">停牌</button>
                    </div>
                    <span class="role">玩家      余额：<span id="balance">100</span>$</span>
                </div>
            </div>
            <!--玩家 -->
            <div id="playerB">
                <div class="wrap">
                    <ul class="player">

                    </ul>
                </div>
                <span class="count countP">0</span>
            </div>
        </main>
        <!--遮罩层-->
        <div class="mask hide">
            <div class="promot" id="promot">

            </div>
        </div>

        <script type="text/javascript" src="js/config.js"></script>
        <script type="text/javascript" src="js/util.js"></script>
        <script type="text/javascript" src="js/interface.js"></script>
        <script type="text/javascript" src="js/game.js"></script>
    </body>
</html>
