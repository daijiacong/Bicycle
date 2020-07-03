package cn.xinhua.bike.service.impl;


import cn.xinhua.bike.dao.BicycleDao;
import cn.xinhua.bike.dao.FavoriteDao;
import cn.xinhua.bike.dao.RouteImgDao;
import cn.xinhua.bike.dao.impl.BicycleDaoImpl;
import cn.xinhua.bike.dao.impl.FavoriteDaoImpl;
import cn.xinhua.bike.dao.impl.RouteImgDaoImpl;
import cn.xinhua.bike.domain.Bicycle;
import cn.xinhua.bike.domain.PageBean;
import cn.xinhua.bike.service.BicycleService;

import java.util.List;

public class BicycleServiceImpl implements BicycleService {
    private BicycleDao bicycleDao = new BicycleDaoImpl();

    private RouteImgDao routeImgDao = new RouteImgDaoImpl();

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();

    @Override
    public PageBean<Bicycle> pageQuery(int cid, int currentPage, int pageSize, String address ) {
        //封装PageBean
        PageBean<Bicycle> pb = new PageBean<Bicycle>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        
        //设置总记录数
        int totalCount = bicycleDao.findTotalCount(cid,address);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Bicycle> list = bicycleDao.findByPage(cid,start,pageSize,address);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);


        return pb;
    }


    @Override
    public Bicycle findOne(String tasknumber) {
        //1.根据tasknumber任务号去route表中查询route对象
        Bicycle route = bicycleDao.findOne(Integer.parseInt(tasknumber));

        //2.根据route的id 查询图片集合信息
        //List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());
        //2.2将集合设置到route对象
        //route.setRouteImgList(routeImgList);
        //3.根据route的sid（商家id）查询商家对象

        //4. 查询收藏次数
       /* int count = favoriteDao.findCountByRid(route.getRid());
        route.setCount(count);*/

        return route;
    }

    @Override
    public boolean addOne(Bicycle bicycle,int uid) {

      bicycleDao.save(bicycle,uid);

        return true;
    }

    @Override
    public boolean apdateBuyorlease(String tasknumber,int uid) {
        bicycleDao.updateBl_uid(tasknumber,uid);
        return true;
    }

    @Override
    public PageBean<Bicycle> findAboutUser(int cid, int uid) {
        //封装PageBean
        PageBean<Bicycle> pb = new PageBean<Bicycle>();

        List<Bicycle> list = bicycleDao.findByUser(cid, uid);
        pb.setList(list);

        return pb;
    }

    @Override
    public PageBean<Bicycle> findUserIndent(int cid, int uid) {
        //封装PageBean
        PageBean<Bicycle> pb = new PageBean<Bicycle>();

        List<Bicycle> list = bicycleDao.findByUserIndent(cid, uid);
        pb.setList(list);

        return pb;
    }
}
