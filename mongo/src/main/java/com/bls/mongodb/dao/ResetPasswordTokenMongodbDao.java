package com.bls.mongodb.dao;

import com.bls.core.resetpwd.ResetPasswordToken;
import com.bls.dao.ResetPasswordTokenDao;
import com.bls.mongodb.core.ResetPasswordTokenMongodb;
import com.google.common.base.Optional;
import com.mongodb.DB;
import org.joda.time.DateTime;
import org.mongojack.DBQuery;

import javax.inject.Inject;
import java.util.List;

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
    public Optional<ResetPasswordToken> read(final String token) {
        ResetPasswordTokenMongodb entity = dbCollection.findOne(DBQuery.is("token", token));
        if (entity == null) return Optional.absent();
        return Optional.of(convert2coreModel(entity));
    }

    @Override
    public void expire(final ResetPasswordToken token) {
        dbCollection.removeById(String.valueOf(token.getId()));
    }
    
    @Override
    public void invalidateExpired() {
        List<ResetPasswordTokenMongodb> tokensMongodb =
                dbCollection.find(DBQuery.lessThan("expiryDate", DateTime.now().getMillis())).toArray();
        
        for (ResetPasswordTokenMongodb tokenMongodb : tokensMongodb) {
            ResetPasswordToken tokenCore = convert2coreModel(tokenMongodb);
            if (tokenCore.checkExpired()) {
                expire(tokenCore);
            }
        }
    }

}
