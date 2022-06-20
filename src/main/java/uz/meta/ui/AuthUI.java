package uz.meta.ui;

import uz.jl.BaseUtils;
import uz.jl.Colors;
import uz.meta.dto.auth.Session;
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
            case "4" -> authUI.projectCRUD();
            case "5" -> authUI.projectColumnCRUD();
            case "6" -> authUI.settings();
            case "0" -> authUI.logout();
            case "q" -> {
                BaseUtils.println("Bye");
                System.exit(0);
            }
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
        main(args);
    }

    private void projectColumnCRUD() {

    }

    private void projectCRUD() {
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
            case "4" -> authUI.projectCRUD();
            case "5" -> authUI.projectColumnCRUD();
            default -> BaseUtils.println("Wrong Choice", Colors.RED);
        }
    }

    private void giveAdminToUser() {

    }

    private void logout() {

    }

    private void settings() {

    }

    private void register() {

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
