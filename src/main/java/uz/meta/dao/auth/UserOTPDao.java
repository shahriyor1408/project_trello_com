package uz.meta.dao.auth;

import uz.jl.BaseUtils;
import uz.meta.config.DbConfigurer;
import uz.meta.dto.OtpDto;
import uz.meta.exceptions.CustomSQLException;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserOTPDao {
    public Optional<Long> save(OtpDto otpDto) throws CustomSQLException {


        try {
            CallableStatement cs = DbConfigurer.getConnection()
                    .prepareCall("select hr.user_otp_create(?);");
            cs.setString(1, BaseUtils.gson.toJson(otpDto));
            ResultSet resultSet = cs.executeQuery();
            if (resultSet.next()) {
                return Optional.of(resultSet.getLong(1));
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new CustomSQLException(e);
        }


    }
}
