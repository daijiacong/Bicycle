<template>
  <div>
   <van-tabbar  v-model="active"  active-color="#1E90FF" inactive-color="#000">
  <van-tabbar-item  replace to="/homepage" icon="wap-home-o">首页</van-tabbar-item>
  <van-tabbar-item   replace to="/release" icon="add-o">发布</van-tabbar-item>
  <van-tabbar-item  replace to="/trends" icon="star-o">动态</van-tabbar-item>
  <van-tabbar-item  replace to="/my" icon="user-o">我的</van-tabbar-item>
</van-tabbar>

  </div>
</template>

<script>
export default {
  name: 'bottom',
  data(){
    return{
       active: 0,
      
       
    }
  }
 
}
</script>


<style scoped>

</style>
