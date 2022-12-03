package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry;
    private final SessionFactory sf;

    public HbmTracker() {
        this.registry = new StandardServiceRegistryBuilder().configure().build();
        this.sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public void init() {
    }

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public Item findById(int id) {
        Item item;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query<Item> query = session
                    .createQuery("FROM item WHERE id = :fId", Item.class)
                    .setParameter("fId", id);
            item = query.uniqueResult();
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query<Item> query = session
                    .createQuery("FROM item", Item.class);
            items = new ArrayList<>(query.list());
            session.getTransaction().commit();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            Query<Item> query = session
                    .createQuery("FROM item WHERE name LIKE :fName", Item.class)
                    .setParameter("fName", key);
            items = new ArrayList<>(query.list());
            session.getTransaction().commit();
        }
        return items;
    }

    @Override
    public boolean replace(int id, Item item) {
        int numberOfEntitiesUpdated;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            numberOfEntitiesUpdated = session
                    .createQuery("UPDATE item SET name = :?fName WHERE id = :fId")
                    .setParameter("fName", item.getName()).setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
        return numberOfEntitiesUpdated != 0;
    }

    @Override
    public boolean delete(int id) {
        int numberOfEntitiesDeleted;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            numberOfEntitiesDeleted = session
                    .createQuery("DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
        }
        return numberOfEntitiesDeleted != 0;
    }

    @Override
    public void close() {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
