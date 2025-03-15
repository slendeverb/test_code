// 用于定义通用组件
let Util={
    // 获取页面元素
    getElement:function (selector){
        return document.querySelector(selector)
    },

    // 将显示列表和隐藏列表反转
    buttonStateChange:function($parent){
        let arrShow,arrHide,$item
        let hide = $parent.dataset.hide
        let show= $parent.dataset.show

        arrShow=show.split(' ')
        arrShow.forEach(function (item,index){
            $item=this.getElement('#'+item)
            $item.className='hide'
        },this)

        arrHide=hide.split(' ')
        arrHide.forEach(function (item,index){
            $item=this.getElement('#'+item)
            $item.className='block'
        },this)

        $parent.setAttribute('data-hide',show)
        $parent.setAttribute('data-show',hide)
    },

    // 生成名称为str的html元素并向element中添加
    appendToElement:function(str,element){
        let childElement=document.createElement(str)
        element.appendChild(childElement)
        return childElement
    },

    // 从卡组中随机抽取一张，并从卡组移除
    randomCard:function(){
        let poker=Config.poker
        let len=poker.length
        let idx=Math.floor(Math.random()*len)
        let card=poker[idx]
        poker.splice(idx,1)
        return card
    },

    // 获取当前扑克的点数，A默认为11
    getCardPoint: function(card){
        let val=card.slice(1)
        if(val==='1'){
            return 11
        }else if(/[jqk]/.test(val)){
            return 10
        }else {
            return val
        }
    },

    // 计算扑克总点数
    calculatePoints: function($ul){
        let count=0
        let cardA=[]
        Array.prototype.forEach.call($ul.querySelectorAll('li'),function(item,index){
            count+=parseInt(item.dataset.val)
            if(item.dataset.val=='11'){
                cardA.push(item)
            }
            // 如果点数大于21就将A的点数变为1
            if(count>21 && cardA.length>0){
                cardA[0].dataset.val=1
                count-=10
            }
        })
        return count
    },

    // 清除ul中的扑克
    clearItems:function($countBanker,$countPlayer){
        let $lis
        Array.prototype.forEach.call(document.querySelectorAll('ul'),function($ul,index){
            $lis=$ul.querySelectorAll('li')
            for(let i=0;i<$lis.length;i++){
                Config.poker.push($lis[i].dataset.str)
                $ul.removeChild($lis[i])
            }
        })

        Interface.roleInfo.banker.cardPoint=0
        Interface.roleInfo.banker.cardNumber=0
        Interface.roleInfo.player.cardPoint=0
        Interface.roleInfo.player.cardNumber=0
    },

    // 更新页面上的扑克总点数
    updateCardPoint:function(currentRole, $role, $count){
        Interface.roleInfo[currentRole].cardPoint=Util.calculatePoints($role)
        if(Interface.roleInfo[currentRole].cardPoint!=0){
            Interface.roleInfo[currentRole].cardNumber+=1
        }
        $count.innerHTML=Interface.roleInfo[currentRole].cardPoint
    },

    // 造成生命值伤害
    causeDamage:function($banker,$player,$bankerLP,$playerLP){
        let bankerPoints=Util.calculatePoints($banker)
        let playerPoints=Util.calculatePoints($player)
        // 如果总点数大于21，就是“爆牌”，相当于0
        if(bankerPoints>21){
            bankerPoints=0
        }
        if(playerPoints>21){
            playerPoints=0
        }
        // 生命值扣除总点数之差
        if(bankerPoints<=playerPoints){
            Interface.roleInfo.banker.lp-=playerPoints-bankerPoints
            if(Interface.roleInfo.banker.lp<0){
                Interface.roleInfo.banker.lp=0
            }
            $bankerLP.innerHTML=Interface.roleInfo.banker.lp
        }else{
            Interface.roleInfo.player.lp-=bankerPoints-playerPoints
            if(Interface.roleInfo.player.lp<0){
                Interface.roleInfo.player.lp=0
            }
            $playerLP.innerHTML=Interface.roleInfo.player.lp
        }
    },

    // 判断游戏是否结束
    settle:function($selector,$playerChips,$countBanker,$countPlayer){
        if(Interface.roleInfo.banker.lp==0){ // 庄家生命值为0，玩家胜利
            Interface.roleInfo.player.chips+=Interface.roleInfo.banker.chips
            $playerChips.innerHTML=Interface.roleInfo.player.chips
            window.alert("恭喜，你获胜了！")

            this.clearItems($countBanker,$countPlayer)
            this.buttonStateChange($selector,$playerChips,$countPlayer)
        }else if(Interface.roleInfo.player.lp==0){ // 玩家生命值为0，游戏结束
            window.alert("生命值降为0")
            eraseCookie("lp")
            eraseCookie("money")
            location.href="/BlackJack/index.jsp"
        }else{ // 游戏未结束
            Util.clearItems($countBanker,$countPlayer)
        }
    }
}