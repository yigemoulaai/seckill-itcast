<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="screen-orientation" content="portrait"> <!--    //Android 禁止屏幕旋转-->
    <meta name="full-screen" content="yes">             <!--    //全屏显示-->
    <meta name="browsermode" content="application">     <!--    //UC应用模式，使用了application这种应用模式后，页面讲默认全屏，禁止长按菜单，禁止收拾，标准排版，以及强制图片显示。-->
    <meta name="x5-orientation" content="portrait">     <!--    //QQ强制竖屏-->
    <meta name="x5-fullscreen" content="true">          <!--    //QQ强制全屏-->
    <meta name="x5-page-mode" content="app">            <!--    //QQ应用模式-->
    <meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no,minimal-ui">    <!--    //WebApp全屏模式-->
    <meta name="format-detection" content="telphone=no, email=no"/>  <!--    //忽略数字识别为电话号码和邮箱-->
    <link rel="icon" href="./images/favicon.ico">
      <link rel="stylesheet" href="https://unpkg.com/swiper/css/swiper.min.css">
      <title>${sku.name}</title>
  </head>
<body>
<div id="app">
<header>
    <div class="back"><img src="./images/back.png" width="44" alt=""></div>
    <div class="swiper-container">
        <div class="swiper-wrapper">
            <#list images as image>
                <div class="swiper-slide"><img src="${image}" width="100%"></img></div>
            </#list>
        </div>
        <!-- 如果需要分页器 -->
        <div class="swiper-pagination"></div>
    </div>
    <div class="detailsInfo">
        <div style="padding: 14px;">
            <img src="./images/detailLog.png" width="45" alt="">
        </div>
        <div style="flex: 1; padding-top: 20px;padding-left: 10px;">
            <div><span>￥</span><span style="font-size: 20px;font-weight: bold">${sku.seckillPrice}</span></div>
            <div style="text-decoration: line-through;font-size: 12px;"><span>￥</span><span>${sku.price}</span></div>
        </div>
        <div style="padding-top: 15px;padding-right: 15px;">
            <div style="font-size: 14px;margin-bottom: 6px">{{message}}</div>
            <div id="time" class="time"><span>{{hours}}</span> ：<span>{{minutes}}</span> ：<span>{{seconds}}</span> </div>
        </div>
    </div>
</header>
<div class="skileDetail">
    <div class="itemCont infoCont">
        <div class="title">${sku.name}</div>
        <div class="desc">${sku.brandName} ${sku.category3Name}</div>
        <div class="item"><div class="info"><span>已选</span><span>${spec} 1件</span></div><img src="./images/right.png" width="44" alt=""></div>
        <div class="item"><div class="info"><span>送至</span><span>北京市昌平区建材城西路9号, 传智播客前台</span></div><img src="./images/right.png" width="44" alt=""></div>
    </div>
    <div class="itemCont comment">
        <div class="title">
            <div class="tit">评论</div>
            <div class="rit">
                <span>好评度 100%</span>
                <img src="./images/right.png" width="44" alt="">
            </div>
        </div>
        <div class="cont">
            <div class="item">
                <div class="top">
                    <div class="info">
                        <img src="./images/icon.png" width="40" height="40" alt="" />
                        <div style="color:#999;margin-left: 10px">张庆</div>
                    </div>
                    <div>
                        <img src="./images/shoucang.png" width="11" alt="">
                        <img src="./images/shoucang.png" width="11" alt="">
                        <img src="./images/shoucang.png" width="11" alt="">
                        <img src="./images/shoucang.png" width="11" alt="">
                        <img src="./images/shoucang.png" width="11" alt="">
                    </div>
                </div>
                <div class="des">
                    <div class="tit">质量不错，灵敏度高，结构巧妙，款式也好看</div>
                    <div style="margin: 10px 0">
                        <img src="./images/detail1.png" width="79" height="79" />
                        <img src="./images/detail2.png" width="79" height="79" />
                    </div>
                    <div class="font">
                        购买时间：2016-12-02      黑色，公开版，128GB,1件
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="content">
        <img src="./images/demo.png" width="100%" alt="">
    </div>
</div>
<footer>
    <div class="collection">
        <span><img src="./images/shoucang.png" width="21" alt=""></span>
        <span>收藏</span>
    </div>
    <button value="立即购买" @click="addOrder()">立即购买</button>
</footer>
</div>
<!-- 引入组件库 -->
<script src="vue.js"></script>
<script type="text/javascript" src="axios.js"></script><!-- axios交互-->
<script>
    var app = new Vue({
        el:'#app',
        data:{
            message:'',
            hours:'',
            minutes:'',
            seconds:'',
            socket:null
        },
        methods:{
            /***
             * 时间运算
             * @param starttimes:秒杀开始时间
             * @param endtimes：秒杀结束时间
             */
            timeCalculate:function (starttimes,endtimes) {
                //获取当前时间
                let nowtimes = new Date().getTime();
                if(nowtimes>endtimes){
                    this.message='活动已结束！';
                    this.isbegin=0;
                    return;
                }
                //时间差
                let nums = 0;

                //前缀，记录倒计时描述
                let prefix ='距离结束：';

                //判断是距离开始/距离结束
                if(starttimes<=nowtimes){
                    //距离结束,运算求出nums
                    nums = endtimes-nowtimes;
                    this.isbegin=1;
                }else{
                    //距离开始
                    prefix='距离开始：';

                    //运算求出nums
                    nums = starttimes-nowtimes;
                    this.isbegin=0;
                }

                //nums定时递减
                let clock = window.setInterval(function () {
                    //时间递减
                    nums=nums-1000;

                    //消息拼接
                    prefix+app.timedown(nums);

                    //nums<0
                    if(nums<=0){
                        //结束定时任务
                        window.clearInterval(clock);
                        //刷新时间，重新调用该方法
                        app.timeCalculate(starttimes,endtimes);
                    }
                },1000);
            },

            //将毫秒转换成天时分秒
            timedown:function(num) {
                var oneSecond = 1000;
                var oneMinute=oneSecond*60;
                var oneHour=oneMinute*60
                var oneDay=oneHour*24;
                //天数
                //var days =Math.floor(num/oneDay);
                //小时
                //this.hours =Math.floor((num%oneDay)/oneHour);
                this.hours =Math.floor(num/oneHour);
                //分钟
                this.minutes=Math.floor((num%oneHour)/oneMinute);
                //秒
                this.seconds=Math.floor((num%oneMinute)/oneSecond);
                //拼接时间格式
                //var str = days+'天'+hours+'时'+minutes+'分'+seconds+'秒';
                //return str;
            },

            //计算时间
            loadTime:function () {
                //秒杀倒计时时间
                let tm1 = new Date("${sku.seckillBegin?string('yyyy-MM-dd HH:mm:ss')}").getTime();
                let tm2 = new Date("${sku.seckillEnd?string('yyyy-MM-dd HH:mm:ss')}").getTime();
                this.timeCalculate(tm1,tm2);
            },
            //下单
            addOrder:function () {
                //获取令牌
                localStorage.setItem("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI4MzRlZmE3Mi0zODRmLTQ0YzMtYjE0Ny02ZTRiMGNjNWE4NDkiLCJpYXQiOjE1OTA2MzY3NDgsImlzcyI6IjgzNGVmYTcyLTM4NGYtNDRjMy1iMTQ3LTZlNGIwY2M1YTg0OSIsInN1YiI6IjgzNGVmYTcyLTM4NGYtNDRjMy1iMTQ3LTZlNGIwY2M1YTg0OSIsInBob25lIjoiMTM2NzAwODEzNzYiLCJuYW1lIjoi5rKI5Z2k5p6XIiwidXNlcm5hbWUiOiJpdGhlaW1hIiwiZXhwIjoxNTkxNDM2NzQ4fQ.IvOURAz7gpNqz22sIffGkDLvQglFx2-vQWI_wq-FgVY")
                var token = localStorage.getItem("token");
                if(token!=null && token!=''){
                    //将令牌传给后台  /lua/order/add
                    var instance = axios.create({
                    });
                    instance.defaults.headers.common['Authorization'] = 'Bearer '+token;
                    //发送请求
                    instance.post(`http://data-seckill-java.itheima.net/lua/order/add?id=${sku.id}`).then(function (response) {
                        console.log(response)
                        //跳转到个人中心
                        //location.href='http://data-seckill-java.itheima.net/#/user';
                        if(response.data.code==202){
                            //如果在排队，就建立链接
                            app.initWebSocket(response.data.username);
                        }
                    })
                }else{
                    //跳转登录
                    //location.href='http://data-seckill-java.itheima.net/#/login';
                    console.log("未登录！")
                }
            },
            //初始化创建websocket链接
            initWebSocket(uname){
                //实现化WebSocket对象，指定要连接的服务器地址与端口  建立连接
                this.socket = new WebSocket("ws://localhost:28082/ws/"+uname);
                //打开事件
                this.socket.onopen = function() {
                    console.log("Socket 已打开");
                };
                //接收消息
                this.socket.onmessage=this.loadMessage;
            },
            //后端消息接收
            loadMessage(e){
                console.log(e.data)
            }
        },
        created:function () {
            this.loadTime();
        }
    });
</script>

<script src="https://unpkg.com/swiper/js/swiper.js"> </script>
<script>
    var mySwiper = new Swiper ('.swiper-container', {
        loop: true, // 循环模式选项
        // 如果需要分页器
        pagination: {
            el: '.swiper-pagination',
        },
    })
</script>
<style>
    html,body{
        padding: 0;
        margin: 0;
        background: #F7F7F8;
    }
    header{
        background: #fff;
    }
    header .back{
        position: absolute;
        top:20px;
        left: 20px;
        z-index: 999;
    }
    header .detailsInfo{
        position: relative;
        z-index: 99;
        color: #fff;
        display: flex;
        top: -10px;
        background: url("./images/topTitle.png") top center no-repeat;
        background-size: cover;
    }
    header .detailsInfo .time span{
        background: #000;
        display: inline-block;
        padding: 2px 4px;
        font-size: 14px;
        border-radius: 3px;
    }
    .swiper-container {
        width: 100%;
        height: 300px;
    }
    .skileDetail{
        padding-bottom: 88px;
    }
    .skileDetail .infoCont .title{
        padding: 10px;
    }
    .skileDetail .infoCont .desc{
        padding: 10px;
        font-size: 12px;
        color: #D82F41;
    }
    .skileDetail .infoCont .item{
        border-top:solid 1px rgba(247,247,248,1);
        padding-left:15px;
        line-height: 44px;
        display: flex;
        justify-content: space-between;
    }
    .skileDetail .infoCont .item .info{
        display: flex;
        width: calc(100% - 44px);
    }
    .skileDetail .infoCont .item span:first-child{
        width: 44px;
        color:#92949C;
    }
    .skileDetail .infoCont .item span:last-child{
        display: block;
        line-height: 44px;
        overflow: hidden;

        flex: 1;
        white-space:nowrap;
        color:#333;
    }
    .itemCont{
        background: #fff;
        margin-bottom: 10px;
    }
    .comment .title{
        padding-right: 0;
        line-height: 44px;
        color:#666666;
        display: flex;
        justify-content: space-between;
        border-bottom: solid 1px #EAEAEA;
        align-items: center;
    }
    .comment .title .tit{
        position: relative;
        font-weight: bold;
        padding-left: 10px;
    }
    .comment .title .tit:before{
        position: relative;
        display: inline-block;
        width:2px;
        height:15px;
        background:rgba(216,47,65,1);
        content: '';
        left: -5px;
        top:2px;
        font-weight: bold;
    }
    .comment .title .rit{
        display: flex;
    }
    .comment .cont{
        padding: 15px 0;
        width: 100%;
    }
    .comment .cont .top{
        width: calc(100% - 20px);
        display: flex;
        justify-content: space-between;
        align-items: center;
    }
    .comment .cont .top .info{
        display: flex;
        flex: 1;
        align-items: center;
        padding-left: 20px;
    }
    .comment .cont .des{
        padding-left: 70px;
    }
    .comment .cont .des .tit{
        font-size: 14px;
    }
    .comment .cont .des .font{
        font-size: 12px;
        color: #999;
    }
    footer{
        position: fixed;
        background: #fff;
        padding: 20px 20px 0px;
        display: flex;
        height: 58px;
        width: calc(100% - 40px);
        left:0px;
        bottom:0px;
        border-top:solid 1px #EDEDED;
    }
    footer button{
        flex: 1;
        text-align: center;
        font-size: 12px;
        height:35px;
        background:linear-gradient(90deg,rgba(208,40,58,1) 43%,rgba(248,118,84,1) 100%);
        border-radius:18px;
        color:#fff;
        outline:none;
        border:none;
    }
    footer .collection{
        width: 30px;
        margin-right: 10px;
        display: flex;
        flex-direction: column;
        font-size: 12px;
    }
</style>
</body>
</html>