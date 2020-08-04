package com.hibernate.main;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import static com.hibernate.main.constants.Constants.*;

@Configuration
@ComponentScan(basePackages = {BASE_PACKAGES})
@EntityScan(basePackages = {BASE_PACKAGES})
@EnableJpaRepositories(basePackages = {BASE_PACKAGES})
public class PackageConfigImpl {

}