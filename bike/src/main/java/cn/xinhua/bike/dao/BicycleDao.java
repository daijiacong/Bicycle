package cn.xinhua.bike.dao;

import cn.xinhua.bike.domain.Bicycle;

import java.util.List;

public interface BicycleDao {

    /**
     * 根据cid查询总记录数
     */
    public int findTotalCount(int cid,String address);

    /**
     * 根据cid，start,pageSize查询当前页的数据集合
     */
    public List<Bicycle> findByPage(int cid, int start, int pageSize, String address);

    /**
     * 根据tasknumber查询
     * @param tasknumber
     * @return
     */
    public Bicycle findOne(int tasknumber);

    /**
     * 单车保存
     * @param bicycle
     */
    public void save(Bicycle bicycle,int uid);

    void updateBl_uid(String tasknumber,int uid);

    public   List<Bicycle> findByUser(int cid, int uid);

    List<Bicycle> findByUserIndent(int cid, int uid);
}
