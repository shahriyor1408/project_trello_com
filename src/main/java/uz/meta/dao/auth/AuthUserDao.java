package uz.meta.dao.auth;

import org.hibernate.Session;
import uz.meta.config.HibernateUtils;

import java.sql.*;
import java.util.Optional;

public class AuthUserDao {
    public Optional<String> login(String username, String password) throws SQLException {
        String result;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call hr.user_login(?,?)}"
                );
                function.registerOutParameter(1, Types.VARCHAR);
                function.setString(2, username);
                function.setString(3, password);
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getString(1);
            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }

            return Optional.ofNullable(result);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
