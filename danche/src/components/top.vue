<template>
  <div style="font-co">
    <!-- <van-nav-bar :left-arrow="JianTouShow" style="background-color:#1E90FF;color:green;"  :title="title" @click-left="onClickLeft" /> -->
    <van-nav-bar style="color:green;background-color:#1E90FF;" @click-left="onClickLeft"   >
 <span slot="title" style="font-size:18px;color:white">{{title}}</span>
<img v-if="JianTouShow" slot="left" style="position:relative;heigth:25px;width:25px;top:10px;left:-10px;" src="../static/images/箭头.png" />
</van-nav-bar>
  </div>
</template>

<script>
export default {
    data(){
      return {
           title:"",
           JianTouShow:false
      }
    },
    methods: {
    onClickLeft() {
      this.$router.back(-1);
      this.$router.isBack=1
      //  history.back();
    }
    },
    // 这有什么用
    mounted(){
       this.title = this.$route.meta.title;
       this.JianTouShow=this.$route.meta.JianTouShow;
    },

  watch:{
      $route(){
      
             this.title = this.$route.meta.title;
             this.JianTouShow = this.$route.meta.JianTouShow;
         
        }
    }
  }
</script>


