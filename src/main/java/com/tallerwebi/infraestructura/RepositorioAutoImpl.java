package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Auto;
import com.tallerwebi.dominio.RepositorioAuto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioAutoImpl implements RepositorioAuto {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioAutoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Auto auto) {
        this.sessionFactory.getCurrentSession().save(auto);
    }
}
