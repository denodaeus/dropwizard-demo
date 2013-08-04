package org.fabianware.dropwizarddemo.service;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

import org.fabianware.dropwizarddemo.conf.HelloWorldConfiguration;
import org.fabianware.dropwizarddemo.healthchecks.TemplateHealthCheck;
import org.fabianware.dropwizarddemo.resources.HelloWorldResource;

public class HelloWorldService extends Service<HelloWorldConfiguration> {
  
  public static final String CONFIGURATION_NAME = "hello-world";

  public static void main(String[] args) throws Exception {
    new HelloWorldService().run(args);
  }

  @Override
  public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    bootstrap.setName(CONFIGURATION_NAME);
  }

  @Override
  public void run(HelloWorldConfiguration configuration, 
      Environment environment) {
    final String template = configuration.getTemplate();
    final String defaultName = configuration.getDefaultName();
    environment.addResource(new HelloWorldResource(template, defaultName));
    environment.addHealthCheck(new TemplateHealthCheck(template));
  }
}
