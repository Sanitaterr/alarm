    let switchCtn = document.querySelector('#switch-cnt');
    let switchC1 = document.querySelector('#switch-c1');
    let switchC2 = document.querySelector('#switch-c2')
    let switchCircle = document.querySelectorAll('.switch_circle')
    let switchBtn = document.querySelectorAll('.switch-btn')
    let aContainer = document.querySelector('#a-container')
    let bContainer = document.querySelector('#b-container')
    let allButtons = document.querySelectorAll('.submit')
 
    let getButtons = (e) => e.preventDefault()
    let changeForm = (e) => {
        //修改类名
        switchCtn.classList.add("is-gx");
        setTimeout(function(){
            switchCtn.classList.remove("is-gx");
        },1500)
        switchCtn.classList.toggle("is-txr");
        switchCircle[0].classList.toggle("is-txr")
        switchCircle[1].classList.toggle("is-txr")
 
        switchC1.classList.toggle("is-hidden");
        switchC2.classList.toggle("is-hidden");
        aContainer.classList.toggle("is-txl");
        bContainer.classList.toggle("is-txl");
        bContainer.classList.toggle("is-z");
    }
    //点击切换
    let shell = (e) => {
        for(var i=0; i< allButtons.length;i++)
            allButtons[i].addEventListener("click",getButtons);
        for(var i=0; i<switchBtn.length;i++)
            switchBtn[i].addEventListener("click",changeForm)
    }
    window.addEventListener("load",shell);





function createCode(){
    var code="";
    var characters="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    for (var  i=0;i<6;i++) {
        code+=characters[Math.floor(Math.random()*characters.length)];
    }
    document.getElementById("code").innerHTML=code;
}

function qdcode(){
    var text=document.getElementById("text").value;
    var t1=document.getElementById("t1").value;
    var t2=document.getElementById("t2").value;
    var createCode=document.getElementById("code").innerText;
    if(t1==""){
        alert("请输入账号!!!");
    }
    else if(t2==""){
        alert("请输入密码!!!");
    }
    else if(text==""){
        alert("请输入验证码!!!");
    }else if(text.toLowerCase() !=createCode.toLowerCase()){//都转换小写字母
         alert("您输入的验证码有误，请重新输入");
         location.reload();//(重新加载)输入错误时，重新生成验证码
    }else{
       alert("验证成功");
        // window.location='index.html'
    }

}
function cj(){
    var t3=document.getElementById("t3").value;
    var t4=document.getElementById("t4").value;
    var t5=document.getElementById("t5").value;
    if(t3==""){
        alert("请输入账号!!!");
    }
    else if(t4==""){
        alert("请输入密码!!!");
    }
    else if(t5==""){
        alert("请输入确认密码!!!");
    }else if(t4!=t5){
         alert("密码前后不一致，请重新输入");
        
    }else{
       alert("创建成功！！！请用户使用账号登录！");
        // window.location='login.html'
        
    }

}
window.onload=function(){
    createCode();//页面加载时自动生成验证码
}

//注册接口调用
function register() {
    document.querySelector('#regbutton').addEventListener('click',() => {
        const form = document.querySelector('#a-form')
        const data = serialize( form ,{ hash : true , empty : true })
        console.log(data);
        axios({
            url:'/user/register',
            method:'POST',
            data
        }).then(result => {
            console.log(result);
        }).catch(error => {
            console.dir(error)
        })
        form.reset()
    })
}
register()

//登录接口调用
function login() {
    document.querySelector('#logbuttom').addEventListener('click' , () => {
        const forms = document.querySelector('#b-form')
        const username = document.querySelector('#t1').value
        const password = document.querySelector('#t2').value
        const data = {username,password}
        // console.log(data);
        axios({
            url:'/user/login',
            method:'POST',
            data
        }).then(result => {
            localStorage.setItem('token',result.data.data)
            console.log(result);
            console.log(11);
            console.log(result.data.data);

        }).catch(error => {
            console.dir(error)
        })
        forms.reset()
        setTimeout(() => {
            location.href='./index.html'
        },500)
    })
}
login()