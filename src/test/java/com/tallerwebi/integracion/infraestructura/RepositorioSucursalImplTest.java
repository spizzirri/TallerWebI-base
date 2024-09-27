package com.tallerwebi.integracion.infraestructura;

import com.tallerwebi.dominio.Ciudad;
import com.tallerwebi.dominio.RepositorioSucursal;
import com.tallerwebi.dominio.Sucursal;
import com.tallerwebi.infraestructura.RepositorioSucursalImpl;
import com.tallerwebi.integracion.config.HibernateTestConfig;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HibernateTestConfig.class})
public class RepositorioSucursalImplTest {

    @Autowired
    private SessionFactory sessionFactory;

    private RepositorioSucursalImpl repositorioSucursalImpl;

    @BeforeEach
    public void init(){
        this.repositorioSucursalImpl = new RepositorioSucursalImpl(sessionFactory);
    }

    @Test
    @Rollback
    @Transactional
    public void dadoQueExisteUnaSucursalMostazaEnMDQYUnaEnMDZDebeDevolverUnaSolaSucursalCuandoBuscoSucursalesMostazaEnMDQ(){

        Ciudad ciudad_mdq = new Ciudad();
        ciudad_mdq.setNombre("MDQ");
        this.sessionFactory.getCurrentSession().save(ciudad_mdq);
        Sucursal sucursal_mdq = new Sucursal();
        sucursal_mdq.setNombre("Mostaza");
        sucursal_mdq.setCiudad(ciudad_mdq);
        this.sessionFactory.getCurrentSession().save(sucursal_mdq);

        Ciudad ciudad_mdz = new Ciudad();
        ciudad_mdz.setNombre("MDZ");
        this.sessionFactory.getCurrentSession().save(ciudad_mdz);
        Sucursal sucursal_mdz = new Sucursal();
        sucursal_mdz.setNombre("Mostaza");
        sucursal_mdz.setCiudad(ciudad_mdz);
        this.sessionFactory.getCurrentSession().save(sucursal_mdz);

        List<Sucursal> sucursales = this.repositorioSucursalImpl.buscarSucursalesPorCiudad("Mostaza", "MDQ");

        assertThat(sucursales.size(), equalTo(1));

        Sucursal sucursal = (Sucursal) sucursales.get(0);

        assertThat(sucursal.getNombre(), equalToIgnoringCase("Mostaza"));
        assertThat(sucursal.getCiudad().getNombre(), equalToIgnoringCase("MDQ"));
    }

    @Test
    @Rollback
    @Transactional
    public void dadoQueExistenDosSucursalesDebeDevolerDosSucursales(){

        dadoQueExistenDosSucursales();

        List<Sucursal> sucursales = cuandoObtengoLasSucursales();

        entoncesObtengoDosSucursales(sucursales);
    }

    private void entoncesObtengoDosSucursales(List<Sucursal> sucursales){
        assertThat(sucursales.size(), equalTo(2));
    }

    private List<Sucursal> cuandoObtengoLasSucursales(){
        return repositorioSucursalImpl.obtener();
    }
    private void dadoQueExistenDosSucursales(){
        Sucursal sucursal_mdq = new Sucursal();
        sucursal_mdq.setNombre("Mostaza_MDQ");
        this.repositorioSucursalImpl.guardar(sucursal_mdq);

        Sucursal sucursal_mdz = new Sucursal();
        sucursal_mdz.setNombre("Mostaza_MDZ");
        this.repositorioSucursalImpl.guardar(sucursal_mdz);
    }
}
