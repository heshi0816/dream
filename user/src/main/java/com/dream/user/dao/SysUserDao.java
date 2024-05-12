package com.dream.user.dao;

import com.dream.user.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SysUserDao extends JpaRepository<SysUser,Integer>{
    SysUser findByUsername(String username);
}
