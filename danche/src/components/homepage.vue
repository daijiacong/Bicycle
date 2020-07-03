<template>
    <div style="background-color: rgb(240, 240, 240);">
    <!-- <img src="../static/images/senlin.png" style="width:100%;height:150px;"/> -->
    
     
          <van-popup v-model="show0" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
          </van-popup>

     <!-- <van-notice-bar text="校园单车易出行系统只负责单车租赁买卖，交易后出现的一系列问题由交易者双方协商！" left-icon="volume-o"/> -->
<div  style="background-color: rgb(240, 240, 240);"> 
      <van-search  placeholder="请输入搜索关键词"  show-action shape="round">
      <div slot="action">搜索</div>
       <div slot="label">地址</div>
</van-search>
   
   <van-swipe :autoplay="3000" indicator-color="white" style="position:relative;" >
     <van-swipe-item><img style="width:100%;height:150px;" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574139717839&di=193a540eccca42984daa2d248bc86a0f&imgtype=0&src=http%3A%2F%2Fcomment.gog.cn%2Fpic%2F003%2F018%2F252%2F00301825206_001f49cb.jpg" /></van-swipe-item>
     <van-swipe-item><img style="width:100%;height:150px;" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574139605803&di=3c9c2250f4597445d2668ee914366848&imgtype=0&src=http%3A%2F%2Fres.zjknews.com.cn%2Fa%2F10001%2Ffile%2F201501%2F25%2F4570d8773f81c485d58b5caa4dac5c66.jpg"/></van-swipe-item>
     <van-swipe-item><img style="width:100%;height:150px;" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1577760048&di=a90f1021123cc17ef2a30c642c90c7e2&imgtype=jpg&er=1&src=http%3A%2F%2Fnews.xhu.edu.cn%2F_upload%2Farticle%2Fimages%2Ff7%2Fc3%2F04e3b8a24cfe814883b43f2ae346%2F06cca6b2-83e4-4d75-a549-19936429effc_d.jpg" /></van-swipe-item>
     <van-swipe-item><img style="width:100%;height:150px;" src="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1574139605803&di=6930d55cef1f4cf7a2a9d73b0a9f3b5f&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fpic%2F7%2Fdf%2Fbbcd1397376.jpg" /></van-swipe-item>
   </van-swipe>
</div>
    <van-row type="flex" justify="center">
      <van-col span="8" style="text-align:center"><router-link to="/homePage/yangche"><img  style="height:30px;width:30px;" src="../static/images/单车.png"><br><span style="color:black;">二手车</span></router-link></van-col>
      <van-col span="8" style="text-align:center"><router-link to="/homePage/youhailajiInfo"><img  style="height:30px;width:30px;" src="../static/images/历史订单.png"><br><span style="color:black;">历史订单</span></router-link></van-col>
      <van-col span="8" style="text-align:center"><router-link to="/homePage/shilajiInfo"><img  style="height:30px;width:30px;" src="../static/images/租车.png"><br><span style="color:black;">租车</span></router-link></van-col>
      <van-col span="8" style="text-align:center"><router-link to="/homePage/ganlajiInfo"><img  style="height:30px;width:30px;" src="../static/images/超值.png"><br><span style="color:black;">今天超值</span></router-link></van-col>
   </van-row>
   <van-row type="flex" justify="center">
      <van-col span="8" style="text-align:center"><router-link to="/homePage/yangche"><img  style="height:30px;width:30px;" src="../static/images/分期.png"><br><span style="color:black;">申请分期</span></router-link></van-col>
      <van-col span="8" style="text-align:center"><router-link to="/homePage/youhailajiInfo"><img  style="height:30px;width:30px;" src="../static/images/保养.png"><br><span style="color:black;">养车</span></router-link></van-col>
      <van-col span="8" style="text-align:center"><router-link to="/homePage/shilajiInfo"><img  style="height:30px;width:30px;" src="../static/images/找车.png"><br><span style="color:black;">找车</span></router-link></van-col>
      <van-col span="8" style="text-align:center"><router-link to="/homePage/ganlajiInfo"><img  style="height:30px;width:30px;" src="../static/images/估价.png"><br><span style="color:black;">估价</span></router-link></van-col>
   </van-row>
     <hr  style="margin: 10px 5px; border-width: 1px 0px; border-top-style: solid; border-right-style: initial; border-bottom-style: solid; border-left-style: initial; border-top-color: rgba(0, 0, 0, 0.1); border-right-color: initial; border-bottom-color: rgba(255, 255, 255, 0.3); border-left-color: initial; border-image: initial; height: 0px;">
     <!-- <div style="height:200px;width:90%;border:1px solid red;">
       <van-row type="flex" gutter="20">
          <van-col span="10" style="text-align:center;height:110px;border:1px solid red;"></van-col>
          <van-col span="10" style="position:relative;heigth:110px;width:200px;border:1px solid red;"> </van-col>
       </van-row>
        <van-row type="flex" gutter="20">
          <van-col span="8" style="text-align:center;height:90px;border:1px solid red;"></van-col>
          <van-col span="8" style="position:relative;heigth:90px;width:50px;border:1px solid red;">  </van-col>
           <van-col span="8" style="border:1px solid red;"> </van-col>
       </van-row>
     </div> -->
     <!-- <hr  style="margin: 0px auto; border-width: 1px 0px; border-top-style: solid; border-right-style: initial; border-bottom-style: solid; border-left-style: initial; border-top-color: rgba(0, 0, 0, 0.1); border-right-color: initial; border-bottom-color: rgba(255, 255, 255, 0.3); border-left-color: initial; border-image: initial; height: 0px;"> -->
    <!-- <div style="height:40px;width:80%;margin:5px auto;border:1px solid blue;border-radius:25px;">
       <img src="../static/images/搜索.png" style="position:relative;top:5px;left:10px;heigth:30px;width:30px;"/>
       <span style="position:relative;font-size:18px;top:-3px;left:45px;">请输入搜索内容</span>-->


       <!-- <input type="button" value="提交" @click="test()"> -->
       <!-- 上传文件 -->
        <!-- <form action="http://47.93.150.30/bike/user/addAvatar" method="post" enctype="multipart/form-data">
            学号：<input name="sno"/> <br>
            姓名：<input name="sname"/> <br>
            上传图片：<input type="file" name="spicture"/><br>
            <input type="submit" value="提交"/>
        </form> -->
          
        
    </div> 
    
</template>

<script>

export default {
  
   data() {
    return {
      images: [
        'https://img.yzcdn.cn/vant/apple-1.jpg',
        'https://img.yzcdn.cn/vant/apple-2.jpg'
      ],
      show0:false,
      URl:"http://47.93.150.30/bike/route",
      sno:'',
      sname:'',
      file:''
    }
  },
  methods:{
  }
  }
  
</script>
