package uz.meta.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.io.FileReader;
import java.util.Objects;
import java.util.Properties;

public class HibernateUtils {
    private static final SessionFactory sessionFactory = setUp();

    private static SessionFactory setUp() {
        StandardServiceRegistry registry = null;

        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder =
                        new StandardServiceRegistryBuilder();
                Properties properties = new Properties();
                properties.load(new FileReader("src/main/resources/datasource.properties"));
                registryBuilder.applySettings(properties);
                registry = registryBuilder.build();
                MetadataSources sources = new MetadataSources(registry);

//                Reflections reflections = new Reflections("uz.meta.domains");
//                reflections.getTypesAnnotatedWith(Entity.class)
//                        .forEach(clazz -> sources.addAnnotatedClassName(clazz.getName()));

//                sources.addAnnotatedClass(UserSessionEntity.class);

                Metadata metadata = sources.getMetadataBuilder().build();
                return metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                if (Objects.nonNull(registry)) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                throw new RuntimeException("Hibernate exception %s".formatted(e.getMessage()));
            }
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
