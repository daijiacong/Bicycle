package cn.xinhua.bike.web.servlet;

import cn.xinhua.bike.domain.Bicycle;
import cn.xinhua.bike.domain.PageBean;
import cn.xinhua.bike.domain.ResultInfo;
import cn.xinhua.bike.domain.User;
import cn.xinhua.bike.service.BicycleService;
import cn.xinhua.bike.service.FavoriteService;
import cn.xinhua.bike.service.impl.BicycleServiceImpl;
import cn.xinhua.bike.service.impl.FavoriteServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/BicycleServlet/*")
public class BicycleServlet extends BaseServlet {
    private BicycleService bicycleService = new BicycleServiceImpl();
    private FavoriteService favoriteService = (FavoriteService) new FavoriteServiceImpl();
    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");
        //接受s_address 地址名称
        String address = request.getParameter("address");
        if (address!=null) {
            address = new String(address.getBytes("iso-8859-1"), "utf-8");
        }//System.out.println("线路名称"+s_address);
        //System.out.println(cidStr);
        int cid = 0;//类别id
        //2.处理参数
        if(cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)){
            cid = Integer.parseInt(cidStr);
        }
        int currentPage = 0;//当前页码，如果不传递，则默认为第一页
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }

        int pageSize = 0;//每页显示条数，如果不传递，默认每页显示5条记录
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 5;
        }

        //3. 调用service查询PageBean对象
        PageBean<Bicycle> pb = bicycleService.pageQuery(cid, currentPage, pageSize,address);
        //System.out.println("service查询PageBean对象"+pb);
        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);

    }

    /**
     * 根据tasknumber查询该单车的详细信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1.接收id
        String tasknumber = request.getParameter("tasknumber");
        //2.调用service查询route对象
        Bicycle route = bicycleService.findOne(tasknumber);
        //3.转为json写回客户端
        writeValue(route,response);
    }

    /**
     * 添加一辆单车信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void andOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收参数
        String brand = request.getParameter("brand");
        String colour = request.getParameter("colour");
        String freetime = request.getParameter("freetime");
        String address = request.getParameter("address");
        String price = request.getParameter("price");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String remarks = request.getParameter("remarks");
        String wechat = request.getParameter("wechat");
        //解决中文参数乱码
        brand = new String(brand.getBytes("iso-8859-1"), "utf-8");
        colour = new String(colour.getBytes("iso-8859-1"), "utf-8");
        freetime = new String(freetime.getBytes("iso-8859-1"), "utf-8");
        address = new String(address.getBytes("iso-8859-1"), "utf-8");
        price = new String(price.getBytes("iso-8859-1"), "utf-8");
        name = new String(name.getBytes("iso-8859-1"), "utf-8");
        phone = new String(phone.getBytes("iso-8859-1"), "utf-8");
        remarks = new String(remarks.getBytes("iso-8859-1"), "utf-8");
        wechat = new String(wechat.getBytes("iso-8859-1"), "utf-8");


        //System.out.println(brand);
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        Bicycle bicycle = new Bicycle();
        try {
            BeanUtils.populate(bicycle,map);
            //解决中文参数乱码
            bicycle.setBrand(brand);
            bicycle.setColour(colour);
            bicycle.setFreetime(freetime);
            bicycle.setAddress(address);
            bicycle.setPrice(price);
            bicycle.setName(name);
            bicycle.setPhone(Integer.parseInt(phone));
            bicycle.setRemarks(remarks);
            bicycle.setWechat(wechat);

           // bicycle.getBrand();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //从session中获取登录用户
        User user = (User)request.getSession().getAttribute("user");
        //调用bicycleService完成添加
        Boolean flag= bicycleService.addOne(bicycle,user.getUid());
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if(flag){
            //添加成功
            info.setFlag(true);
        }else{
            //添加失败
            info.setFlag(false);
            info.setErrorMsg("添加失败!");
        }

        //3.转为json写回客户端
        writeValue(info,response);
    }

    /**
     * 购买或租用这辆自行车  apdate bl_uid
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void apdateBuyorlease(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tasknumber = request.getParameter("tasknumber");
        //从session中获取登录用户
        User user = (User)request.getSession().getAttribute("user");
        Boolean flag= bicycleService.apdateBuyorlease(tasknumber,user.getUid());
    }

    /**
     * 查询以当前用户发布的单车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findUserLease(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String cidStr = request.getParameter("cid");
        int cid = Integer.parseInt(cidStr);
        User user = (User)request.getSession().getAttribute("user");
        //3. 调用service查询PageBean对象
        PageBean<Bicycle> pb = bicycleService.findAboutUser(cid,user.getUid());
        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }

    /**
     * 查询以当前用户用于或购买过的单车
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findUserIndent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接受参数
        String cidStr = request.getParameter("cid");
        int cid = Integer.parseInt(cidStr);
        User user = (User)request.getSession().getAttribute("user");
        //3. 调用service查询PageBean对象
        PageBean<Bicycle> pb = bicycleService.findUserIndent(cid,user.getUid());
        //4. 将pageBean对象序列化为json，返回
        writeValue(pb,response);
    }




    /**
     * 判断当前登录用户是否收藏过该线路
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取线路id
        String rid = request.getParameter("rid");

        //2. 获取当前登录的用户 user
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if(user == null){
            //用户尚未登录
            uid = 0;
        }else{
            //用户已经登录
            uid = user.getUid();
        }

        //3. 调用FavoriteService查询是否收藏
        boolean flag = favoriteService.isFavorite(rid, uid);

        //4. 写回客户端
        writeValue(flag,response);
    }

    /**
     * 添加收藏
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取线路rid
        String rid = request.getParameter("rid");
        //2. 获取当前登录的用户
        User user = (User) request.getSession().getAttribute("user");
        int uid;//用户id
        if(user == null){
            //用户尚未登录
            return ;
        }else{
            //用户已经登录
            uid = user.getUid();
        }


        //3. 调用service添加
        favoriteService.add(rid,uid);

    }


}
