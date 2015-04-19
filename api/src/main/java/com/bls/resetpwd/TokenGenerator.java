package com.bls.resetpwd;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Marcin Podlodowski on 28.04.15.
 */
public class TokenGenerator {
    public static String getNew() {
        return new BigInteger(130, new SecureRandom()).toString(32);
    }
}
