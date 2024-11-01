package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Jugador;
import com.tallerwebi.dominio.Posiciones;
import com.tallerwebi.dominio.RepositorioJugador;
import com.tallerwebi.infraestructura.config.HibernateInfraestructuraTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.Query;
import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateInfraestructuraTestConfig.class})
public class RespositorioJugadorTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioJugador repositorioJugador;

    @BeforeEach
    public void init(){
        this.repositorioJugador = new RepositorioJugadorImpl(this.sessionFactory);
    }

    @Test
    @Transactional
    @Rollback
    public void dadoQueExisteUnRespositorioJugadorCuandoGuardoUnJugadorLoPuedoObtenerDesdeLaBD(){
        Jugador jugador = new Jugador();
        jugador.setNombre("Matias");
        jugador.setPosicion(Posiciones.DEFENSOR);
        jugador.setNumero(2);

        this.repositorioJugador.guardar(jugador);

        String hql = "FROM Jugador WHERE nombre= :nombre";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("nombre", "Matias");
        Jugador jugadorObtenido = (Jugador)query.getSingleResult();

        assertThat(jugadorObtenido, equalTo(jugador));
    }

    @Test
    @Transactional
    @Rollback
    public void dadoQueExistenJugadoresEnDistintasPosicionesCuandoConsultoJugadoresDefensoresObtengoUnaColeccionCon2JugadoresDefensores(){
        this.sessionFactory.getCurrentSession().save(new Jugador("Matias", 2, Posiciones.DEFENSOR));
        this.sessionFactory.getCurrentSession().save(new Jugador("Juan", 6, Posiciones.DEFENSOR));
        this.sessionFactory.getCurrentSession().save(new Jugador("Castolo", 9, Posiciones.DELANTERO));
        this.sessionFactory.getCurrentSession().save(new Jugador("Leandro", 5, Posiciones.MEDIOCAMPO));

        List<Jugador> jugadores = this.repositorioJugador.obtenerJugadores(Posiciones.DEFENSOR);

        assertThat(jugadores.size(), equalTo(2));
        assertThat(jugadores.get(0).getPosicion(), equalTo(Posiciones.DEFENSOR));
        assertThat(jugadores.get(1).getPosicion(), equalTo(Posiciones.DEFENSOR));
    }
}
