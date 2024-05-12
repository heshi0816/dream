package com.dream.user.service.impl;


import com.dream.user.dao.SysUserDao;
import com.dream.user.entity.SysUser;
import com.dream.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Autowired
    SysUserDao sysUserDao;

    @Override
    public List<SysUser> search() {
        return sysUserDao.findAll();
    }

    @Override
    public boolean add(SysUser sysUser) {
      //  sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        SysUser newUser =sysUserDao.save(sysUser);
        return newUser!=null;
    }
    @Override
    public boolean update(SysUser sysUser) {
      //  sysUser.setPassword(MD5Util.encode(sysUser.getPassword()));
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        SysUser newUser =sysUserDao.save(sysUser);
        return newUser!=null;
    }
    @Override
    public boolean  delete(Integer id) {
        try {
            sysUserDao.deleteById(id);
        }catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public SysUser searchById(Integer id) {
        return sysUserDao.findById(id).get();
    }


    @Override
    public SysUser searchByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }
}
