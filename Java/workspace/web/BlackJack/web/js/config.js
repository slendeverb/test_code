// 定义基本信息
let Config={
    // 定义扑克牌的名称
    poker:['h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'h7', 'h8', 'h9', 'h10', 'hj', 'hq', 'hk',
        's1', 's2', 's3', 's4', 's5', 's6', 's7', 's8', 's9', 's10', 'sj', 'sq', 'sk',
        'c1', 'c2', 'c3', 'c4', 'c5', 'c6', 'c7', 'c8', 'c9', 'c10', 'cj', 'cq', 'ck',
        'd1', 'd2', 'd3', 'd4', 'd5', 'd6', 'd7', 'd8', 'd9', 'd10', 'dj', 'dq', 'dk'],

    // 定义游戏规则
    rules:'BlackJack游戏：' +
        '<br>角色分为玩家和庄家' +
        '<br>角色初始筹码和生命值都是100' +
        '<br>庄家初始筹码为50-100的随机数，生命值为20-30的随机数' +
        '<br>点击开始按钮后，庄家先开始叫牌' +
        '<br>A的分数为1或11' +
        '<br>J Q K的分数为10' +
        '<br>其余牌的分数分别为2-10' +
        '<br>21及以内的分数有效' +
        '<br>超过21为爆牌，分数降为0' +
        '<br>当双方都停牌之后（爆牌也算停牌），' +
        '<br>计算双方的点数，点数低的一方生命值扣除相差的部分' +
        '<br>当对方生命值为0时己方胜利，获得对方所有的筹码',

    // 定义庄家和玩家的基本数据
    actor:{
        banker:{
            initMoney:50,
            initLP:20,
            cardPoint:0,
            stopCardAt:17, // 表示庄家在总点数到达后就会停牌
        },
        player:{
            // 如果Cookie中有LP和Money的历史记录，就读取，否则初始化
            initMoney:(getCookie("money")==null || getCookie("money")=="")?100:parseInt(getCookie("money")),
            initLP:(getCookie("lp")==null || getCookie("lp")=="")?100:parseInt(getCookie("lp")),
            cardPoint:0
        }
    },

    // 随机庄家的生命值和筹码
    initBanker:function(){
        this.actor.banker.initMoney=Math.round(Math.random()*50)+50
        this.actor.banker.initLP=Math.round(Math.random()*10)+20
    }
}