package uz.meta.service.auth;

import uz.meta.dao.auth.AuthUserDao;
import uz.meta.dao.auth.UserOTPDao;
import uz.meta.domains.auth.UserOTPEntity;
import uz.meta.dto.OtpDto;
import uz.meta.dto.auth.UserCreateDTO;
import uz.meta.dto.response.ResponseEntity;
import uz.meta.exceptions.CustomSQLException;
import uz.meta.service.MailService;


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
}
