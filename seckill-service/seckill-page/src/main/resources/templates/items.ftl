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
    <style>
        .swiper-container{
            overflow: hidden;
        }
    </style>
    <link rel="icon" href="./images/favicon.ico">
    <title>新诗“精魂”的追寻：穆旦研究新探 散文 中文 老年</title>
</head>
<body>
<div id="app">
    <header>
        <div class="back">
            <img onclick="history.go(-1)" src="./images/back.png" width="44" alt="">
        </div>
        <div class="swiper-container">
            <div class="swiper-wrapper">
                <#list images as image>
                    <div class="swiper-slide">
                        <img src="${image}" :onerror="defaultImage" width="100%"></img>
                    </div>
                </#list>
            </div>
            <!-- 如果需要分页器 -->
            <div class="swiper-pagination"></div>
        </div>
        <div class="detailsInfo">
            <div style="padding:8px 14px;position: relative;">
                <div class="cards"><span class="left"></span><span class="rit"></span></div>
                <img src="./images/detailLog.png" class="ico" width="45" alt="">
            </div>
            <div style="flex: 1; padding-top: 15px;padding-left: 30px;">
                <div><span style="font-size: 12px;">￥</span><span style="font-size: 20px;font-weight: bold">${sku.seckillPrice}</span></div>
                <div style="text-decoration: line-through;font-size: 12px;"><span>￥</span><span>${sku.price}</span></div>
            </div>
            <div style="padding-top: 10px;padding-right: 15px;font-size: 12px;">
                <div v-if="isbegin==1" id="time" class="time">
                    <div style="margin-bottom: 6px">距离结束：</div>
                    <div class="tim" ><span>{{hours}}</span> ：<span>{{minutes}}</span> ：<span>{{seconds}}</span></div>
                </div>
                <div v-if="isbegin==0" id="time" class="time">
                    <div style="margin-bottom: 6px">距离开始：</div>
                    <div class="tim"><span>{{hours}}</span> ：<span>{{minutes}}</span> ：<span>{{seconds}}</span> </div></div>
                <div v-if="isbegin==-1" id="time" class="time" style="line-height: 50px;">活动已结束</div>
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
                            <img src="./images/icon.png" width="25" height="25" alt="" />
                            <div style="color:#666;margin-left: 10px; font-size: 14px;">张庆</div>
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
                            <img src="./images/detail1.png" width="79" height="79" style="border-radius: 3px;"/>
                            <img src="./images/detail2.png" width="79" height="79" style="border-radius: 3px;"/>
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
        <button v-if="isbegin==1" value="立即购买" @click="addOrder()">立即购买</button>
        <button v-if="isbegin!=1" value="立即购买" style="background: #92949C;" disabled="disabled">立即购买</button>
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
            socket:null,
            isbegin:-1,
            showTime:'',
            defaultImage:'this.src="https://img11.360buyimg.com/n7/jfs/t1/101646/6/13621/393111/5e5d1390E7ea13607/0117bb13ca414b11.jpg"'
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

                console.log('starttimes:'+starttimes,'endtimes:'+endtimes,'nowtimes:'+nowtimes)
                if(nowtimes>endtimes){
                    this.message='活动已结束！';
                    this.isbegin=-1;
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
                this.showTime=this.hours+'小时'+this.minutes+'分钟'+this.seconds+'秒'
            },

            //计算时间
            loadTime:function () {
                //查询商品信息
                axios.get('http://data-seckill-java.itheima.net/api/sku/${sku.id}').then(function (resp) {
                    let skuinfo = resp.data.data;

                    //秒杀倒计时时间
                    console.log(skuinfo.seckillBegin)
                    console.log(skuinfo.seckillEnd)
                    let tm1 = new Date(skuinfo.seckillBegin.replace(/-/g,"/")).getTime();
                    let tm2 = new Date(skuinfo.seckillEnd.replace(/-/g,"/")).getTime();
                    app.timeCalculate(tm1,tm2);
                })
            },
            //下单
            addOrder:function () {
                //获取令牌
                var token = localStorage.getItem("token");
                if(token!=null && token!=''){
                    //将令牌传给后台  /lua/order/add
                    var instance = axios.create({
                    });
                    instance.defaults.headers.common['Authorization'] = 'Bearer '+token;
                    //发送请求
                    instance.post(`http://data-seckill-java.itheima.net/api/order/add/${sku.id}`).then(function (response) {
                        if(response.data.flag){
                            //跳转到个人中心
                            location.href='http://data-seckill-java.itheima.net/#/orderinfo?id='+response.data.message;
                        }else if(response.data.code===401){
                            alert('您的登录信息已过期，请重新登录！')
                            window.localStorage.clear();
                            //跳转登录
                            location.href='http://data-seckill-java.itheima.net/#/login';
                        }else{
                            alert(response.data.message)
                        }
                    })
                }else{
                    //跳转登录
                    location.href='http://data-seckill-java.itheima.net/#/login';
                }
            }
        },
        created:function () {
            this.loadTime();
        }
    });
</script>

<script>
    // var mySwiper = new Swiper ('.swiper-container', {
    //     loop: true, // 循环模式选项
    //     // 如果需要分页器
    //     pagination: {
    //         el: '.swiper-pagination',
    //     },
    // })
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
        height: 66px;
        top: -4px;
        background: linear-gradient(-90deg, #F87755 0%, #D82F41 64%);
    }
    header .detailsInfo .ico{
        z-index: 9;
        position: relative;
        left: 0px;
        top: 4px;
    }
    header .detailsInfo .cards .left{
        background: #C11E30;
        width: 65px;
        height: 70px;
        position: absolute;
        top:-4px;
        left: 0px;
        z-index: 1;
    }
    header .detailsInfo .cards .rit{
        background: transparent;
        width: 30px;
        height: 70px;
        position: absolute;
        overflow: hidden;
        top:-4px;
        left: 65px;
        z-index: 1;
    }
    header .detailsInfo .cards .rit::before{
        content: '';
        display: block;
        position: relative;
        width: 20px;
        height: 130px;
        transform: rotateZ(10deg) translate(-14px, -10px);
        box-shadow: 0px -4px 10px rgba(0,0,0,0.1);
        background: #C11E30;
    }
    header .detailsInfo .time span{
        background: #000;
        display: inline-block;
        padding: 2px 4px;
        text-align: center;
        min-width: 18px;
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
        color:#333;
        padding: 10px;
    }
    .skileDetail .infoCont .desc{
        padding: 10px;
        font-size: 12px;
        color: #D82F41;
        position: relative;
        top: -8px;
        line-height: 6px;
    }
    .skileDetail .infoCont .item{
        border-top:solid 1px rgba(247,247,248,1);
        padding-left:15px;
        line-height: 44px;
        display: flex;
        justify-content: space-between;
    }
    .skileDetail .infoCont .item .info{
        font-size: 13px;
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
        font-size: 14px;
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
        font-size: 13px;
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
        margin-bottom: 10px;
    }
    .comment .cont .top .info{
        display: flex;
        flex: 1;
        align-items: center;
        padding-left: 20px;
    }
    .comment .cont .des{
        padding-left: 57px;
    }
    .comment .cont .des .tit{
        font-size: 13px;
        color: #333333;
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