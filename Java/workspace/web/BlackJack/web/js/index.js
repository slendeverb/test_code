// 用于定义主界面相关
let Index=function (){
    let $historyRecord=Util.getElement('#historyRecord') // 历史记录
    let $rules=Util.getElement('#rules') // 游戏规则
    let $mask=Util.getElement('.mask') // 用于显示信息，外面的黑色遮罩
    let $prompt=Util.getElement('.prompt') // 里面的白色半透明

    // 初始化
    let init=function (){
        eventBind()
    }

    // 向元素上绑定事件监听器
    let eventBind=function (){
        $historyRecord.addEventListener('click',function (e){
            let message="记录：<br>用户名&nbsp&nbsp&nbsp&nbsp生命值&nbsp&nbsp&nbsp&nbsp筹码&nbsp&nbsp&nbsp&nbsp时间"
            // 从Cookie中得到从数据库查询的历史记录
            let result=getCookie("result")
            if(result){
                // 使用jsp页面定义的分隔符分割记录
                let results=result.split('&')
                for(let i=0;i<results.length;i++){
                    if(results[i]==''){
                        continue
                    }
                    let attributes=results[i].split('#');
                    let username=attributes[0]
                    let lp=attributes[1]
                    let money=attributes[2]
                    let date=attributes[3]
                    let time=attributes[4]
                    message+="<br>"+username+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+lp+"&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp"+money+"&nbsp&nbsp&nbsp&nbsp"+date+" "+time
                }
            }
            // 显示历史记录
            Interface.promptMessage($mask,$prompt,false,message)
        },false)

        $rules.addEventListener('click',function (e){
            // 显示历史记录
            Interface.promptMessage($mask,$prompt,false,Config.rules)
        },false)

        $mask.addEventListener('click',function(e){
            if(e.target.id!='prompt'){ //点击外面的黑色遮罩
                // 隐藏遮罩层
                Interface.promptMessage($mask,$prompt,true)
            }
        },false)
    }

    return {
        init:init
    }
}()

Index.init()