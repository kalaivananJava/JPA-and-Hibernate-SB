package com.kgisl.hibernatexml;
import java.util.List;
 
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
 
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
 
        Book book = new Book(1,"Core Java", "Learn Core Java with Coding Examples");
        Book book1 = new Book(2,"Learn Hibernate", "Learn Hibernate with building projects");
 
        EntityManager em = null;
        EntityTransaction transaction = null;
 
        try {
            System.out.println("hello");
            em = Persistence.createEntityManagerFactory("SimpleJPAProjectPU").createEntityManager();
            // em=JpaUtil.getEntityManagerFactory().createEntityManager();

            transaction = em.getTransaction();
            System.out.println("hello");
            System.out.println(transaction);
            // Start a transaction
            transaction.begin();
 
            // Save the book objects
            em.persist(book);
            em.persist(book1);
 
            // Commit transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
           e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
 
        try {
            em = Persistence.createEntityManagerFactory("SimpleJPAProjectPU").createEntityManager();
            List<Book> books = em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
            books.forEach(b -> System.out.println("Print book name: " + b.getName()));
        } catch (Exception e) {
           // e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
 