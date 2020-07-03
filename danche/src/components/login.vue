<template>
  <div class="body">
     <div class="_form">
       <div class="title">
          <span>欢迎登录校园易出行</span>
       </div>
       <van-form @submit="login"   >
          <van-field   style="font-size:20px;line-height:40px"  autocomplete="off"
            v-model="username"
            name="username"
            placeholder="请输入用户名"
            
          />
          <van-field  autocomplete="off"
            v-model="password"  style="font-size:20px;line-height:40px;"
            type="password"
            name="password"
          
            placeholder="请输入密码"
            
          />
           <van-field  label-width=50px  autocomplete="off"
            v-model="check"  style="font-size:20px;line-height:40px;"
            type="text"
            name="check"
             placeholder="请输入验证码"
          
            
          />
         
            
              <img id="vcode" src="http://localhost/bike/checkCode" height="32px" alt="" @click="changeCheckCode()">
              
          <div style="margin: 16px;">
            <van-button round block type="info" native-type="submit">
              登录
            </van-button><br>
            <div style="text-align:right;" >没有账户？<router-link to="/register"><span style="color:white">立即注册</span> </router-link></div>
            <br>
            <br>
            <br>
            <br>
            <br><br><br><br><br>
          </div>
        </van-form>
     </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  
    data() {
    return {
      username: '',
      password: '',
      check:'',
      userToken:'',
     // URL:"http://47.93.150.30/bike"
    };
  },
  methods: {
    login(values) {
      if(this.username==''||this.password==''){
        this.$dialog.alert({
          message:"用户名或密码不能为空！ ！"
        })

      }

    
      else{
            //console.log('submit', values);
            axios.get("http://localhost/bike/user/login",
                {
                  params:values,
                
              }).then((res) => {
                
                if(!res.data.flag){
                    this.$dialog.alert({
                        message: res.data.errorMsg,
                    })
                }else{
                      this.userToken=res.data.data
                        // 将用户token保存到vuex中
                      this.$store.commit('changeLogin',this.userToken);
                      this.$router.push({path:"/homepage"})
                  
                }
                
            })
        }
    },
     changeCheckCode() {
        //1.获取验证码图片对象
            var img = document.getElementById("vcode");
       console.log(img);
                      img.src="http://localhost/bike/checkCode?"+new Date().getTime();
                      
                                        }
  },
}
</script>

<style scoped>
.body{
    background: url(../static/images/landing.png) no-repeat ;
    background-size:100% 100%; 
     
   background-attachment: fixed;

}
._form{
  margin: 200px 20px 10px;
  border-radius:0px 0px 10px 10px;
 
}
.title {
    height: 80px;
    line-height: 80px;
    background: #1E90FF;
    font-size: 22px;
    padding:0 30px; 
    color: white;
    border-radius:10px 10px 0px 0px;
}
#vcode{
  position:relative;
  float:right;
  bottom: 45px;
  right: 50px;
}
</style>