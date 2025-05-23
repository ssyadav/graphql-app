package com.satya.graphql.lec10.config;

import com.satya.graphql.lec10.dto.FruitDto;
import graphql.schema.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.ClassNameTypeResolver;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class TypeResolverConfig {

  @Bean
  public RuntimeWiringConfigurer configurer(TypeResolver resolver) {
    return c -> c.type("Product", b -> b.typeResolver(resolver));
  }

  @Bean
  public TypeResolver typeResolver() {
    ClassNameTypeResolver resolver = new ClassNameTypeResolver();
    resolver.addMapping(FruitDto.class, "Fruit");
    return resolver;
  }
}
