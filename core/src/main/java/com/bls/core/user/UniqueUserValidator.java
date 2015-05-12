package com.bls.core.user;

import com.bls.dao.UserDao;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserValidator implements ConstraintValidator<UniqueUser, User> {
    
    // FIXME field injection doesn't work ATM, userDao == null
    @Inject
    private UserDao userDao;

    @Override
    public void initialize(UniqueUser uniqueUser) {
    }

    @Override
    public boolean isValid(User user, ConstraintValidatorContext constraintValidatorContext) {
        if (userDao == null) return false;
        return !userDao.findByEmail(user.getEmail()).isPresent();
    }
}
