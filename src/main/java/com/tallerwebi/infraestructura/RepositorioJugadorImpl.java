package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Posiciones;
import com.tallerwebi.dominio.RepositorioJugador;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class RepositorioJugadorImpl implements RepositorioJugador {

    @Autowired
    private final SessionFactory sessionFactory;

    public RepositorioJugadorImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void guardar(Jugador jugador) {
        this.sessionFactory.getCurrentSession().save(jugador);
    }

    @Override
    public List<Jugador> obtenerJugadores(Posiciones posicion) {
        String hql = "FROM Jugador WHERE posicion= :posicion";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("posicion", posicion);
        return query.getResultList();
    }
}
