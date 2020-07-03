package cn.xinhua.bike.web.servlet;

import cn.xinhua.bike.domain.ResultInfo;
import cn.xinhua.bike.domain.User;
import cn.xinhua.bike.service.UserService;
import cn.xinhua.bike.service.impl.UserServiceImpl;
import cn.xinhua.bike.util.JedisUtil;
import cn.xinhua.bike.util.UuidUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import redis.clients.jedis.Jedis;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@WebServlet("/user/*") // /user/add /user/find
public class UserServlet extends BaseServlet {


    //声明UserService业务对象
    private UserService service = new UserServiceImpl();

    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }

        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();

        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用service完成注册
        //UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if(flag){
            //注册成功
            info.setFlag(true);
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }

        //将info对象序列化为json
       writeValue(info,response);

    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证校验
        String check = request.getParameter("check");
        //从sesion中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//为了保证验证码只能使用一次
        //比较
        if(checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)){
            //验证码错误
            ResultInfo info = new ResultInfo();
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码错误");
            //将info对象序列化为json
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        //1.获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用Service查询
       // UserService service = new UserServiceImpl();
        User u  = service.login(user);

        ResultInfo info = new ResultInfo();

        //4.判断用户对象是否为null
        if(u == null){
            //用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        //5.判断用户是否激活
        if(u != null && !"Y".equals(u.getStatus())){
            //用户尚未激活
            info.setFlag(false);
            info.setErrorMsg("您尚未激活，请激活");
        }
        //6.判断登录成功
        if(u != null && "Y".equals(u.getStatus())){
            //当前登录的用户信息存到session里面,当需要用到当前登录的用户信息聪session中拿
            request.getSession().setAttribute("user",u);
            //6.生成Token令牌
            String token= UuidUtil.getUuid();
            info.setFlag(true);
            //6.1把生成Token令牌传给前端
            info.setData(token);

            //6.2把生成Token令牌传给前端存储到redis数据库
            //1.1获取jedis客户端
            Jedis jedis = JedisUtil.getJedis();
            jedis.set(String.valueOf(token), String.valueOf(u));
            //归还连接
            jedis.close();

        }

        //响应数据
        ObjectMapper mapper = new ObjectMapper();

        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    /**
     * 查询单个对象
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
       User user = (User)request.getSession().getAttribute("user");  //这里用户头像不是最新的
        User u  = service.findOne(user.getUid());
        //将user写回客户端
       writeValue(u,response);
    }

    /**
     * 激活功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void active(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取激活码
        String code = request.getParameter("code");
        if(code != null){
            //2.调用service完成激活
            //UserService service = new UserServiceImpl();
            boolean flag = service.active(code);

            //3.判断标记
            String msg = null;
            if(flag){
                //激活成功
                msg = "激活成功，请<a href='login.html'>登录</a>";
            }else{
                //激活失败
                msg = "激活失败，请联系管理员!";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    /**
     * 上传表单信息 包含文件
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     //上传
        try {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        //判断前台的form是否有multipart属性
        if (isMultipart){
            //采用默认临界值和系统临时文件夹构造文件项工厂对象。
            FileItemFactory factory=new DiskFileItemFactory();
            //创建一个上传工具，指定使用缓存区与临时文件存储位置.
            ServletFileUpload upload= new ServletFileUpload(factory);
            /*它是用于解析request对象，得到所有上传项.每一个FileItem就相当于一个上传项.
            通过parseRequest获取form中的所有请求字段，并保持到items集合中*/
            List<FileItem> items = upload.parseRequest(request);
            //遍历items中的数据（sno，sname，spicture）
            //Iterator迭代器遍历集合中的所有元素
            Iterator<FileItem> iter =items.iterator();
            while (iter.hasNext()){
                FileItem item =iter.next();
                //System.out.println(item);
                String itemName=item.getFieldName();
                int sno=-1;
                String sname=null;
                //判断前台字段是普通form表单字段，还是文件字段  Field（普通）
                if (item.isFormField()){
                    //根据name属性判断item是sno，sname属性
                    if (itemName.equals("sno")){
                        sno=Integer.parseInt(item.getString("UTF-8"));
                    }else if(item.equals("sname")){
                        sname = item.getString("UTF-8");
                    }else {
                        System.out.println("其他字段..........");
                    }
                    //spicture
                }else {
                   //文件上传
                    // 文件getFieldName是获取 普通表单字段的name值
                    //getName()是获取文件名

              //      String fileName = item.getName();
               //     System.out.println(fileName);

                    //获取文件内容 并上传
                    //定义文件路径：指定上传的位置(服务器路径)
                    //获取服务器路径：G:\\Tomcat\\apache-tomcat-8.5.31\\upload  动态获取
                   // String path = request.getSession().getServletContext().getRealPath("upload");
                    //System.out.println(path);

                   String path = "E:/前端开发/Vue练习/danche/src/img/";

                   //aliyun
                   // String path = "C:\\software\\tomcat\\apache-tomcat-8.5.53\\webapps\\ROOT\\dist\\img\\";
                    String fileName=UuidUtil.getUuid()+".jpg";

                    File file = new File(path,fileName);
                    //上传
                    //item是文件的内容
                    item.write(file);
                    System.out.println(fileName+"上传成功！");
                    //拼接相对路径
                    String uavatar ="img/"+fileName;
                    //图片相对路径保存到数据库
                    User user = (User)request.getSession().getAttribute("user");
                    boolean flag = service.addUserAvatar(uavatar,user.getUid());

                    //返回文件的路径
                   // response.getWriter().write(uavatar);
                    //方法结束了
                    return ;

                }
            }
             }
        }catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
