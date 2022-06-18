package uz.meta.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtils {
    private static final SessionFactory sessionFactory = setUp();

    public static SessionFactory setUp() {
        StandardServiceRegistryBuilder srBuilder = new StandardServiceRegistryBuilder();
        StandardServiceRegistry build = srBuilder.configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClasses()
                .getMetadataBuilder().build();
        return metadata.buildSessionFactory();
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
