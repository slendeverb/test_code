let Interface={
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

    initBanker: function(){
        this.roleInfo.banker.cardPoint = Config.actor.banker.cardPoint
        this.roleInfo.banker.cardNumber=0
        this.roleInfo.banker.lp=Config.actor.banker.initLP
        this.roleInfo.banker.chips=Config.actor.banker.initMoney
    },

    drawCard: function($role){
        let $li=Util.appendToElement('li',$role)
        let card=Util.randomCard()
        let val=Util.getCardPoint(card)

        $li.className=card
        $li.setAttribute('data-val',val)
        $li.setAttribute('data-str',card)
    },

    promptMessage:function ($mask,$prompt,flag,message){
        if(flag){
            $mask.className='mask hide'
            return
        }
        $prompt.innerHTML=message
        $mask.className='mask block'
    },

    stopCard:function(currentRole,$role,$count){
        while(Util.calculatePoints($role)<Config.actor.banker.stopCardAt){
            Interface.drawCard($role)
            Util.updateCardPoint(currentRole,$role,$count)
        }
    }
}