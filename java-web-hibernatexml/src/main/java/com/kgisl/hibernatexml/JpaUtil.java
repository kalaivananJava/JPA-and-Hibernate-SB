package com.kgisl.hibernatexml;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
 
public class JpaUtil {
    private static EntityManagerFactory entityManagerFactory;
 
    public static EntityManagerFactory getEntityManagerFactory() {
        if (entityManagerFactory == null) {
            try {
                // Create the EntityManagerFactory from persistence.xml
                entityManagerFactory = Persistence.createEntityManagerFactory("SimpleJPAProjectPU");
            } catch (Exception e) {
                e.printStackTrace();
                if (entityManagerFactory != null) {
                    entityManagerFactory.close();
                }
            }
        }
        return entityManagerFactory;
    }
 
    public static void shutdown() {
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    public static void main(String[] args) {
        getEntityManagerFactory();
    }
}
