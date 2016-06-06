package br.com.financemate.repository;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class AbstractRepository<T> {

    @PersistenceContext
    private EntityManager em;
    protected final Class clazz;

    
    public AbstractRepository(Class clazz) {
        this.clazz = clazz;
    }

    public T create(T entity) {
        em.persist(entity);
        return entity;
    }

    public T update(T entity) {
        return em.merge(entity);
    }

    public void remove(Long id) {
        em.remove(em.getReference(clazz, id));
    }

    public T find(Long id) {
        return (T) em.find(clazz, id);
    }

    public List<T> list(String sql) {
        List<T> list = em.createQuery(sql).getResultList();
        if (list==null){
            list = new ArrayList<T>();
        }
        return list;
    }
    
    public T find(String sql) {
        T t = (T) em.createQuery(sql).getSingleResult();
        return (T) t;
    }

}
