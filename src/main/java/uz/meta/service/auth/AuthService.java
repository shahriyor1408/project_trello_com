package uz.meta.service.auth;

import uz.jl.BaseUtils;
import uz.meta.dao.auth.AuthUserDao;
import uz.meta.dto.auth.Session;
import uz.meta.dto.auth.UserDTO;
import uz.meta.dto.response.AppErrorDTO;
import uz.meta.dto.response.DataDTO;
import uz.meta.dto.response.ResponseEntity;


import java.sql.SQLException;
import java.util.Optional;

public class AuthService {

    AuthUserDao authUserDao = new AuthUserDao();

    public ResponseEntity login(String username, String password) {
        Optional<String> jsonDataOptional;
        try {
            jsonDataOptional = authUserDao.login(username, password);
            if (jsonDataOptional.isEmpty()) {
                return new ResponseEntity<>(new DataDTO<>(
                        AppErrorDTO
                                .builder()
                                .friendlyMessage("Something wrong. Please try later")
                                .developerMessage("user_login() procedure returned null check it out(hr.user_login(username, password)")
                                .build()),
                        404);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        UserDTO dto = BaseUtils.gson.fromJson(jsonDataOptional.get(), UserDTO.class);
        Session.setSessionUser(dto);
        return new ResponseEntity<>(new DataDTO<>(dto), 200);
    }
}
