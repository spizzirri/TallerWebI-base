package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.calendario.ItemRendimiento;
import com.tallerwebi.dominio.calendario.RepositorioCalendario;
import com.tallerwebi.dominio.calendario.TipoRendimiento;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("repositorioCalendario")
public class RepositorioCalendarioImpl implements RepositorioCalendario {

    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCalendarioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<ItemRendimiento> obtenerItemsRendimiento() {
        return this.sessionFactory.getCurrentSession()
                .createQuery("FROM ItemRendimiento", ItemRendimiento.class)
                .getResultList();
    }

    @Override
    public void guardar(ItemRendimiento itemRendimiento) {
        if (!existeItemRendimientoPorFecha(itemRendimiento.getFecha())) {
            this.sessionFactory.getCurrentSession().save(itemRendimiento);
        } else {
            throw new RuntimeException("Ya existe un ItemRendimiento para esta fecha.");
        }
    }

    @Override
    public boolean existeItemRendimientoPorFecha(LocalDate fecha) {
        String hql = "SELECT count(*) FROM ItemRendimiento WHERE fecha = :fecha";
        Long count = this.sessionFactory.getCurrentSession()
                .createQuery(hql, Long.class)
                .setParameter("fecha", fecha)
                .uniqueResult();
        return count > 0;
    }

    @Override
    public void vaciarCalendario() {
        Session session = this.sessionFactory.getCurrentSession();
        session.createQuery("DELETE FROM ItemRendimiento").executeUpdate();
    }

    @Override
    public List<ItemRendimiento> obtenerItemsPorTipoRendimiento(TipoRendimiento tipoRendimiento) {
        return List.of();
    }

    @Override
    public void eliminar(ItemRendimiento dia) {
        this.sessionFactory.getCurrentSession().delete(dia);
    }


    @Override
    public void actualizar(ItemRendimiento itemRendimiento) {
        this.sessionFactory.getCurrentSession().merge(itemRendimiento);
    }


}

