package com.dream.user.service;


import com.dream.user.entity.SysUser;

import java.util.List;

public interface SysUserService {
    List<SysUser> search();
    SysUser searchById(Integer id);
    boolean add(SysUser sysUser);
    boolean update(SysUser sysUser);
    boolean delete(Integer id);
    SysUser searchByUsername(String username);

}
