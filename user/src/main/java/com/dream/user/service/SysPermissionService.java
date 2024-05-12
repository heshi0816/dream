package com.dream.user.service;


import com.dream.user.entity.SysPermission;

import java.util.List;

public interface SysPermissionService {
    List<SysPermission> search();

    SysPermission searchById(Integer id);

    boolean update(SysPermission sysPermission);

    boolean delete(Integer id);

    boolean add(SysPermission sysPermission);
}
