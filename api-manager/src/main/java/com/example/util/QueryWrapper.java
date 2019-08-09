package com.example.util;

import javax.persistence.EntityManager;
import java.util.List;

public class QueryWrapper<T> {

    public List<T> list(EntityManager em, String hsql){
        return em.createQuery(hsql).getResultList();
    }

    public List<T> list2(EntityManager em, String nativeSql, Class cls){
        return em.createNativeQuery(nativeSql, cls).getResultList();
    }
}
