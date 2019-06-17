package com.davids.org.crudhql.dao;

import com.davids.org.crudhql.model.Employee;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeHQLImpl implements EmployeeDAO{

    private EntityManager entityManager;

    @Autowired
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public List<Employee> getAllEmployees() {
        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("SELECT employee FROM Employee employee");
        List list = query.getResultList();
        return list;
    }

    @Override
    public Employee getEmployeeById(Integer id) {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        Query query = currentSession.createQuery("SELECT employee FROM Employee employee WHERE employee.id =: idToSearch");
        query.setParameter("idToSearch", id);
        return (Employee) query.getSingleResult();
    }

    @Override
    public void save(Employee employee) {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.merge(employee);
    }

    @Override
    public void delete(Integer id) {

        //get the current hibernate session
        Session currentSession = entityManager.unwrap(Session.class);

        try {
            Query query = currentSession.createQuery("DELETE FROM Employee employee WHERE employee.id =: idToDelete");
            query.setParameter("idToDelete", id);
            query.executeUpdate();
        }
        finally {
            if (currentSession != null) {
                currentSession.close();
            }
        }

    }
}