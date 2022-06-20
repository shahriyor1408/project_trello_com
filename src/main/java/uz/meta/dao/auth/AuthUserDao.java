package uz.meta.dao.auth;

import org.hibernate.Session;
import uz.meta.config.HibernateUtils;
import uz.meta.dto.auth.UserCreateDTO;
import uz.meta.exceptions.CustomSQLException;

import java.sql.*;
import java.util.Optional;

public class AuthUserDao {
    public Optional<String> login(String username, String password) throws CustomSQLException {
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
                throw new CustomSQLException(e.getCause().getLocalizedMessage());
            }
            return Optional.of(result);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }

    public Optional<Long> register(UserCreateDTO userCreateDTO) throws CustomSQLException {
        Long result;
        Session session = HibernateUtils.getSessionFactory().getCurrentSession();
        session.beginTransaction();

        try {
            CallableStatement callableStatement = session.doReturningWork(connection -> {
                CallableStatement function = connection.prepareCall(
                        "{ ? = call hr.user_create(?)}"
                );
                function.registerOutParameter(1, Types.VARCHAR);
                function.setString(2, userCreateDTO.toString());
                function.execute();
                return function;
            });
            try {
                result = callableStatement.getLong(1);
            } catch (SQLException e) {
                throw new CustomSQLException(e.getCause().getLocalizedMessage());
            }
            return Optional.of(result);
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}

