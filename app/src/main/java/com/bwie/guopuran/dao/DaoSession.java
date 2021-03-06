package com.bwie.guopuran.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.bwie.guopuran.dao.UserDao;

import com.bwie.guopuran.dao.UserDaoDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoDaoConfig;

    private final UserDaoDao userDaoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoDaoConfig = daoConfigMap.get(UserDaoDao.class).clone();
        userDaoDaoConfig.initIdentityScope(type);

        userDaoDao = new UserDaoDao(userDaoDaoConfig, this);

        registerDao(UserDao.class, userDaoDao);
    }
    
    public void clear() {
        userDaoDaoConfig.clearIdentityScope();
    }

    public UserDaoDao getUserDaoDao() {
        return userDaoDao;
    }

}
