// 定义游戏相关
let Game=function (){

    let $selector=Util.getElement('#selector') // 牌桌上的按钮
    let $banker=Util.getElement('.banker') // 庄家的ul用于存放扑克
    let $player=Util.getElement('.player') // 玩家的ul用于存放扑克
    let $countBanker=Util.getElement('.countBanker') // 庄家的扑克总点数
    let $countPlayer=Util.getElement('.countPlayer') // 玩家的扑克总点数
    let $playerChips=Util.getElement('#playerChips') // 玩家的筹码
    let $playerLP=Util.getElement('#playerLP') // 玩家的生命值
    let $bankerChips=Util.getElement('#bankerChips') // 庄家的筹码
    let $bankerLP=Util.getElement('#bankerLP') // 庄家的生命值
    let $mask=Util.getElement('.mask') // 用于显示信息，外面的黑色遮罩
    let $prompt=Util.getElement('.prompt') // 里面的白色半透明

    let currentRole='banker' // 现在轮到谁行动

    // 初始化
    let init=function (){
        $bankerLP.innerHTML=Interface.roleInfo.banker.lp
        $bankerChips.innerHTML=Interface.roleInfo.banker.chips
        $playerLP.innerHTML=Interface.roleInfo.player.lp
        $playerChips.innerHTML=Interface.roleInfo.player.chips
        eventBind()
    }

    // 向元素上绑定事件监听器
    let eventBind=function (){
        $selector.addEventListener('click',function(e){
            if(e.target.id=='start'){ // 点击“开始”按钮
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
            }else if(e.target.id=='rules'){ // 点击“游戏规则按钮”
                Interface.promptMessage($mask,$prompt,false,Config.rules)
            }else if(e.target.id=='drawCard'){ // 点击“叫牌”按钮
                // 玩家叫牌
                let points=Util.calculatePoints($player)
                if (points<=21){
                    Interface.drawCard($player)
                    Util.updateCardPoint(currentRole,$player,$countPlayer)
                }
                currentRole='banker'
                // 点数大于21就自动停牌
                if(points>21){
                    Interface.stopCard('banker',$banker,$countBanker)
                    Util.causeDamage($banker,$player,$bankerLP,$playerLP)
                    Util.settle($selector,$playerChips,$countBanker,$countPlayer)
                    return
                }
                // 庄家叫牌
                if(Util.calculatePoints($banker)<Config.actor.banker.stopCardAt){
                    Interface.drawCard($banker)
                    Util.updateCardPoint(currentRole,$banker,$countBanker)
                }
                currentRole='player'
            }else if(e.target.id=='stopCard'){ // 点击停牌按钮
                Interface.stopCard('banker',$banker,$countBanker)
                Util.causeDamage($banker,$player,$bankerLP,$playerLP)
                Util.settle($selector,$playerChips,$countBanker,$countPlayer)
            }else if(e.target.id=='exit'){ // 点击“保存并退出”按钮
                // 保存LP，Money，fromPage到Cookie
                setCookie("lp",Interface.roleInfo.player.lp,1)
                setCookie("money",Interface.roleInfo.player.chips,1)
                setCookie("fromPage","blackjack")
                location.href="/BlackJack/index.jsp"
            }
        },false)

        $mask.addEventListener('click',function(e){
            if(e.target.id!='prompt'){ //点击外面的黑色遮罩
                // 如果在显示列表的按钮中有“游戏规则”，就隐藏遮罩层
                let state=$selector.dataset.show.indexOf('rules')
                if(state!=-1){
                    Interface.promptMessage($mask,$prompt,true)
                    return
                }else{ // 没有“游戏规则”表示是正在进行游戏时，一般不可能
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