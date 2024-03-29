package uz.meta.service.auth;

import uz.jl.BaseUtils;
import uz.meta.dao.auth.AuthUserDao;
import uz.meta.dto.auth.Session;
import uz.meta.dto.auth.UserCreateDTO;
import uz.meta.dto.auth.UserDTO;
import uz.meta.dto.response.AppErrorDTO;
import uz.meta.dto.response.DataDTO;
import uz.meta.dto.response.ResponseEntity;

import java.util.Optional;

public class AuthService {
    AuthUserDao authUserDao = new AuthUserDao();

    public ResponseEntity<DataDTO<UserDTO>> login(String username, String password) {
        try {
            Optional<String> jsonDataOptional = authUserDao.login(username, password);
            if (jsonDataOptional.isEmpty()) {
                return new ResponseEntity<>(new DataDTO<>(
                        AppErrorDTO
                                .builder()
                                .friendlyMessage("Something wrong. Please try later")
                                .developerMessage("user_login() procedure returned null check it out(hr.user_login(username, password)")
                                .build()),
                        404);
            }
            UserDTO dto = BaseUtils.gson.fromJson(jsonDataOptional.get(), UserDTO.class);
            Session.setSessionUser(dto);
            return new ResponseEntity<>(new DataDTO<>(dto), 200);

        } catch (Exception e) {
            return new ResponseEntity<>(new DataDTO<>(
                    AppErrorDTO
                            .builder()
                            .friendlyMessage(e.getLocalizedMessage())
                            .developerMessage(e.getCause().getMessage())
                            .build()
            ), 500);
        }
    }

    public ResponseEntity<DataDTO<Long>> register(UserCreateDTO userCreateDTO) {
        try {
            Optional<Long> userOptional = authUserDao.register(userCreateDTO);
            if (userOptional.isEmpty()) {
                return new ResponseEntity<>(new DataDTO<>(
                        AppErrorDTO
                                .builder()
                                .friendlyMessage("Something wrong try again")
                                .developerMessage("Bro check your user_create() function")
                                .build()
                ), 500);
            }

            Long userID = userOptional.get();
            return new ResponseEntity<>(new DataDTO<>(userID), 200);
        } catch (Exception e) {
            return new ResponseEntity<>(new DataDTO<>(
                    AppErrorDTO
                            .builder()
                            .friendlyMessage(e.getLocalizedMessage())
                            .developerMessage(e.getCause().getMessage())
                            .build()
            ), 500);
        }
    }
}
