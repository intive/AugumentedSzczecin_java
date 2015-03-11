package com.rsawczyn.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
/**
 * Augmented Szczecin 2015 Application
 *
 */
public class AugmentedApplication extends Application<AugmentedConfiguration> 
{
    public static void main( String[] args ) throws Exception {
		new AugmentedApplication().run(args);
        System.out.println( "Hello World!" );
    }
	
	@Override
    public void initialize(Bootstrap<AugmentedConfiguration> bootstrap) {
    }

    @Override
    public void run(AugmentedConfiguration configuration, Environment environment) {
        final PoiResource resource = new PoiResource();
		environment.jersey().register(resource);
    }
}
