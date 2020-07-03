import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import axios from 'axios'

Vue.use(Vuex)
/* eslint-disable no-new */
const store = new Vuex.Store({
  plugins: [createPersistedState()],
  state:{
    data:[],
    JWT_token:sessionStorage.getItem('JWT_token') ? sessionStorage.getItem('JWT_token') : ''  
       
  },
  mutations:{
      getdata(state,res){
         state.data=res.data;
         //console.log(state.data);
      },
      getuser(state,userName){  
             state.userName = userName;
           },
      changeLogin(state,JWT_token){
              state.JWT_token = JWT_token;
              sessionStorage.setItem('JWT_token',JWT_token);
              
           }
          //  add(state,n){  
          //        state.count += n    //调试
          //      },
      
  },
  actions:{
      getdata(context){
        axios.get("http://127.0.0.1/danche/userxinxi.php?userName="+this.state.userName).then((res)=>{
             context.commit("getdata",res);
        }).catch(err=>{
           console.error(err);
        })
      }
      
  },
  modules: {
  }
})

export default store
