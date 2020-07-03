import Vue from 'vue'
import VueRouter from 'vue-router'

const homepage = () => import(/* webpackChunkName: "homePage" */ '../components/homepage')
const release = () => import(/* webpackChunkName: "danChe" */ '../components/release')
const trends = () => import(/* webpackChunkName: "dongtai" */ '../components/trends')
const my = () => import(/* webpackChunkName: "my" */ '../components/my')
// const kehuishouInfo = () => import(/* webpackChunkName: "kehuishouinfo" */ '../components/homePage/kehuishouInfo')
// const ganlajiInfo = () => import(/* webpackChunkName: "ganlajiinfo" */ '../components/homePage/ganlajiInfo')
// const shilajiInfo = () => import(/* webpackChunkName: "shilajiinfo" */ '../components/homePage/shilajiInfo')
const yangche = () => import(/* webpackChunkName: "youhailajiinfo" */ '../components/homePage/yangche')
const  detail= () => import(/* webpackChunkName: "detail" */ '../components/trends/detail')
const  register= () => import(/* webpackChunkName: "zuce" */ '../components/register')
const  login= () => import(/* webpackChunkName: "denglu" */ '../components/login')

Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'hash',
  routes:[
  {
   path: '/homepage',
   name: 'homepage',
   component:homepage,
   meta:{"title":"首页","JianTouShow":false,"TopShow":true,"BottomShow":true}
},{
  path: '/release',
  name: 'release',
  component:release,
  meta:{"title":"发布","JianTouShow":false,"TopShow":true,"BottomShow":true}
},{
  path: '/trends',
  name: 'trends',
  component:trends,
  meta:{"title":"动态","JianTouShow":false,"TopShow":true,"BottomShow":true}
},
{
  path: '/my',
  name: 'my',
  component:my,
  meta:{"title":"我的","JianTouShow":false,"TopShow":false,"BottomShow":true}
},
{
  path: '/homePage/yangche',
  name: 'yangche',
  component:yangche,
  meta:{"title":"可回收","JianTouShow":true,"TopShow":true,"BottomShow":false}
},
{
  path: '/trends/detail/:tasknumber',
  name: 'detail',
  component:detail,
  meta:{"title":"购买详情","JianTouShow":true,"TopShow":true,"BottomShow":false}
},
{
  path: '/register',
  name: 'register',
  component:register,
  meta:{"title":"注册","JianTouShow":false,"TopShow":false,"BottomShow":false}
},{
  path: '/login',
  name: 'login',
  component:login,
  meta:{"title":"登陆","JianTouShow":false,"TopShow":false,"BottomShow":false}
}]
});

//导航守卫
// 使用 router.beforeEach 注册一个全局前置守卫，判断用户是否登陆
router.beforeEach((to, from, next) => {
        // to 去哪个路由
      // from 从哪个路由
      // next() true 允许进入 / false 不允许 / '/user' 进入user地址
      // beforeRouteEnter守卫不能访问this，通过 `vm` 访问组件实例 next(vm=>{})
        //debugger
  if (to.path === '/login'||to.path === '/register') {
    next();
  } else {
    let token = sessionStorage.getItem('JWT_token');
    if (!token) {
      next('/login');
    } else {
      next();
    }
  }
  
});

// eslint-disable-next-line no-new
// const router = new VueRouter({
//   mode: 'history',
//   base: process.env.BASE_URL,
//   router
// })

export default router
