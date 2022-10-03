package com.seniorproject.foody.config;

import com.seniorproject.foody.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class MyDataRestConfig implements RepositoryRestConfigurer {

    private EntityManager entityManager;

    @Autowired // autowire JPA entity manager
    public MyDataRestConfig(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        HttpMethod[] theUnspportedActions = {HttpMethod.PUT,HttpMethod.POST,HttpMethod.DELETE};

        disableHttpMethods(Restaurant.class,config, theUnspportedActions);



        // helper method to expose id
        exposeIds(config);
    }

    private void disableHttpMethods(Class theClass,RepositoryRestConfiguration config, HttpMethod[] theUnspportedActions) {
        config.getExposureConfiguration()
                .forDomainType(theClass)
                .withItemExposure((metadata,httpMethods) -> httpMethods.disable(theUnspportedActions))
                .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(theUnspportedActions));
    }

    private void exposeIds(RepositoryRestConfiguration config){
        // expose entity ids

        // get a list of all entity classes from the entity manager
        Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
        // create an arr of the entity types
        List<Class> entityClasses = new ArrayList<>();

        //get the entity type for the entities
        for(EntityType e : entities){
            // add in the entityClasses collection
            entityClasses.add(e.getJavaType());
        }
        // expose the entity ids for the arr of entity domain type
        Class[] domainTypes = entityClasses.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
