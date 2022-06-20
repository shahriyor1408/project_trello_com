package uz.meta.dao.auth;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import uz.meta.config.HibernateUtils;

import java.util.Objects;

public class GenericDao<T> {
    protected SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    private Session session = getSession();

    protected Session getSession() {
        if (Objects.isNull(sessionFactory) || sessionFactory.isClosed()) {
            sessionFactory = HibernateUtils.getSessionFactory();
        }

        if (Objects.isNull(session) || !session.isOpen()) {
            session = sessionFactory.getCurrentSession();
        }
        return session;
    }
}
