package com.dream.user.service.impl;

import com.dream.user.dao.SysPermissionDao;
import com.dream.user.entity.SysPermission;
import com.dream.user.service.SysPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysPermissionServiceImpl implements SysPermissionService {
    @Autowired
    SysPermissionDao sysPermissionDao;

    @Override
    public List<SysPermission> search() {
        return sysPermissionDao.findAll();
    }

    @Override
    public SysPermission searchById(Integer id) {
        return sysPermissionDao.findById(id).get();
    }

    @Override
    public boolean update(SysPermission sysPermission) {
        return sysPermissionDao.save(sysPermission) != null;
    }

    @Override
    public boolean delete(Integer id) {
        try {
            sysPermissionDao.deleteById(id);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean add(SysPermission sysPermission) {
        return sysPermissionDao.save(sysPermission) != null;
    }
}
