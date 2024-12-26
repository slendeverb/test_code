let Index=function (){
    let $historyRecord=Util.getElement('#historyRecord')
    let $rules=Util.getElement('#rules')
    let $mask=Util.getElement('.mask')
    let $prompt=Util.getElement('.prompt')

    let init=function (){
        eventBind()
    }

    let eventBind=function (){
        $historyRecord.addEventListener('click',function (e){
            let message="记录：<br>用户名&nbsp&nbsp&nbsp&nbsp生命值&nbsp&nbsp&nbsp&nbsp筹码&nbsp&nbsp&nbsp&nbsp时间"
            let result=getCookie("result")
            if(result){
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
            Interface.promptMessage($mask,$prompt,false,message)
        },false)

        $rules.addEventListener('click',function (e){
            Interface.promptMessage($mask,$prompt,false,Config.rules)
        },false)

        $mask.addEventListener('click',function(e){
            if(e.target.id!='prompt'){
                Interface.promptMessage($mask,$prompt,true)
            }
        },false)
    }

    return {
        init:init
    }
}()

Index.init()