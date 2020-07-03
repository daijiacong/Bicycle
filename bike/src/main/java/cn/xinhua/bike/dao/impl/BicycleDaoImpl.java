package cn.xinhua.bike.dao.impl;

import cn.xinhua.bike.dao.BicycleDao;
import cn.xinhua.bike.domain.Bicycle;
import cn.xinhua.bike.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class BicycleDaoImpl implements BicycleDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid,String address) {
        //String sql = "select count(*) from tab_route where cid = ?";
        //1.定义sql模板
        String sql = "select count(*) f rom tab_bicycle where 1=1 ";
        //如果对字符串进行拼接操作，每次拼接,都会构建一个新的String对象，既耗时，又浪费空间。为了解决这一问题，可以使用`java.lang.StringBuilder`类
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(cid != 0){
            sb.append( " and cid = ? ");

            params.add(cid);//添加？对应的值
        }

        if(address != null && address.length() > 0){
            sb.append(" and address like ? ");

            params.add("%"+address+"%");
        }

        sql = sb.toString();


        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Bicycle> findByPage(int cid, int start, int pageSize, String address) {
        //String sql = "select * from tab_route where cid = ? and rname like ?  limit ? , ?";
        String sql = " select * from tab_bicycle where 1 = 1 ";
        //1.定义sql模板
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(cid != 0){
            sb.append( " and cid = ? ");

            params.add(cid);//添加？对应的值
        }

        if(address != null && address.length() > 0){
            sb.append(" and address like ? ");

            params.add("%"+address+"%");
        }
        sb.append(" limit ? , ? ");//分页条件

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);


        return template.query(sql,new BeanPropertyRowMapper<Bicycle>(Bicycle.class),params.toArray());
    }

    @Override
    public Bicycle findOne(int tasknumber) {
        String sql = "select * from tab_bicycle where tasknumber = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Bicycle>(Bicycle.class),tasknumber);
    }

    @Override
    public void save(Bicycle bicycle,int uid) {
        //1.定义sql
        String sql = "insert into tab_bicycle(brand,colour,freetime,address,price,name,phone,remarks,wechat,r_uid,cid) values(?,?,?,?,?,?,?,?,?,?,?)";
        //2.执行sql
        System.out.println("sqlBrand="+bicycle.getBrand());
        template.update(sql,bicycle.getBrand(),
                bicycle.getColour(),
                bicycle.getFreetime(),
                bicycle.getAddress(),
                bicycle.getPrice(),
                bicycle.getName(),
                bicycle.getPhone(),
                bicycle.getRemarks(),
                bicycle.getWechat(),
                uid,
                bicycle.getCid()
        );
    }

    @Override
    public void updateBl_uid(String tasknumber,int uid) {
        //1.定义sql
        String sql = "update tab_bicycle set bl_uid=? where tasknumber=?";
        //3. 执行sql
        int count = template.update(sql,uid,tasknumber);
        System.out.println(count);
    }

    @Override
    public List<Bicycle> findByUser(int cid, int uid) {
        //1.定义sql模板
        String sql = " select * from tab_bicycle where 1 = 1 and cid = ? and r_uid=? ";

        return template.query(sql,new BeanPropertyRowMapper<Bicycle>(Bicycle.class),cid,uid);

    }

    @Override
    public List<Bicycle> findByUserIndent(int cid, int uid) {
        //1.定义sql模板
        String sql = " select * from tab_bicycle where 1 = 1 and cid = ? and bl_uid=? ";

        return template.query(sql,new BeanPropertyRowMapper<Bicycle>(Bicycle.class),cid,uid);

    }
}
