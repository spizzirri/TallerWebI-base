package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Auto;
import com.tallerwebi.dominio.Modelo;
import com.tallerwebi.dominio.RepositorioAuto;
import com.tallerwebi.infraestructura.config.HibernateInfraestructuraTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import javax.persistence.Query;
import javax.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateInfraestructuraTestConfig.class})
public class RepositorioAutoImplTest {

    @Autowired
    private SessionFactory sessionFactory;
    private RepositorioAuto repositorioAuto;

    @BeforeEach
    public void init(){
        this.repositorioAuto = new RepositorioAutoImpl(sessionFactory);
    }

    @Test
    @Transactional
    public void dadoQueExisteUnRepositorioAutoCuandoGuardoUnAutoEntoncesLoEncuentroEnLaBaseDeDatos(){
        Modelo modelo = new Modelo();
        modelo.setDescripcion("Focus");
        this.sessionFactory.getCurrentSession().save(modelo);

        Auto auto = new Auto();
        //auto.setModelo("Focus");
        auto.setModelo(modelo);

        this.repositorioAuto.guardar(auto);

        String hql = "SELECT a FROM Auto a INNER JOIN a.modelo WHERE a.modelo.descripcion = :modelo";
        Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
        query.setParameter("modelo", "Focus");
        Auto autoObtenido = (Auto)query.getSingleResult();

        assertThat(autoObtenido, equalTo(auto));
    }

}
