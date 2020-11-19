/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appagenda;

import entidades.Provincia;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author sergy
 */
public class AppAgenda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AppAgendaPU");
        EntityManager em = emf.createEntityManager();

        Provincia provinciaCadiz = new Provincia();
        provinciaCadiz.setNombre("Cadiz");
        Provincia provinciaSevilla = new Provincia();
        provinciaSevilla.setNombre("Sevilla");

        em.getTransaction().begin();
        //Añadir aqui las operaciones de modificacion de la base de datos
        em.persist(provinciaCadiz);
        em.persist(provinciaSevilla);
        em.getTransaction().commit();
        
        // Eliminar objetos
        Provincia provinciaId15 = em.find(Provincia.class, 15);
        em.getTransaction().begin();
        if (provinciaId15 != null){
            em.remove(provinciaId15);
        }else{
            System.out.println("No hay ninguna provincia con ID = 15");
        }
        em.getTransaction().commit();

        em.close();
        emf.close();
        try {
            DriverManager.getConnection("jdbc:derby:BDAgenda;shutdown=true");
        } catch (SQLException ex) {
        }

    }

}
