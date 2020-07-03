<template>
  <div class="rg_body">
     <div class="rg_form">
       <div class="rg_title">
          <span>新用户注册</span>
       </div>
       <!-- 表单校验：
					1.用户名：单词字符，长度8到20位
					2.密码：单词字符，长度8到20位
					3.email：邮件格式
					4.姓名：非空
					5.手机号：手机号格式
					6.出生日期：非空
					7.验证码：非空 -->
       <van-form @submit="onSubmit"  >
          <van-field  class="form_field"   autocomplete="off"
            v-model="username"
            name="username"
            label="用户名"
            placeholder="请输入用户名"
            :rules="[{ required: true, message: ' ' },{pattern:/^\w{8,20}$/ ,message:'至少8个字符最多20个字符'}]"
          />
          <van-field  autocomplete="off" class="form_field"
            v-model="password"  style="font-size:20px;line-height:40px;"
            type="password"
            name="password"
            label="密码"
            placeholder="请输入密码"
            :rules="[{ required: true, message: ' ' },{pattern:/^\w{8,20}$/ ,message:'至少8个字符最多20个字符'}]"
          />
           
          <van-field    autocomplete="off" class="form_field"
            v-model="email"  
            name="email"
            type="email"
            label="email"
             placeholder="请输入email"
            :rules="[{ required: true, message: ' ' },{pattern:/\w+@\w+\.\w+/ ,message:'请输入正确的邮箱格式'}]"
            
          />
        
          <van-field    autocomplete="off" class="form_field"
            v-model="name"  
            label="姓名"
            type="text"
            name="name"
             placeholder="请输入姓名"
            :rules="[{ required: true, message: '  ' }]"
          />
           <van-field    autocomplete="off" class="form_field"
            v-model="telephone" 
            label="手机号"
            type="text"
            name="telephone"
             placeholder="手机号"
            :rules="[{ required: true, message: '  ' },{pattern:/^1[3456789]\d{9}$/ ,message:'请输入正确的手机号'}]"
          />
          <van-field name="sex" label="性别"  class="form_field" >
            <template #input>
              <van-radio-group v-model="radio" direction="horizontal">
                <van-radio name="m">男</van-radio>
                <van-radio name="w">女</van-radio>
              </van-radio-group>
            </template>
          </van-field>

        <van-field    autocomplete="off" class="form_field"
            v-model="birthday"  
            label="出生日期"
            type="date"
            name="birthday"
             placeholder="出生日期"
            :rules="[{ required: true, message: '  ' }]"
          />

          <van-field    autocomplete="off" class="form_field"
            v-model="check" 
            type="text"
            name="check"
            placeholder="请输入验证码"
            :rules="[{ required: true, message: '  ' }]"
          />
                      <img id="rg_vcode" src="http://localhost/bike/checkCode" height="32px" alt="" @click="changeCheckCode()">
                      
                  <div style="margin: 10px;">
                    <van-button round block type="info" native-type="submit">
                      注册
                    </van-button><br>
                     <div style="text-align:right;" >已有账号?<router-link to="/login"><span style="color:white">前去登录</span> </router-link></div>
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
      email:'',
      name:'',
      telephone:'',
      birthday:'',
      radio: 'm',
      // pattern: /^\w{8,20}$/,
      

    };
  },
  methods: {
    onSubmit(values) {

       console.log('submit', values);
      
          axios.get("http://localhost/bike/user/regist",
          {
            params:values,
         
        }).then((res) => {
           if(res.data.flag==false){
              this.$dialog.alert({
                  message: res.data.errorMsg,
              })
           }else{
             this.$dialog.alert({
                  message: "注册成功！！！",
              })
               this.$router.push({path:"/login"})
           }
       })
    },
     changeCheckCode() {
        //1.获取验证码图片对象
            var img = document.getElementById("rg_vcode");
						img.src="http://localhost/bike/checkCode?"+new Date().getTime();
                                        }
  },
      
}
</script>


<style scoped>
.rg_body{
    background: url(../static/images/register.png) no-repeat ;
    background-size:100% 100%; 
     
   background-attachment: fixed;

}
.rg_form{
  margin: 60px 40px 0px;
  border-radius:0px 0px 10px 10px;
 
}
.rg_title {
    height: 50px;
    line-height: 50px;
    background: #1E90FF;
    font-size: 22px;
    padding:0 40px; 
    color: white;
    border-radius:10px 10px 0px 0px;
    text-align: center;
    padding-top: 10px;
}
#rg_vcode{
  position:relative;
  float:right;
  bottom: 45px;
  right: 50px;
}
.form_field{
  font-size:20px;
  line-height:30px;
}
</style>