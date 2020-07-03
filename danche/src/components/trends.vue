<template>
    <div style="padding:0px 0px 50px; background-color: rgb(240, 240, 240);" >
        <!-- Search 搜索 -->
        <van-search v-model="value" show-action placeholder="请输入搜索单车地址" @search="changePage">
             <div slot="action" @click="changePage">搜索</div>
       </van-search>
         <van-tabs v-model="activeName" @change="changePage" color="#1E90FF" title-active-color="#1E90FF" style="padding-top:3px;" >
               <van-tab  name="1" title="买入"  >
                
                  <van-cell-group>
                     <div  v-for="(list,i) in lists1" :key="i">
                        
                    <div  style="border-radius:15px;height:130px;margin:0px 10px 15px;background-color:white;">
                        <div :class="{ notBought: !list.isBuy, Bougth: list.isBuy }">
                             
                            <b>信息号: {{list.tasknumber}}</b>
                             <router-link v-bind="{to:'/trends/detail/'+list.tasknumber}"><span style="position:absolute;right:25px;color:white;">详情</span></router-link>
                        </div>
                          <van-row>
                                 <van-col span="12">单车品牌: {{list.brand}}<br>单车颜色: {{list.colour}}<br>空闲时间: {{list.freetime}}<br>单车地址: {{list.address}}</van-col>
                                <van-col span="12"><div v-if="list.isBuy" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#708090;font-size:30px;border:1px solid #708090">已成交 </div></van-col>
                                 <van-col span="12"><div v-if="!list.isBuy" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#1E90FF;font-size:30px;border:1px solid #1E90FF">待成交 </div></van-col>
                         </van-row>
                          
                    </div>  
                 </div>
                 </van-cell-group>          
                
             </van-tab>
             <van-tab name="2" title="租用" >
                <van-cell-group>
                 <div  v-for="(list,i) in lists1" :key="i" >
                    <div  style="border-radius:15px;height:130px;margin:0px 10px 15px;background-color:white;">
                        <div :class="{ notBought: !list.isBuy, Bougth: list.isBuy }">
                             
                            <b>信息号: {{list.tasknumber}}</b>
                             <router-link v-bind="{to:'/trends/detail/'+list.tasknumber}"><span style="position:absolute;right:25px;color:white;">详情</span></router-link>
                        </div>
                          <van-row>
                                <van-col span="12">单车品牌: {{list.brand}}<br>单车颜色: {{list.colour}}<br>空闲时间: {{list.freetime}}<br>单车地址: {{list.address}}</van-col>
                                <van-col span="12"><div v-if="list.isBuy" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#708090;font-size:30px;border:1px solid #708090">已成交 </div></van-col>
                                 <van-col span="12"><div v-if="!list.isBuy" style="position:absolute; right:0px;margin:50px 40px 10px; ;color:#1E90FF;font-size:30px;border:1px solid #1E90FF">待成交 </div></van-col>
                         </van-row>
                          
                    </div>  
                 </div>
                 </van-cell-group>          
               
             </van-tab>
            </van-tabs>
            <!-- 分页展示 -->
            <van-pagination  v-model="currentPage"  :page-count="totalPage" mode="simple"  @change="changePage"  />
           
     
     </div>
</template>

<script>
import axios from 'axios';

export default {
    data(){
        return {
            lists1:[],  //接收res.data.list数据     
            activeName:'1',
            currentPage: null, //当前页数，即第几页
            totalPage:null,    //总页数
            value:''
        }
    },
    methods:{
    getPage(){
          axios({
             method:'get',
             url:"http://localhost/bike/BicycleServlet/pageQuery?cid="+this.activeName
          }).then(res=>{
              this.currentPage=res.data.currentPage;  //当前页数初始等于1
              this.totalPage=res.data.totalPage   //数据库查询到的数据的总页数
             // console.log(res.data.list);
               this.lists1=res.data.list;  
                 for(let i = 0; i < this.lists1.length; i++){
                   if(this.lists1[i].bl_uid=== null||this.lists1[i].bl_uid==''){
                       this.lists1[i]['isBuy'] = false
                   }else{
                       this.lists1[i]['isBuy'] = true 
                   }
               }
             
          })    
      },
    changePage(){
        
        axios({
             method:'get',
             url:"http://localhost/bike/BicycleServlet//pageQuery?cid="+this.activeName+"&&currentPage="+this.currentPage+"&&address="+this.value
          }).then(res=>{
              this.currentPage=res.data.currentPage;  //当前页数初始等于1
              this.totalPage=res.data.totalPage   //数据库查询到的数据的总页数
             // console.log(res.data.list);
               this.lists1=res.data.list;  
                 for(let i = 0; i < this.lists1.length; i++){
                   if(this.lists1[i].bl_uid=== null||this.lists1[i].bl_uid==''){
                       this.lists1[i]['isBuy'] = false
                   }else{
                       this.lists1[i]['isBuy'] = true 
                   }
               }
             
          })    
    },
     
    },
    mounted(){
        this.getPage();
        
    },
      
}
</script>

