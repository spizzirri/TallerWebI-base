package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Modelo;
import com.tallerwebi.dominio.RepositorioModelo;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioModeloImpl implements RepositorioModelo {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioModeloImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Modelo modelo) {
        this.sessionFactory.getCurrentSession().save(modelo);

    }

    @Override
    public List<Modelo> obtener() {

        String hql = "FROM Modelo";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);

        return query.getResultList();
    }

    @Override
    public void actualizar(Modelo modelo) {
        String hql = "UPDATE Modelo SET descripcion = :descripcion WHERE id = :id";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("descripcion", modelo.getDescripcion());
        query.setParameter("id", modelo.getId());
        query.executeUpdate();
    }
}
