package org.openjfx.controller;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.openjfx.model.MenuItem;
import org.openjfx.utils.HibernateUtil;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.math.BigDecimal;
import java.util.List;

public class MenuController {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void insertSampleMenus() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // ------------------ Entrantes ------------------
            MenuItem bruschetta = new MenuItem();
            bruschetta.setName("Bruschetta");
            bruschetta.setDescription("Tostas de pan con tomate fresco, albahaca y aceite de oliva.");
            bruschetta.setPrice(new BigDecimal("4.99"));
            bruschetta.setCategory("Entrante");
            bruschetta.setAvailable(true);
            session.persist(bruschetta);

            MenuItem calamari = new MenuItem();
            calamari.setName("Calamares Fritos");
            calamari.setDescription("Calamares frescos rebozados y fritos, servidos con salsa tártara.");
            calamari.setPrice(new BigDecimal("6.99"));
            calamari.setCategory("Entrante");
            calamari.setAvailable(true);
            session.persist(calamari);

            // ------------------ Platos Principales ------------------
            MenuItem lasagna = new MenuItem();
            lasagna.setName("Lasagna Tradicional");
            lasagna.setDescription("Capas de pasta, carne, queso y salsa bechamel horneadas a la perfección.");
            lasagna.setPrice(new BigDecimal("12.99"));
            lasagna.setCategory("Plato Principal");
            lasagna.setAvailable(true);
            session.persist(lasagna);

            MenuItem steak = new MenuItem();
            steak.setName("Bistec a la Parrilla");
            steak.setDescription(
                    "Bistec de res de primera calidad, cocinado a tu gusto, acompañado de verduras asadas.");
            steak.setPrice(new BigDecimal("19.99"));
            steak.setCategory("Plato Principal");
            steak.setAvailable(true);
            session.persist(steak);

            MenuItem sushiPlatter = new MenuItem();
            sushiPlatter.setName("Plato de Sushi Variado");
            sushiPlatter.setDescription("Selección de sushi fresco, incluyendo nigiri, sashimi y maki.");
            sushiPlatter.setPrice(new BigDecimal("16.99"));
            sushiPlatter.setCategory("Plato Principal");
            sushiPlatter.setAvailable(true);
            session.persist(sushiPlatter);

            // ------------------ Postres ------------------
            MenuItem tiramisu = new MenuItem();
            tiramisu.setName("Tiramisú");
            tiramisu.setDescription("Clásico postre italiano de capas de bizcocho, café, mascarpone y cacao.");
            tiramisu.setPrice(new BigDecimal("5.99"));
            tiramisu.setCategory("Postre");
            tiramisu.setAvailable(true);
            session.persist(tiramisu);

            MenuItem cheesecake = new MenuItem();
            cheesecake.setName("Cheesecake de Frutos Rojos");
            cheesecake.setDescription("Suave cheesecake con base de galleta y cobertura de frutos rojos.");
            cheesecake.setPrice(new BigDecimal("6.49"));
            cheesecake.setCategory("Postre");
            cheesecake.setAvailable(true);
            session.persist(cheesecake);

            // ------------------ Bebidas ------------------
            MenuItem mojito = new MenuItem();
            mojito.setName("Mojito");
            mojito.setDescription("Cóctel refrescante de ron, menta, limón, azúcar y soda.");
            mojito.setPrice(new BigDecimal("7.99"));
            mojito.setCategory("Bebida");
            mojito.setAvailable(true);
            session.persist(mojito);

            MenuItem vinoTinto = new MenuItem();
            vinoTinto.setName("Vino Tinto de la Casa");
            vinoTinto.setDescription("Selección de vinos tintos locales, ideal para acompañar cualquier plato.");
            vinoTinto.setPrice(new BigDecimal("8.99"));
            vinoTinto.setCategory("Bebida");
            vinoTinto.setAvailable(true);
            session.persist(vinoTinto);

            MenuItem cerveza = new MenuItem();
            cerveza.setName("Cerveza Artesanal");
            cerveza.setDescription("Variedad de cervezas artesanales de diferentes estilos y sabores.");
            cerveza.setPrice(new BigDecimal("5.99"));
            cerveza.setCategory("Bebida");
            cerveza.setAvailable(true);
            session.persist(cerveza);

            // ------------------ Especialidades de la Casa ------------------
            MenuItem paella = new MenuItem();
            paella.setName("Paella Valenciana");
            paella.setDescription("Auténtica paella con mariscos, pollo, verduras y azafrán.");
            paella.setPrice(new BigDecimal("14.99"));
            paella.setCategory("Especialidad de la Casa");
            paella.setAvailable(true);
            session.persist(paella);

            MenuItem ribeye = new MenuItem();
            ribeye.setName("Ribeye Steak Especial");
            ribeye.setDescription("Corte de ribeye jugoso, marinado con hierbas finas y servido con papas fritas.");
            ribeye.setPrice(new BigDecimal("21.99"));
            ribeye.setCategory("Especialidad de la Casa");
            ribeye.setAvailable(true);
            session.persist(ribeye);

            // ------------------ Opciones Vegetarianas ------------------
            MenuItem veggieBurger = new MenuItem();
            veggieBurger.setName("Hamburguesa Vegetariana");
            veggieBurger.setDescription("Hamburguesa de garbanzos con aguacate, tomate y lechuga en pan integral.");
            veggieBurger.setPrice(new BigDecimal("9.99"));
            veggieBurger.setCategory("Plato Principal");
            veggieBurger.setAvailable(true);
            session.persist(veggieBurger);

            MenuItem quinoaSalad = new MenuItem();
            quinoaSalad.setName("Ensalada de Quinoa");
            quinoaSalad.setDescription("Quinoa mezclada con vegetales frescos, nueces y vinagreta de limón.");
            quinoaSalad.setPrice(new BigDecimal("7.99"));
            quinoaSalad.setCategory("Entrante");
            quinoaSalad.setAvailable(true);
            session.persist(quinoaSalad);

            // ------------------ Bebidas Sin Alcohol ------------------
            MenuItem limonada = new MenuItem();
            limonada.setName("Limonada Natural");
            limonada.setDescription("Refrescante limonada hecha con limones frescos y miel.");
            limonada.setPrice(new BigDecimal("3.99"));
            limonada.setCategory("Bebida");
            limonada.setAvailable(true);
            session.persist(limonada);

            MenuItem jugoNaranja = new MenuItem();
            jugoNaranja.setName("Jugo de Naranja Natural");
            jugoNaranja.setDescription("Jugo recién exprimido de naranjas maduras.");
            jugoNaranja.setPrice(new BigDecimal("4.49"));
            jugoNaranja.setCategory("Bebida");
            jugoNaranja.setAvailable(true);
            session.persist(jugoNaranja);

            // ------------------ Bebidas Calientes ------------------
            MenuItem cafe = new MenuItem();
            cafe.setName("Café Americano");
            cafe.setDescription("Café negro preparado con agua caliente y granos seleccionados.");
            cafe.setPrice(new BigDecimal("2.99"));
            cafe.setCategory("Bebida");
            cafe.setAvailable(true);
            session.persist(cafe);

            MenuItem latte = new MenuItem();
            latte.setName("Latte de Vainilla");
            latte.setDescription("Espresso con leche vaporizada y sirope de vainilla.");
            latte.setPrice(new BigDecimal("3.99"));
            latte.setCategory("Bebida");
            latte.setAvailable(true);
            session.persist(latte);

            tx.commit();
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static List<MenuItem> getAllMenus() {
        List<MenuItem> menus = null;
        try (Session session = sessionFactory.openSession()) {
            menus = session.createQuery("FROM MenuItem", MenuItem.class).getResultList();
            System.out.println(menus);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return menus;
    }

    public static void main(String[] args) {
        // Insertar menús de muestra
        insertSampleMenus();

        // Seleccionar y mostrar todos los menús
        List<MenuItem> menus = getAllMenus();
        if (menus != null) {
            for (MenuItem menu : menus) {
                System.out.println(menu);
            }
        }

        // Cerrar la SessionFactory al finalizar
        sessionFactory.close();
    }
}