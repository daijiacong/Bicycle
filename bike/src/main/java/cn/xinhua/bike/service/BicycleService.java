package cn.xinhua.bike.service;

import cn.xinhua.bike.domain.PageBean;
import cn.xinhua.bike.domain.Bicycle;

/**
 * 线路Service
 */
public interface BicycleService {

    /**
     * 根据类别进行分页查询
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Bicycle> pageQuery(int cid, int currentPage, int pageSize, String address);

    /**
     * 根据tasknumber查询
     * @param tasknumber
     * @return
     */
    public Bicycle findOne(String tasknumber);

    /**
     * 添加单车信息
     * @param bicycle
     * @return
     */
    boolean addOne(Bicycle bicycle,int uid);

    boolean apdateBuyorlease(String tasknumber,int uid);

    public  PageBean<Bicycle> findAboutUser(int cid, int uid);

    PageBean<Bicycle> findUserIndent(int cid, int uid);

}
