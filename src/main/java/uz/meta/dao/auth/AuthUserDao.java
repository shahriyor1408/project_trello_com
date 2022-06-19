package uz.meta.dao.auth;

import uz.meta.config.DbConfigurer;
import uz.meta.domains.auth.UserEntity;
import uz.meta.exceptions.CustomSQLException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthUserDao {
    public UserEntity findUserByPhoneNumber(String phoneNumber) throws CustomSQLException {
        try {
            PreparedStatement ps = DbConfigurer
                    .getConnection()
                    .prepareStatement("select t.* from hr.users t where t.phone  = ?;");
            ps.setString(1, phoneNumber);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return UserEntity.childBuilder()
                        .username(resultSet.getString("username"))
                        .password(resultSet.getString("password"))
                        .language(resultSet.getString("language"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .build();
            }
            return null;
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }

    }
}
