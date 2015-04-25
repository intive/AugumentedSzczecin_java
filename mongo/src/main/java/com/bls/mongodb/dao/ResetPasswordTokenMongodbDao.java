package com.bls.mongodb.dao;

import com.bls.core.user.ResetPasswordToken;
import com.bls.dao.ResetPasswordTokenDao;
import com.bls.mongodb.core.ResetPasswordTokenMongodb;
import com.mongodb.DB;
import org.mongojack.DBQuery;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * ResetpwdToken mongodb data provider
 */
public class ResetPasswordTokenMongodbDao extends CommonMongodbDao<ResetPasswordTokenMongodb,
        ResetPasswordToken<String>, String> implements ResetPasswordTokenDao<ResetPasswordToken<String>> {


    @Inject
    public ResetPasswordTokenMongodbDao(final DB db) {
        super(db);
    }

    @Override
    protected Class<ResetPasswordTokenMongodb> getMongodbModelType() {
        return ResetPasswordTokenMongodb.class;
    }

    @Override
    public Optional<String> read(final String token) {
        ResetPasswordTokenMongodb entity = dbCollection.findOne(DBQuery.is("token", token));
        return entity != null ? Optional.of(entity.getCoreEntity().getId()) : Optional.empty();
    }

    @Override
    public void expire(final ResetPasswordToken token) {
        dbCollection.removeById(String.valueOf(token.getId()));
    }
    
    @Override
    public void invalidateExpired() {
        List<ResetPasswordToken<String>> tokens = findAll();
        for (ResetPasswordToken token : tokens) {
            if (token.checkExpired()) {
                expire(token);
            }
        }
    }
}
