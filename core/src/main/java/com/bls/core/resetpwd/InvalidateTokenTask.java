package com.bls.core.resetpwd;

import com.bls.dao.ResetPasswordTokenDao;
import com.google.common.collect.ImmutableMultimap;
import io.dropwizard.servlets.tasks.Task;

import javax.inject.Inject;
import java.io.PrintWriter;

/**
 *  A task to check {@link ResetPasswordToken}s stored in database and invalidate the expired ones.
 */
public class InvalidateTokenTask extends Task {
    private final ResetPasswordTokenDao tokenDao;
    
    @Inject
    public InvalidateTokenTask(ResetPasswordTokenDao tokenDao) {
        super("invalidateTokens");
        this.tokenDao = tokenDao;
    }

    @Override
    public void execute(ImmutableMultimap<String, String> immutableMultimap, PrintWriter printWriter) 
            throws Exception {
        tokenDao.invalidateExpired();
    }
}
