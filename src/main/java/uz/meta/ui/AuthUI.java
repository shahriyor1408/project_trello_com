package uz.meta.ui;

import uz.jl.BaseUtils;
import uz.jl.Colors;
import uz.meta.dao.auth.AuthUserDao;
import uz.meta.domains.auth.UserEntity;
import uz.meta.domains.auth.UserOTPEntity;
import uz.meta.dto.OtpDto;
import uz.meta.dto.auth.Session;
import uz.meta.dto.auth.UserCreateDTO;
import uz.meta.dto.response.ResponseEntity;
import uz.meta.enums.Language;
import uz.meta.exceptions.CustomSQLException;
import uz.meta.service.auth.AuthService;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuthUI {
    AuthService service = new AuthService();
    AuthUserDao authUserDao = new AuthUserDao();

    public static void main(String[] args) {
        AuthUI authUI = new AuthUI();
        if (Objects.isNull(Session.sessionUser)) {
            BaseUtils.println("Login -> 1");
            BaseUtils.println("Register -> 2");
        } else {
            BaseUtils.println("Settings    -> 4");
            BaseUtils.println("Logout      -> 5");
        }
        BaseUtils.println("Quit -> q");
        String choice = BaseUtils.readText("?:");
        switch (choice) {
            case "1" -> authUI.login();
            case "2" -> authUI.register();
            case "3" -> authUI.settings();
            case "4" -> authUI.logout();
            case "q" -> {
                BaseUtils.println("Bye");
                System.exit(0);
            }
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
        main(args);
    }

    private void logout() {

    }

    private void settings() {

    }

    private void register() {
        try {
            String phoneNumber = BaseUtils.readText("Phone Number : ");
            UserEntity userEntity = authUserDao.findUserByPhoneNumber(phoneNumber);
            if (Objects.nonNull(userEntity)) {
                BaseUtils.println("Phone number already registered", Colors.RED);
                return;
            }
//            String otp = BaseUtils.otp(6);
//            OtpDto otpDto = OtpDto.builder()
//                    .otp(otp)
//                    .phone(phoneNumber)
//                    .expiry(LocalDateTime.now().plusMinutes(2))
//                    .build();
//            service.sendOtp(otpDto);

            String receiverOtp = BaseUtils.readText("otp:");
//            UserOTPEntity userOTPEntity = service.findByOtpAndPhoneNumber(receiverOtp, phoneNumber);

//            if (Objects.isNull(userOTPEntity)) {
//                BaseUtils.println("Invalid OTP", Colors.RED);
//                return;
//            }
//
//            if (userOTPEntity.getExpires().isBefore(LocalDateTime.now())) {
//                BaseUtils.println("Invalid OTP", Colors.RED);
//                return;
//            }

            String firstName = BaseUtils.readText("First Name : ");
            String lastName = BaseUtils.readText("Last Name : ");

            UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                    .language(Language.RU.name())
                    .firstName(firstName)
                    .lastName(lastName)
                    .phone(phoneNumber)
                    .build();

            print_response(service.register(userCreateDTO));

        } catch (CustomSQLException e) {
            BaseUtils.println(e.getLocalizedMessage(), Colors.RED);
        }
    }

    private void login() {
        String username = BaseUtils.readText("username ");
        String password = BaseUtils.readText("password ");
        print_response(service.login(username, password));
    }

    public void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response.getData()), color);
    }
}
