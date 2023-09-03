package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.After;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static java.util.Objects.nonNull;

public class HbmTrackerTest {

    private final StandardServiceRegistry registry =
            new StandardServiceRegistryBuilder().configure().build();

    private final SessionFactory sf =
            new MetadataSources(registry).buildMetadata().buildSessionFactory();

    @After
    public void after() {
        Session session = sf.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.createQuery(
                    "delete from Item").executeUpdate();
            session.getTransaction().commit();

        } catch (Exception e) {
            if (nonNull(transaction)) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Test
    public void whenAddNewItemThenFindById() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            Assertions.assertNotNull(result);
            Assertions.assertEquals(item.getName(), result.getName());
        }
    }

    @Test
    public void whenFindAll() {
        try (HbmTracker tracker = new HbmTracker()) {
            List<Item> result = tracker.findAll();
            Assertions.assertNotNull(result);
            Assertions.assertTrue(result.size() > 0);
        }
    }

    @Test
    public void whenFindByName() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            List<Item> result = tracker.findByName(item.getName());
            Assertions.assertNotNull(result);
            Assertions.assertEquals(item.getName(), result.get(0).getName());
        }
    }

    @Test
    public void whenReplace() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item();
            Item item2 = new Item();
            item.setName("test1");
            item2.setName("test2");
            tracker.add(item);
            tracker.replace(item.getId(), item2);
            Item result = tracker.findById(item.getId());
            Assertions.assertNotNull(result);
            Assertions.assertEquals(item2.getName(), result.getName());
        }
    }

    @Test
    public void whenDelete() {
        try (HbmTracker tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test5");
            tracker.add(item);
            tracker.delete(item.getId());
            Item result = tracker.findById(item.getId());
            Assertions.assertNull(result);
        }
    }
}