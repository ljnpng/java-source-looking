package org.example.steam;//package com.ljnpng;
//
//import java.util.function.Consumer;
//
///**
// * TODO
// */
//public class RoleService {
//
//    @Autowired
//    RoleDao roleDao;
//
//    /**
//     * 删除指定的角色
//     * @param roleId
//     */
//    public void deleteRole(Long roleId) {
//        operateRole(roleId, id -> roleDao.delete(id));
//    }
//
//    /**
//     * 锁定指定的角色
//     * @param roleId
//     */
//    public void lockRole(Long roleId) {
//        operateRole(roleId, id -> roleDao.lock(id));
//    }
//
//    public void operateRole(Long roleId, Consumer<Long> consumer) {
//        Optional<Role> oldRole = roleDao.getRole(roleId);
//        if (oldRole.isPresent()) {
//            consumer.accept(roleId);
//        } else {
//            throw new BusinessException(String.format("Cannot find the role record for id [%d]", roleId));
//        }
//
//    }
//}
