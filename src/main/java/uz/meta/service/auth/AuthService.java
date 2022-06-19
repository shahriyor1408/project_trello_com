package uz.meta.service.auth;

import uz.jl.BaseUtils;
import uz.meta.dao.auth.AuthUserDao;
import uz.meta.dao.auth.UserOTPDao;
import uz.meta.domains.auth.UserOTPEntity;
import uz.meta.dto.OtpDto;
import uz.meta.dto.auth.Session;
import uz.meta.dto.auth.UserCreateDTO;
import uz.meta.dto.auth.UserDTO;
import uz.meta.dto.response.AppErrorDTO;
import uz.meta.dto.response.DataDTO;
import uz.meta.dto.response.ResponseEntity;
import uz.meta.exceptions.CustomSQLException;
import uz.meta.service.MailService;


import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class AuthService {

    AuthUserDao authUserDao = new AuthUserDao();
    MailService mailService = new MailService();
    UserOTPDao userOTPDao = new UserOTPDao();

    public void sendOtp(OtpDto otpDto) {

        CompletableFuture.runAsync(() -> {
            try {
                mailService.sendMessage("Activation Code", otpDto.getOtp(), otpDto.getPhone());
                userOTPDao.save(otpDto);
            } catch (CustomSQLException e) {
                e.printStackTrace();
            }
        });
    }

    public ResponseEntity register(UserCreateDTO userCreateDTO) {
        return null;
    }

    public UserOTPEntity findByOtpAndPhoneNumber(String receiverOtp, String phoneNumber) {

        return null;
    }

    public ResponseEntity login(String username, String password) {
        try {
            Optional<String> jsonDataOptional = authUserDao.login(username, password);
            if (jsonDataOptional.isEmpty()) {
                return new ResponseEntity<>(new DataDTO<>(
                        AppErrorDTO
                                .builder()
                                .friendlyMessage("Something wrong. Plese try later")
                                .developerMessage("user_login() prodsedure returned null check it out(hr.user_login(username, password)")
                                .build()),
                        404);
            }

            UserDTO dto = BaseUtils.gson.fromJson(jsonDataOptional.get(), UserDTO.class);
            Session.setSessionUser(dto);
            return new ResponseEntity<>(new DataDTO<>(dto), 200);

        } catch (CustomSQLException e) {
            return new ResponseEntity<>(new DataDTO<>(
                    AppErrorDTO
                            .builder()
                            .friendlyMessage(e.getMessage())
                            .build()),
                    500);
        }
    }
}
