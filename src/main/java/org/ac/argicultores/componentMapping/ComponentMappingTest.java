package org.ac.argicultores.componentMapping;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

public class ComponentMappingTest {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("component_mapping");

    public static void main(String[] args) {

        Student student = new Student();
        ComponentMappingTest cmt = new ComponentMappingTest();

        student.setName("Igor");

        Address address = new Address();
        address.setCity("Porto");
        address.setStreet("Rua do Zambeze");
        address.setZipCode("4052-301");

        student.setAddress(address);

        cmt.save(student);
        Student fetch = cmt.fetch(1);
        System.out.println(fetch);
    }


    private void save(Student student){

        EntityManager em = emf.createEntityManager();

        try{

            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();

        } catch (RollbackException ex){

            em.getTransaction().rollback();

        } finally {
            em.close();
        }

    }

    private Student fetch(Integer id){

        EntityManager em = emf.createEntityManager();

        try{

            return em.find(Student.class,id);

        } finally {
            em.close();
        }
    }
}
