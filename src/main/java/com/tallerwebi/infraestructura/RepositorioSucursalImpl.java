package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.RepositorioSucursal;
import com.tallerwebi.dominio.Sucursal;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepositorioSucursalImpl implements RepositorioSucursal {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioSucursalImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Sucursal> obtener() {
        String hql = "FROM Sucursal";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Sucursal obtener(Long id) {
        String hql = "FROM Sucursal WHERE id = :idSucursal";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("idSucursal", id);

        return (Sucursal) query.getSingleResult();
    }

    @Override
    public void guardar(Sucursal sucursal) {
        this.sessionFactory.getCurrentSession().save(sucursal);
    }

    @Override
    public List<Sucursal> buscarSucursalesPorCiudad(String sucursal, String nombre_ciudad) {
        String hql = "SELECT s FROM Sucursal s JOIN Ciudad c ON s.ciudad.id = c.id WHERE s.nombre = :sucursal AND c.nombre = :nombre_ciudad";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("nombre_ciudad", nombre_ciudad);
        query.setParameter("sucursal", sucursal);

        return query.getResultList();
    }
}
