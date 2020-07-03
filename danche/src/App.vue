<template>
  <div id="app">
    <top v-if="TopShow"></top>
    <div>
      <transition :name="transitionName">
          <router-view class="Router"></router-view>
      </transition>
      <!-- <router-view></router-view> -->
      
    <bottom v-if="BottomShow"></bottom>
    </div>
  </div>

</template>

<script>
import bottom from '@/components/bottom.vue'
import top from '@/components/top.vue'
// import axios from 'axios'

export default {
  
  data(){
    return {
    TopShow:true,
    BottomShow:true,
    transitionName: ""
    // info:{
    //   id:null,
    //   num:null
    // }
   
    }
  },
  name: 'app',
  components: {
    bottom,
    top
  },
  methods:{

    //  login(){
    //    //直接把参数写在post头部，还有好几种写法，区别不大
    //    axios.get("http://127.0.0.1/test.php").then((res)=>{
       
    //      console.log(res.data);
        
     
        
         
    //    })
    //  }
      
  },
   
    mounted(){
       
    this.TopShow = this.$route.meta.TopShow;
    this.BottomShow = this.$route.meta.BottomShow;

   
    }, 
  watch:{
    $route(to){
             this.TopShow = to.meta.TopShow;
             this.BottomShow = to.meta.BottomShow;
               
              var isBack = this.$router.isBack // 监听路由变化时的状态为前进还是后退
             
              if(isBack==1) {
                this.transitionName = 'slide-right'
              }else if(isBack==0&&!this.$route.meta.BottomShow){
                 this.transitionName = 'slide-left'
              }else  {
                this.transitionName = ''
              }
                this.$router.isBack = 0


               
        }
  }
}
</script>
 
<style  scoped>

 .Router
    {
        position: absolute;
        width: 100%;
        transition: all ease 250ms;
    }
    .slide-left-enter,
    .slide-right-leave-active
    {
        -webkit-transform: translate(100%, 0);
        transform: translate(100%, 0);
    }

    .slide-left-leave-active,
    .slide-right-enter
    {
        opacity: 0;
        -webkit-transform: translate(-100%, 0);
        transform: translate(-100% 0);
    }

</style>