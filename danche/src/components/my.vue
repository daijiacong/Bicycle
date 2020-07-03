<template>
    <div>
      <div style="margin-top:25px;">
        
         <!-- <img  :src="require(`../${lists3.uavatar}`)" style="position:absolute;height:80px;">   -->
      <!-- <img v-lazy="lists3.unavatar" id="userhead" alt=""/>   -->
        <van-row>
          
         <!-- 上传头像 -->
         
           <van-col span="8" >
              <van-uploader :after-read="afterRead" :upload-text="'上传头像'" :before-read="beforeRead"  :max-count="2" v-model="fileList" :deletable="false" style="position:absolute;height:80px;top:25px;left:25px; width:80px;border-radius:80px; " >
              </van-uploader>
             </van-col>
            <!-- <van-col span="8" > <van-uploader> <img  :src="require(`../${lists3.uavatar}`)" style="position:absolute;height:80px;width=80px;"> </van-uploader></van-col> -->
          <van-col span="16"><b style="position:absolute;top:30px;font-size:25px">{{ $store.state.data.nickName}}</b>
            <br>
            你好！
            <br>
            <!-- 用户名：{{$store.state.data.userName}} -->
            <b>{{lists3.username}}</b>  
          </van-col>
        </van-row>
     </div>

       <hr style="margin-top:50px;height: 15px; background-color: rgb(240, 240, 240); border: none;">
      

        
        
        
        <van-cell is-link @click="showPopup2">
        <img src="../static/images/发布任务.png" style="height:25px;"/>
            <b style="position:absolute;margin-left:20px;font-size:18px;">我的发布</b>  
        </van-cell>   
          <van-popup style="background-color: rgb(240, 240, 240);" v-model="show2" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
            
            <hr style="margin-top:0px;height:45px; background-color:#1E90FF; border: none;">
            <span style="position:absolute;top:12px;font-size:18px;left:41%;color:white">我的发布</span>  
              <van-tabs v-model="active" @change="getMyReleasePage" color="#1E90FF" title-active-color="#1E90FF" style="padding-top:3px;"  >
                <van-tab title="卖出" name='1'>
              
                 <div  v-for="(list,i) in lists1" :key="i">
                        
                      <div  style="border-radius:15px;height:130px;margin:5px 10px 30px;background-color:white;">
                        <div :class="{ notBought: !list.isBuy, Bougth: list.isBuy }">
                             
                            <b>信息号: {{list.tasknumber}}</b>
                             <router-link v-bind="{to:'/trends/detail/'+list.tasknumber}"><span style="position:absolute;right:25px;color:white;">详情</span></router-link>
                        </div>
                          <van-row>
                                <van-col span="12">单车品牌: {{list.brand}}<br>单车颜色: {{list.colour}}<br>空闲时间: {{list.freetime}}<br>单车地址: {{list.address}}</van-col>
                                <van-col span="12"><div v-if="list.isBuy" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#708090;font-size:30px;border:1px solid #708090">已成交 </div></van-col>
                                 <van-col span="12"><div v-if="!list.isBuy" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#1E90FF;font-size:30px;border:1px solid #1E90FF">待成交 </div></van-col>
                                 <van-button v-if="!list.isBuy" style="position:relative;right:-130px;top:95px;" size="mini" type="danger" @click="Del2(list.tasknumber)">删除</van-button>
                         </van-row>
                             
                          
                    </div>  
                 </div>
                </van-tab>

                <van-tab title="租出" name='2'>
                       <div  v-for="(list,i) in lists1" :key="i">
                        
                      <div  style="border-radius:15px;height:130px;margin:5px 10px 30px;background-color:white;">
                        <div :class="{ notRent: !list.isRent, Rent: list.isRent }">
                             
                            <b>信息号: {{list.tasknumber}}</b>
                             <router-link v-bind="{to:'/trends/detail/'+list.tasknumber}"><span style="position:absolute;right:25px;color:white;">详情</span></router-link>
                        </div>
                          <van-row>
                                <van-col span="12">单车品牌: {{list.brand}}<br>单车颜色: {{list.colour}}<br>空闲时间: {{list.freetime}}<br>单车地址: {{list.address}}</van-col>
                                <van-col span="12"><div v-if="list.isRent" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#708090;font-size:30px;border:1px solid #708090">已成交 </div></van-col>
                                 <van-col span="12"><div v-if="!list.isRent" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#1E90FF;font-size:30px;border:1px solid #1E90FF">待成交 </div></van-col>
                                 <van-button v-if="!list.isRent" style="position:relative;right:-130px;top:95px;" size="mini" type="danger" @click="Del1(list.tasknumber)">删除</van-button>
                         </van-row>
                             
                          
                    </div>  
                 </div>
                </van-tab>
              </van-tabs>  


             
          </van-popup>
        <van-cell is-link @click="showPopup1">
        <img src="../static/images/订单.png" style="position:absolute;left:-5px;height:30px;"/>
            <b style="position:absolute;margin-left:43px;font-size:18px;">我的订单</b>  
        </van-cell>   
          <van-popup v-model="show1" closeable close-icon-position="top-left"  close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
           <hr style="margin-top:0px;height:45px; background-color:#1E90FF; border: none;">
            <span style="position:absolute;top:12px;font-size:18px;left:41%;color:white">我的订单</span>  
         
                <van-tabs v-model="activeName" @change="getMyIndentPage" color="#1E90FF" title-active-color="#1E90FF" style="padding-top:3px;"  >
                   <van-tab title="买入" name="1">
                       <div  v-for="(list,i) in lists2" :key="i">
                        
                      <div  style="border-radius:15px;height:130px;margin:5px 10px 30px;background-color:white;">
                        <div :class="{  Bougth: true }">
                             
                            <b>信息号: {{list.tasknumber}}</b>
                             <router-link v-bind="{to:'/trends/detail/'+list.tasknumber}"><span style="position:absolute;right:25px;color:white;">详情</span></router-link>
                        </div>
                          <van-row>
                                <van-col span="12">单车品牌: {{list.brand}}<br>单车颜色: {{list.colour}}<br>空闲时间: {{list.freetime}}<br>单车地址: {{list.address}}</van-col>
                                <van-col span="12"><div v-if="list.isRent" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#708090;font-size:30px;border:1px solid #708090">已成交 </div></van-col>
                                
                         </van-row>       
                          
                    </div>  
                 </div>
                     
                
                </van-tab>

                <van-tab title="租入" name='2' >
                       <div  v-for="(list,i) in lists2" :key="i">
                        
                      <div  style="border-radius:15px;height:130px;margin:5px 10px 30px;background-color:white;">
                        <div :class="{ Rent: true }">
                             
                            <b>信息号: {{list.tasknumber}}</b>
                             <router-link v-bind="{to:'/trends/detail/'+list.tasknumber}"><span style="position:absolute;right:25px;color:white;">详情</span></router-link>
                        </div>
                          <van-row>
                                <van-col span="12">单车品牌: {{list.brand}}<br>单车颜色: {{list.colour}}<br>空闲时间: {{list.freetime}}<br>单车地址: {{list.address}}</van-col>
                                <van-col span="12"><div v-if="list.isRent" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#708090;font-size:30px;border:1px solid #708090">已成交 </div></van-col>                
                         </van-row>
                             
                          
                    </div>  
                 </div>
                </van-tab>
              </van-tabs>  
            
          </van-popup>
     
      <hr style="height: 10px; background-color: rgb(240, 240, 240); border: none;">
   
        <van-cell is-link @click="showPopup3">
        <img src="../static/images/用户信用.png" style="height:25px;"/>
            <b style="position:absolute;margin-left:20px;font-size:18px;">用户信用</b>  
        </van-cell>   
          <van-popup v-model="show3" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
           
           <hr style="margin-top:0px;height:45px; background-color:#1E90FF; border: none;">
            <span style="position:absolute;top:12px;font-size:18px;left:41%;color:white">用户信用</span>  
           
            <div style="margin:100px 50px 50px 50px;">
               <span style="font-size:20px;">联系姓名:隔壁老王</span>
                 <br>
                 <br>
                <span style="font-size:20px;">联系电话:1389236584</span>
                <br>
                <br>
                <span style="font-size:20px;">Wechat:abc4484415p</span>
                <br>
                <br>
                <span style="font-size:20px;">单车地址：中大新华13栋1楼</span>
                <br>
                <br>
                <span style="font-size:20px;">空闲时间:2019-11-10 11:30至2019-11-11 15:30</span>
              </div>
          </van-popup>

        <van-cell is-link @click="showPopup11">
            <img src="../static/images/公告.png" style="height:25px;"/>
            <b style="position:absolute;margin-left:20px;font-size:18px;">系统公告</b>  
        </van-cell>   
          <van-popup v-model="show11" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
           
           <hr style="margin-top:0px;height:45px; background-color:#1E90FF; border: none;">
            <span style="position:absolute;top:12px;font-size:18px;left:41%;color:white">系统公告</span>  
          </van-popup>

           <van-cell is-link @click="showPopup12">
            <img src="../static/images/帮助.png" style="height:25px;"/>
            <b style="position:absolute;margin-left:20px;font-size:18px;">帮助与反馈</b>  
        </van-cell>   
          <van-popup v-model="show12" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
           <hr style="margin-top:0px;height:45px; background-color:#1E90FF; border: none;">
            <span style="position:absolute;top:12px;font-size:18px;left:41%;color:white">帮助与反馈</span>  
          </van-popup>
          
            <van-cell is-link @click="showPopup13">
            <img src="../static/images/电话.png" style="height:25px;"/>
            <b style="position:absolute;margin-left:20px;font-size:18px;">联系客服:0668-5444-564</b>  
        </van-cell>   
          <van-popup v-model="show13" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
           <hr style="margin-top:0px;height:45px; background-color:#1E90FF; border: none;">
            <span style="position:absolute;top:12px;font-size:18px;left:41%;color:white">联系客服</span>  
          </van-popup>


          <hr style="height: 20px; background-color: rgb(240, 240, 240); border: none;">

        <van-cell is-link @click="showPopup4">
        <img src="../static/images/设置.png" style="height:25px;"/>
            <b style="position:absolute;margin-left:20px;font-size:18px;">设置</b>  
        </van-cell>  
        <hr style="height: 200px; background-color: rgb(240, 240, 240); border: none;">
        
          <van-popup v-model="show4" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
            
               
              <hr style="margin-top:0px;height:45px; background-color:#1E90FF; border: none;">
              <span style="position:absolute;top:12px;font-size:18px;left:44%;color:white">设置</span>  

               <div style="margin-top:0px">

                  <van-cell is-link @click="showPopup6" size="large"><span style="font-size:18px;" >图标</span><img style="position:absolute;right:5px;height:30px;width:30px;" src="../static/images/头像.png"/></van-cell>
                  <van-popup v-model="show6" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
                  </van-popup>
                  
                  <van-cell is-link @click="showPopup7" size="large"><span style="font-size:18px;" >手机号</span><span style="position:absolute;right:5px;">183******67</span></van-cell>
                  <van-popup v-model="show7" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
                  </van-popup>
                   
                   <hr style="height:10px; background-color: rgb(240, 240, 240); border: none;">

                  <van-cell is-link @click="showPopup8" size="large"><span style="font-size:18px;" >关于</span><span style="position:absolute;right:5px;">版本号1.1.0</span></van-cell>
                  <van-popup v-model="show8" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
                  </van-popup>

                  <van-cell is-link @click="showPopup8" size="large"><span style="font-size:18px;" >密码管理</span><span style="position:absolute;right:5px;">已设置</span></van-cell>
                  <van-popup v-model="show8" closeable close-icon-position="top-left" close-icon="arrow-left" position="right" :style="{ width: '100%',height: '100%' }">
                        
                        <hr style="margin-top:0px;height:45px; background-color:#1E90FF; border: none;">
                        <b style="position:absolute;top:14px;font-size:15px;left:45%;color:white">设置密码</b>  
                         <!-- <hr style="height: 100px; background-color: rgb(240, 240, 240); border: none;"> -->
                         <div style="margin:20px 50px 50px 50px;">
                            <van-cell-group>
                                <van-field :value=this.$store.state.data.userName  label="用户名"  disabled/>
                                <van-field placeholder="请填写旧密码"  label="旧密码"  v-model="usedPassword"/>
                                <van-field placeholder="请填写新的密码" type="password" label="新密码" v-model="newPassword" />
                                <van-field placeholder="请再次填写新密码" type="password" label="确认密码"  v-model="newPassword1"/>
                            </van-cell-group>
                            <van-button type="info" size="large" @click="setpassword()">更新密码</van-button>
                         </div> 
                  </van-popup>
             
                  <hr style="height: 15px; background-color: rgb(240, 240, 240); border: none;">
                  <van-cell is-link @click="showPopup9" size="large"><span style="font-size:18px;" >换账号登录</span></van-cell>
                  <van-popup v-model="show9" round position="bottom" :style="{ height: '20%' }"/>

                  <hr style="height: 15px; background-color: rgb(240, 240, 240); border: none;">
                 
               </div>
              <van-cell is-link @click="showPopup10" size="large"><span style="font-size:18px;" >退出登录</span></van-cell>
                  <van-popup v-model="show10" round closeable close-icon-position="top-left" position="bottom" :style="{ height: '25%' }">
                    <div style=" margin:30px 40px 20px 40px;text-align:center;"><span style="font-size:10px;">退出后不会删除任何历史数据，下次登录依然可以使用本账号。</span>
                         <hr style="height: 1px; background-color: rgb(240, 240, 240); border: none;">
                         
                         <!-- <a style="color:red;font-size:18px" href="http://localhost/bike/user/exit">退出登录</a> -->
                        <van-button  type="danger" @click="exit" style="font-size:18px" >退出登录</van-button>
                         <hr style="height: 5px; background-color: rgb(240, 240, 240); border: none;">
                         <span style="font-size:18px;">取消</span> 

                    </div>
                        
                  </van-popup>
             
             <hr style="height: 220px; background-color: rgb(240, 240, 240); border: none;">
      </van-popup>
       
          
      <!-- <div style="margin-top:15px;height:100px;text-align:center;border:1px red soild">
           <van-button round block type="primary" style="position:relative;width:300px;height:40px;margin:5px auto;">登录</van-button>
           <van-button round block type="primary" style="position:relative;width:300px;height:40px;margin:15px auto;">注册</van-button>
      </div> -->

    </div>

</template>

<script>

 import axios from 'axios';
import { Toast } from 'vant';
export default {

  data() {
    return {
      show1: false,
      show2: false,
      show3: false,
      show4: false,
      show5:false,
      show6:false,
      show7:false,
      show8:false,
      show9:false,
      show10:false,
      show11:false,
      show12:false,
      show13:false,
      username:'',
      active:"1",
      activeName:'1',
      usedPassword:"",
      newPassword:"",
      newPassword1:"",
      lists1:[],
      lists2:[],
      lists3:[],
      lists4:[],
      uavatar: '50a5b86dd8d44482add7e0f3f6926965.jpg',
      
      fileList: [
        {
           url:require('../img/1eb4add3c66749c3a7f9b51c01bad91f.jpg')
               }
      ]
     
    }
    
  },
 

  methods: {
    showPopup1() {
      this.show1= true;
    },showPopup2() {
      this.show2= true;
    },showPopup3() {
      this.show3= true;
    },showPopup4() {
      this.show4= true;
    },showPopup5() {
      this.show5= true;
    },showPopup6() {
      this.show6= true;
    },showPopup7() {
      this.show7= true;
    },showPopup8() {
      this.show8= true;
    },showPopup9() {
      this.show9= true;
    },showPopup10() {
      this.show10= true;
    },showPopup11() {
      this.show11= true;
    },showPopup12() {
      this.show12= true;
    },showPopup13() {
      this.show13= true;
    }, 
       
     getMyReleasePage(){
          axios({
             method:'get',
             url:"http://localhost/bike/BicycleServlet/findUserLease?cid="+this.active
          }).then(res=>{
              
               this.lists1=res.data.list;  
               //console.log(res.data.list);
                 for(let i = 0; i < this.lists1.length; i++){
                   if(this.lists1[i].bl_uid=== null||this.lists1[i].bl_uid==''){
                       this.lists1[i]['isBuy'] = false
                   }else{
                       this.lists1[i]['isBuy'] = true 
                   }
               }
             
          })    
      },
     getMyIndentPage(){
            axios({
             method:'get',
             url:"http://localhost/bike/BicycleServlet/findUserIndent?cid="+this.activeName
          }).then(res=>{
              
               this.lists2=res.data.list;  
                //console.log(res.data.list);
                 for(let i = 0; i < this.lists2.length; i++){
                   if(this.lists2[i].bl_uid=== null||this.lists2[i].bl_uid==''){
                       this.lists2[i]['isBuy'] = false
                   }else{
                       this.lists2[i]['isBuy'] = true 
                   }
               }
             
          })    
     
     },
     Del1(tasknumber){
       axios.get("http://localhost/danche/lease/deleteLease.php?tasknumber="+tasknumber).then(
             (res)=>{
              alert(res.data);
              this.getList1();
              })    
     },
    Del2(tasknumber){
       axios.get("http://localhost/danche/sell/deleteSell.php?tasknumber="+tasknumber).then(
             (res)=>{
              alert(res.data);
              this.getList2();
              })    
     },
     setpassword(){
       if(this.newPassword!==this.newPassword1){
             alert("两次输入的新密码不一致!");
       }else{
           if(this.usedPassword!==this.$store.state.data.passWord){
               alert("用户名或密码错误，请重新填写。");
           }else{
              axios.get("http://localhost/danche/updatepassword.php?passWord="+this.newPassword+"&&userName="+this.$store.state.data.userName).then(
             (res)=>{
              alert(res.data);
              this.$router.push({path: '/landing'})

              })    
           }

       }
     },
    //  java
    getOne(){
      axios.get("http://localhost/bike/user/findOne").then(
             (res)=>{
             this.lists3=res.data;
            // this.fileList[0].=this.lists3.uavatar;
            //  var img = document.getElementById("uavatar");
            //  img.src="../static/images/avatar/1.jpg";    
            //  '<li>+<img src="'+"../static/images/avatar/1.jpg"+'" style="width: 299px;">+</li>'   
         //   this.fileList[0].url=require(`../${this.lists3.uavatar}`);
            this.fileList[0].url=require(`.././${this.lists3.uavatar}`);
            console.log(this.lists3.uavatar);
            
           // this.uavatar1=this.lists3.uavatar;  
              })    
    },

    exit(){
    
      sessionStorage.clear();
      this.$router.push({path:"/login"})
    },
          /**
          * 上传图片之后调用接口
          */
    afterRead (file) {
              this.uploadImg(file.file);
              //console.log(file);
              //console.log(file.file)
          },
          /**
          * 上传图片之前判断图片是否符合条件
          */
    beforeRead (file) {
              if (file.type !== 'image/jpeg' && file.type !== 'image/png') {
                  Toast('请上传 jpg/png 格式图片');
                      return false;
              }
              let isLt1M = file.size / 1024 / 1024 <= 1
              if (!isLt1M) {
                  Toast('图片大小1M以内');
                  return false
              }
              return true;
          },
          /**
           * 上传图片
          */
    uploadImg (file) {
            // 创建form对象
              let formdata1 = new FormData();
             // let formdata2 =new FormData();
              // 通过append向form对象添加数据,可以通过append继续添加数据
              //或formdata1.append('file',file);
              formdata1.append('file', file, file.name);
              // console.log(formdata1);
              // console.log(formdata2)
              //设置请求头
              let config = {
                  headers:{
                      'Content-Type':'multipart/form-data'
                  }
              };
              axios.post('http://localhost/bike/user/addAvatar',formdata1,config).then((res)=>{   //这里的url为后端接口
                  console.log(res.data);
                 // this.path1=res.data;
                  //res 为接口返回值
              }).catch(() => {})
          },
       
   },
 created(){
      // this.getList1();
      // this.getList2();
      // this.getList3();
      // this.getList4();
      // this.$store.dispatch("getdata");
      
      this.getOne();
      this.getMyReleasePage();
      this.getMyIndentPage();
      
      
  },
  mounted(){
    
  }
    
   
    
    
  
  // computed:{
  //  data(){
  //    return this.$store.state.data;
  //  },
  // },
  
  
}
</script>


