<template>
    <div style="padding:0px 0px 0px; background-color: rgb(240, 240, 240);">
              <van-tabs v-model="activeName" @change="release" color="#1E90FF" title-active-color="#1E90FF" >
               <van-tab title="卖出" name="1" >
                <div >
                  <van-cell-group>
                    <!-- <div >
                       <van-uploader v-model="fileList" multiple :upload-text="'上传单车图片'" :before-read="beforeRead" :max-count="5"  />
                    </div> -->
                      <van-field size="large" is-link	 label="单车品牌" input-align="right" placeholder="请输入单车品牌" v-model="brand"/>
                      <van-field size="large" is-link	 label="单车颜色" input-align="right" placeholder="请输入颜色" v-model="colour"/>
                      <van-field size="large" is-link	 label="空闲时间" input-align="right" placeholder="请输入单车空闲时间" v-model="freetime"/>
                      <van-field size="large" is-link	 label="单车地址" input-align="right" placeholder="请输入单车放置地址" v-model="address"/>
                      <span style="position:relative;left:10px;color:#778899;">发布人信息</span>
                      <van-field size="large" is-link	 label="单车租用价" input-align="right" placeholder="请输入单车的租用价格" v-model="price" />
                    <van-field size="large" is-link	  label="联系姓名" input-align="right" placeholder="请输入联系姓名" v-model="name"/>
                    <van-field size="large" is-link	 label="联系电话" input-align="right" placeholder="请输入联系电话" v-model="phone"/>
                    <van-field size="large" is-link	 label="备注" input-align="right" placeholder="如不可载人(可不填）" v-model="remarks"/>
                    <van-field size="large" is-link	 label="微信号" input-align="right" placeholder="请输入微信号" v-model="wechat"/>
                    <div><van-button type="info" size="large" @click="release()">提交</van-button></div>
                    <br><br><br>
                 </van-cell-group>          
                </div>
             </van-tab>
             <van-tab title="租出" name="2" >
                   <van-cell-group>

                     <!-- <div >
                       <van-uploader v-model="fileList" multiple :upload-text="'上传单车图片'"  :max-count="5"  />
                    </div> -->

                     <van-field size="large" is-link	 label="单车品牌" input-align="right" placeholder="请输入单车品牌" v-model="brand"/>
                      <van-field size="large" is-link	 label="单车颜色" input-align="right" placeholder="请输入颜色" v-model="colour"/>
                      <van-field size="large" is-link	 label="空闲时间" input-align="right" placeholder="请输入单车空闲时间" v-model="freetime"/>
                      <van-field size="large" is-link	 label="单车地址" input-align="right" placeholder="请输入单车放置地址" v-model="address"/>
                      <span style="position:relative;left:10px;color:#778899;">发布人信息</span>
                      <van-field size="large" is-link	 label="单车租用价" input-align="right" placeholder="请输入单车的租用价格" v-model="price" />
                    <van-field size="large" is-link	  label="联系姓名" input-align="right" placeholder="请输入联系姓名" v-model="name"/>
                    <van-field size="large" is-link	 label="联系电话" input-align="right" placeholder="请输入联系电话" v-model="phone"/>
                    <van-field size="large" is-link	 label="备注" input-align="right" placeholder="如不可载人(可不填）" v-model="remarks"/>
                    <van-field size="large" is-link	 label="微信号" input-align="right" placeholder="请输入微信号" v-model="wechat"/>
                    <div><van-button type="info" size="large" @click="release()">提交</van-button></div>
                    <br><br><br>
                 </van-cell-group>          
               
             </van-tab>
            </van-tabs>
    </div>
</template>
<script>
 import axios from 'axios';
import { Toast } from 'vant';
export default {
    data(){
        return{
            brand:"",
            colour:"",            
            wechat:"",
            phone:"",
            freetime:"",
            address:"",
            price:"",
            name:"",
            remarks:"",
           activeName:'1',
            fileList: []

        }
    },
    methods:{
 
    release(){
     
          axios.get("http://localhost/bike/BicycleServlet/andOne?freetime="+this.freetime+"&&address="+this.address+"&&name="+this.name+"&&phone="+this.phone+"&&wechat="+this.wechat+"&&brand="+this.brand+"&&colour="+this.colour+"&&price="+this.price+"&&remarks="+this.remarks+"&&cid="+this.activeName).then(
            (res)=>{ 
             if(res.data.flag){
               Toast('上传成功！！！');
             }
            }
          ).catch((res)=>{
            console.log(res)
          })
    },
     afterRead (file) {
              this.uploadImg(file.file);
              // console.log(file);
              // console.log(file.file)
              
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
              // 通过append向form对象添加数据,可以通过append继续添加数据
              //或formdata1.append('file',file);
              formdata1.append('file', file, file.name);
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
      
    
}
</script>
