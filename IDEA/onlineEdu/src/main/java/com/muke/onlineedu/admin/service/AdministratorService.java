package com.muke.onlineedu.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.muke.onlineedu.admin.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministratorService extends IService<Admin> {
    boolean loginById(int id,String gmPassword);
    int stateCheck(int gmId);
    Admin getUser();
    void setUser(Admin admin);
    void saveGMChanges(String Email, String Tel);
    void updateUser();
    boolean changePassword(String newPassword,String oldPassword);
    List<Admin> getPartAccounts(int gmPower, String key, int startPage, int pageSize);
    List<Admin> getAllAccounts(int gmPower, String key);
    void updatePower(int sign,int id);
    String getAccountStatusStr(int id);
    int CheckAccountStatus(int gmAccountStatus);
    List<Admin> batchAddAdminAccount(int quantity,int gmPower);
}
