package uz.meta.ui;

import uz.jl.BaseUtils;
import uz.jl.Colors;
import uz.meta.dto.auth.EmployeeCreateDTO;
import uz.meta.dto.auth.Session;
import uz.meta.dto.auth.UserCreateDTO;
import uz.meta.dto.response.ResponseEntity;
import uz.meta.enums.UserRole;
import uz.meta.service.auth.AuthService;

import java.util.Objects;

public class AuthUI {
    AuthService service = new AuthService();

    public static void main(String[] args) {
        AuthUI authUI = new AuthUI();

        if (Objects.isNull(Session.sessionUser)) {
            BaseUtils.println("Login -> 1");
            BaseUtils.println("Register -> 2");
        } else if (Session.sessionUser.getRole().equals(UserRole.ADMIN.name())) {
            BaseUtils.println("Give admin to user     -> 3");
            BaseUtils.println("Project CRUD           -> 4");
            BaseUtils.println("Project Column CRUD    -> 5");
            BaseUtils.println("Settings               -> 6");
            BaseUtils.println("Logout                 -> 0");
        } else {
            BaseUtils.println("Project CRUD           -> 4");
            BaseUtils.println("Project Column CRUD    -> 5");
            BaseUtils.println("Settings               -> 6");
            BaseUtils.println("Logout                 -> 0");
        }

        BaseUtils.println("Quit -> q");
        String choice = BaseUtils.readText("?:");
        switch (choice) {
            case "1" -> authUI.login();
            case "2" -> authUI.register();
            case "3" -> authUI.giveAdminToUser();
            case "4" -> authUI.boardCRUD();
            case "5" -> authUI.boardColumnCRUD();
            case "6" -> authUI.settings();
            case "0" -> authUI.logout();
            case "q" -> {
                BaseUtils.println("Bye", Colors.CYAN);
                System.exit(0);
            }
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
        main(args);
    }

    private void boardColumnCRUD() {

    }

    private void boardCRUD() {
        AuthUI authUI = new AuthUI();
        BaseUtils.println("Create Project    -> 1");
        BaseUtils.println("Delete Project    -> 2");
        BaseUtils.println("Update Project    -> 3");
        BaseUtils.println("Project List      -> 4");
        BaseUtils.println("Go Back           -> 5");

        String choice = BaseUtils.readText("?:");
        switch (choice) {
            case "1" -> authUI.login();
            case "2" -> authUI.register();
            case "3" -> authUI.giveAdminToUser();
            case "4" -> authUI.boardCRUD();
            case "5" -> authUI.boardColumnCRUD();
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
    }

    private void giveAdminToUser() {

    }

    private void logout() {
        Session.sessionUser = null;
    }

    private void settings() {

    }

    private void register() {
        String username = BaseUtils.readText("create username: ");
        String password = BaseUtils.readText("create password: ");
        String fullName = BaseUtils.readText("Your full name: ");
        String phoneNumber = BaseUtils.readText("Your phone number: ");
        String email = BaseUtils.readText("Your email: ");
        EmployeeCreateDTO employeeCreateDTO = EmployeeCreateDTO.builder()
                .fullName(fullName)
                .phoneNumber(phoneNumber)
                .email(email)
                .build();

        UserCreateDTO userCreateDTO = UserCreateDTO.builder()
                .username(username)
                .password(password)
                .employeeCreateDTO(employeeCreateDTO)
                .build();
        service.register(userCreateDTO);
    }

    private void login() {
        String username = BaseUtils.readText("username : ");
        String password = BaseUtils.readText("password : ");
        print_response(service.login(username, password));
    }

    public void print_response(ResponseEntity response) {
        String color = response.getStatus() != 200 ? Colors.RED : Colors.GREEN;
        BaseUtils.println(BaseUtils.gson.toJson(response.getData()), color);
    }
}
