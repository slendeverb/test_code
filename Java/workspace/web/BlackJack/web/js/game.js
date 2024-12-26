let Game=function (){

    let $selector=Util.getElement('#selector')
    let $banker=Util.getElement('.banker')
    let $player=Util.getElement('.player')
    let $countBanker=Util.getElement('.countBanker')
    let $countPlayer=Util.getElement('.countPlayer')
    let $playerChips=Util.getElement('#playerChips')
    let $playerLP=Util.getElement('#playerLP')
    let $bankerChips=Util.getElement('#bankerChips')
    let $bankerLP=Util.getElement('#bankerLP')
    let $mask=Util.getElement('.mask')
    let $prompt=Util.getElement('.prompt')

    let currentRole='banker'

    let init=function (){
        $bankerLP.innerHTML=Interface.roleInfo.banker.lp
        $bankerChips.innerHTML=Interface.roleInfo.banker.chips
        $playerLP.innerHTML=Interface.roleInfo.player.lp
        $playerChips.innerHTML=Interface.roleInfo.player.chips
        eventBind()
    }

    let eventBind=function (){
        $selector.addEventListener('click',function(e){
            if(e.target.id=='start'){
                currentRole='banker'
                Util.clearItems($countBanker,$countPlayer)
                Config.initBanker()
                Interface.initBanker()
                $bankerLP.innerHTML=Interface.roleInfo.banker.lp
                $bankerChips.innerHTML=Interface.roleInfo.banker.chips
                Util.buttonStateChange($selector)
                Interface.drawCard($banker)
                Util.updateCardPoint(currentRole,$banker,$countBanker)
                currentRole='player'
            }else if(e.target.id=='rules'){
                Interface.promptMessage($mask,$prompt,false,Config.rules)
            }else if(e.target.id=='drawCard'){
                let points=Util.calculatePoints($player)
                if (points<=21){
                    Interface.drawCard($player)
                    Util.updateCardPoint(currentRole,$player,$countPlayer)
                }
                currentRole='banker'
                if(points>21){
                    Interface.stopCard('banker',$banker,$countBanker)
                    Util.causeDamage($banker,$player,$bankerLP,$playerLP)
                    Util.settle($selector,$playerChips,$countBanker,$countPlayer)
                    return
                }

                if(Util.calculatePoints($banker)<Config.actor.banker.stopCardAt){
                    Interface.drawCard($banker)
                    Util.updateCardPoint(currentRole,$banker,$countBanker)
                }
                currentRole='player'
            }else if(e.target.id=='stopCard'){
                Interface.stopCard('banker',$banker,$countBanker)
                Util.causeDamage($banker,$player,$bankerLP,$playerLP)
                Util.settle($selector,$playerChips,$countBanker,$countPlayer)
            }else if(e.target.id=='exit'){
                setCookie("lp",Interface.roleInfo.player.lp,1)
                setCookie("money",Interface.roleInfo.player.chips,1)
                setCookie("fromPage","blackjack")
                location.href="/BlackJack/index.jsp"
            }
        },false)

        $mask.addEventListener('click',function(e){
            if(e.target.id!='prompt'){
                let state=$selector.dataset.show.indexOf('rules')
                if(state!=-1){
                    Interface.promptMessage($mask,$prompt,true)
                    return
                }else{
                    Interface.promptMessage($mask,$prompt,true)
                    Util.clearItems($countBanker,$countPlayer)
                    Util.updateCardPoint('banker',$banker,$countBanker)
                    Util.updateCardPoint('player',$player,$countPlayer)
                    Util.buttonStateChange($selector)
                }
            }
        },false)
    }

    return {
        init:init
    }
}()

Game.init()