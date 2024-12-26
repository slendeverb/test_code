let Util={
    getElement:function (selector){
        return document.querySelector(selector)
    },

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

    appendToElement:function(str,element){
        let childElement=document.createElement(str)
        element.appendChild(childElement)
        return childElement
    },

    randomCard:function(){
        let poker=Config.poker
        let len=poker.length
        let idx=Math.floor(Math.random()*len)
        let card=poker[idx]
        poker.splice(idx,1)
        return card
    },

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

    calculatePoints: function($ul){
        let count=0
        let cardA=[]
        Array.prototype.forEach.call($ul.querySelectorAll('li'),function(item,index){
            count+=parseInt(item.dataset.val)
            if(item.dataset.val=='11'){
                cardA.push(item)
            }
            if(count>21 && cardA.length>0){
                cardA[0].dataset.val=1
                count-=10
            }
        })
        return count
    },

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

    updateCardPoint:function(currentRole, $role, $count){
        Interface.roleInfo[currentRole].cardPoint=Util.calculatePoints($role)
        if(Interface.roleInfo[currentRole].cardPoint!=0){
            Interface.roleInfo[currentRole].cardNumber+=1
        }
        $count.innerHTML=Interface.roleInfo[currentRole].cardPoint
    },

    causeDamage:function($banker,$player,$bankerLP,$playerLP){
        let bankerPoints=Util.calculatePoints($banker)
        let playerPoints=Util.calculatePoints($player)
        if(bankerPoints>21){
            bankerPoints=0
        }
        if(playerPoints>21){
            playerPoints=0
        }
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

    settle:function($selector,$playerChips,$countBanker,$countPlayer){
        if(Interface.roleInfo.banker.lp==0){
            Interface.roleInfo.player.chips+=Interface.roleInfo.banker.chips
            $playerChips.innerHTML=Interface.roleInfo.player.chips
            window.alert("恭喜，你获胜了！")

            this.clearItems($countBanker,$countPlayer)
            this.buttonStateChange($selector,$playerChips,$countPlayer)
        }else if(Interface.roleInfo.player.lp==0){
            window.alert("生命值降为0")
            eraseCookie("lp")
            eraseCookie("money")
            location.href="/BlackJack/index.jsp"
        }else{
            Util.clearItems($countBanker,$countPlayer)
        }
    }
}