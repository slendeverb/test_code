// 定义游戏过程中相关
let Interface={
    // 定义游戏过程中的庄家和玩家信息
    roleInfo:{
        banker:{
            cardPoint:Config.actor.banker.cardPoint,
            cardNumber:0,
            lp:Config.actor.banker.initLP,
            chips:Config.actor.banker.initMoney
        },
        player:{
            cardPoint:Config.actor.player.cardPoint,
            cardNumber:0,
            lp:Config.actor.player.initLP,
            chips:Config.actor.player.initMoney
        }
    },

    // 在调用了Config.initBanker()后及时更新roleInfo
    initBanker: function(){
        this.roleInfo.banker.cardPoint = Config.actor.banker.cardPoint
        this.roleInfo.banker.cardNumber=0
        this.roleInfo.banker.lp=Config.actor.banker.initLP
        this.roleInfo.banker.chips=Config.actor.banker.initMoney
    },

    // 叫牌
    drawCard: function($role){
        let $li=Util.appendToElement('li',$role)
        let card=Util.randomCard()
        let val=Util.getCardPoint(card)

        $li.className=card
        $li.setAttribute('data-val',val)
        $li.setAttribute('data-str',card)
    },

    // 提示信息，flag为true表示隐藏
    promptMessage:function ($mask,$prompt,flag,message){
        if(flag){
            $mask.className='mask hide'
            return
        }
        $prompt.innerHTML=message
        $mask.className='mask block'
    },

    // 停牌
    stopCard:function(currentRole,$role,$count){
        // 庄家直到抽取到17的停牌点
        while(Util.calculatePoints($role)<Config.actor.banker.stopCardAt){
            Interface.drawCard($role)
            Util.updateCardPoint(currentRole,$role,$count)
        }
    }
}