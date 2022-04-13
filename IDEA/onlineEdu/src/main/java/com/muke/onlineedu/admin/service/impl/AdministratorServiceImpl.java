package com.muke.onlineedu.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.muke.onlineedu.admin.dao.AdministratorDao;
import com.muke.onlineedu.admin.entity.Admin;
import com.muke.onlineedu.admin.service.AdministratorService;
import com.muke.onlineedu.common.service.AccountStatusService;
import com.muke.onlineedu.common.service.GMPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Service("administratorService")
public class AdministratorServiceImpl extends ServiceImpl<AdministratorDao, Admin> implements AdministratorService {
    static List<Admin> resultest = null;
    private Admin user;

    @Autowired
    private GMPowerService gmPowerService;
    @Autowired
    private AccountStatusService accountStatusService;

    public static String md5(String str) {  // md5 加密
        byte[] digest = null;
        MessageDigest md5;
        String md5Str = null;
        try {
            md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }

    public static int getRandom() {
        double max = 99999999, min = 100000;
        int password = (int) (Math.random() * (max - min) + min);
        return password;
    }

    @Override
    public boolean loginById(int id, String gmPassword) {  // id 登录
        List<Admin> result;
        boolean state;
        result = baseMapper.selectById(id, md5(gmPassword));
        state = setUserData(result);
        return state;
    }

    //    public boolean loginTel(int gmTel,String gmPassword){  // 电话号码登录
//        String sql="SELECT gmId,gmEmail,gmTel,gmPower,gmAccountStatus from administrator WHERE gmTel="+gmTel+" AND gmPassword=\""+md5(gmPassword)+"\"";
//        Statement stmt;
//        ResultSet result = null;
//        boolean state=false;
//        try {
//            stmt=CONN.createStatement();
//            result=stmt.executeQuery(sql);
//            state=setUserData(result);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return state;
//    }
//    public boolean loginEmail(String gmEmail,String gmPassword){  // 邮箱登录
//        String sql="SELECT gmId,gmEmail,gmTel,gmPower,gmAccountStatus from administrator WHERE gmEmail=\""+gmEmail+"\" AND gmPassword=\""+md5(gmPassword)+"\"";
//        Statement stmt;
//        ResultSet result = null;
//        boolean state=false;
//
//        try {
//            stmt=CONN.createStatement();
//            result=stmt.executeQuery(sql);
//            state=setUserData(result);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return state;
//    }
    private void activation(int gmId) {
        baseMapper.updateGMAccountStatus(1, gmId);
    }

    @Override
    public int stateCheck(int gmId) {  // 账号状态检查,返回值为数字,需根据字典表进一步判断
        int gmAccountStatus = baseMapper.getGMAccountStatus(gmId);
        return gmAccountStatus;

    }

    private boolean setUserData(List<Admin> result) {  // 将数据写入 user 中
        boolean sign = false;
        this.user = new Admin();
        this.user.setGmAccountStatus(916);  // 默认登录失败,账号或密码错误
        if (result.size() == 1) {
            sign = true;
            this.user = result.get(0);
            this.user.setGmPowerClass(gmPowerService.findByPowerNumber(this.user.getGmPower()));
            if (this.user.getGmAccountStatus() == 4) {  //若账号之前为未激活则登录后更改为正常
                this.activation(this.user.getGmId());
            }
        }
        return sign;
    }

    @Override
    public void setUser(Admin user) {  // set user
        this.user = user;
    }
    @Override
    public Admin getUser() {  // get user
        return this.user;
    }
    public String getAccountStatusStr(int gmId) {  // 获取账号状态的中文含义(数据库中是以数字标识权限,需要通过字典表来获取其中文含义)
        return baseMapper.getAccountStatusStr(gmId);
    }
    @Override
    public void updateUser() {  // 更新 user 的属性
        List<Admin> result = baseMapper.selectBy(this.user.getGmId());
        setUserData(result);
    }

    @Override
    public void saveGMChanges(String gmEmail, String gmTel) {  // 更改信息
        int gmId=this.user.getGmId();
        baseMapper.updateGMInfo(gmEmail,gmTel,gmId);
    }
    @Override
    public boolean changePassword(String newPassword,String oldPassword) {  // 更改密码
        int gmId=this.user.getGmId();
        List<Admin> state = baseMapper.selectById(gmId, md5(oldPassword));  // 验证密码是否正确(通过"id登录"验证)
        boolean sign=false;
        if(state.size()==1){
            sign=true;
        }
        if(sign) {
            baseMapper.changePassword(md5(newPassword),gmId);
        }else {
            System.out.println("密码错误.");
        }
        return sign;
    }
    @Override
    public List<Admin> getPartAccounts(int gmPower,String key,int startPage,int pageSize){
        List<Admin> adminList = baseMapper.getPartAccounts(gmPower, key, startPage, pageSize);
        for (Admin admin:adminList){
            admin.setGmPowerClass(gmPowerService.findByPowerNumber(admin.getGmPower()));
            admin.setAccountStatus(accountStatusService.findBy(admin.getGmAccountStatus()));
        }
        return adminList;
    }

    @Override
    public List<Admin> getAllAccounts(int gmPower, String key){
        return baseMapper.getAllAccounts(gmPower, key);
    }


//    public int getAdminRow(){  // 获取表数据的总行数
//        String sql="select * from administrator";
//        int row=0;
//        Statement stmt;
//        try {
//            stmt=CONN.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
//            ResultSet result=stmt.executeQuery(sql);
//            result.last();
//            row = result.getRow();
//            System.out.println(",row:"+row);
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return row;
//    }
//    public List<Admin> getAllAccounts(int gmPower,String key,int startPage,int pageSize){  // 获取所有权限低于此管理员的管理员信息
//        List<Admin> adminList=new ArrayList<>();
//        String sql="SELECT gmId,gmEmail,gmTel,gmPower,gmAccountStatus "
//                + "from administrator WHERE gmPower<"+gmPower +
//                " AND (gmId LIKE '%"+key+"%' "
//                + "OR gmEmail LIKE '%"+key+"%' "
//                + "OR gmTel LIKE '%"+key+"%' "
//                + "OR gmPower LIKE '%"+key+"%') "
//                + "limit "+startPage+","+pageSize,
//                gmEmail,gmPowerStr,accountStatusStr,gmTel;
//        int gmId,gmAccountStatus;
//        Statement stmt;
//        try {
//            stmt=CONN.createStatement();
//            ResultSet result=stmt.executeQuery(sql);
//            while(result.next()) {
//                gmId=result.getInt("gmId");
//                gmEmail=result.getString("gmEmail");
//                gmTel=result.getString("gmTel");
//                gmPower=result.getInt("gmPower");  // 权限-数字 已经在参数中定义了
//                gmPowerStr=this.getPowerStr(gmPower);  // 权限-中文
//                gmAccountStatus=result.getInt("gmAccountStatus");  // 账号状态-数字
//                accountStatusStr=this.getAccountStatusStr(gmId);  // 账号状态-中文
//                Admin admin=new Admin(gmId,gmEmail,gmTel,gmPower,gmPowerStr,gmAccountStatus,accountStatusStr);
//                adminList.add(admin);
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return adminList;
//    }
//
    @Override
    public void updatePower(int sign,int id) {  // 更改低级权限的管理员账号状态
        baseMapper.updatePower(sign,id);
    }

    @Override
    public List<Admin> batchAddAdminAccount(int quantity,int gmPower) { //批量注册指定等级管理员账号
        List<Admin> adminList=new ArrayList<>();
        for(int i=0;i<quantity;i++){
            String gmPassword=String.valueOf(getRandom());
            String password=md5(gmPassword);
            Admin admin=new Admin();
            admin.setGmPassword(password);
            admin.setGmInitPassword(gmPassword);
            admin.setGmPower(gmPower);
            admin.setGmAccountStatus(4);
            baseMapper.addGM(admin);

            admin.setGmId(admin.getGmId());
            adminList.add(admin);
        }
        return adminList;
    }

//    public int generateKeys(PreparedStatement prep) {  //获取刚插入数据的自增 id
//        ResultSet result;
//        try {
//            result = prep.getGeneratedKeys();
//            if(result.next()) {
//                return result.getInt(1);
//            }
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        return -1;
//    }


    @Override
    public int CheckAccountStatus(int gmAccountStatus) { // 转换,之前没与前端协同好
        if(gmAccountStatus==0) {
            return 911;
        }else if(gmAccountStatus==1) {
            return 915;
        }else if(gmAccountStatus==2) {
            return 913;
        }else if(gmAccountStatus==3) {
            return 914;
        }else if(gmAccountStatus==4) {
            return 912;
        }else{
            return 916;
        }
    }
}
