<template>
    <div>
        
        <van-cell-group>
            <van-field :value=lists.tasknumber label="信息号"  disabled/>
        </van-cell-group>
         <van-cell-group>
            <van-field :value=lists.brand label="单车品牌"  disabled/>
        </van-cell-group>
         <van-cell-group>
            <van-field :value=lists.colour label="单车颜色"  disabled/>
        </van-cell-group>
         <van-cell-group>
            <van-field :value=lists.freetime label="空闲时间"  disabled/>
        </van-cell-group>
         <van-cell-group>
            <van-field :value=lists.address label="单车地址"  disabled/>
        </van-cell-group>
        <van-cell-group>
            <van-field :value=lists.price label="单车价格"  disabled/>
        </van-cell-group>
         <van-cell-group>
            <van-field :value=lists.name label="联系人"  disabled/>
        </van-cell-group>
         <van-cell-group>
            <van-field :value=lists.phone label="联系人电话"  disabled/>
        </van-cell-group>
         <van-cell-group>
            <van-field :value=lists.remarks label="备注"  disabled/>
        </van-cell-group>
         <van-cell-group>
            <van-field :value=lists.wechat label="联系人微信"  disabled/>
        </van-cell-group>
  <van-button type="info" @click="Return()" style="position:absolute;left:20px;">取消</van-button>
  <van-button v-if="ifbuyOrLease" type="danger" @click=" buyOrLease()" style="position:absolute;right:20px;">确认买/租</van-button>
    </div>
</template>
<script>
import axios from 'axios';
export default {
    data(){
        return {
         lists:[],
         ifbuyOrLease:''
        }
    },
     methods:{
        getDetail(){
       axios.get("http://localhost/bike/BicycleServlet/findOne?tasknumber="+this.$route.params.tasknumber).then(
             (res)=>{
                 this.lists=res.data;
                 this.wherebuyOrLease(this.lists.bl_uid);
              })    
     },
        Return(){
             this.$router.go(-1);
        },
       buyOrLease(){
            
               
           
                axios.get("http://localhost/bike/BicycleServlet/apdateBuyorlease?tasknumber="+this.$route.params.tasknumber).then(
                ()=>{
                     this.$dialog.alert({
                        message: "购买/租用成功！",
                    })
                    this.$router.go(-1);
                    
                })   
            
        },
         wherebuyOrLease(bl_uid){
          if(bl_uid){
              this.ifbuyOrLease=false
          }else{
              this.ifbuyOrLease=true;
          }
          
    },
        
     },
   mounted(){
        //  console.log(this.$route.params.tasknumber);
        this.getDetail();
         
    },
}
</script>