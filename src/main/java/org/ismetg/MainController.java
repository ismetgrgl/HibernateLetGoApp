package org.ismetg;

import org.ismetg.entity.User;
import org.ismetg.service.UserService;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class MainController {
    static Scanner scanner = new Scanner(System.in);
    static UserService userService = new UserService();
    public static void menu(){

        while (true) {
            System.out.println("1- Login");
            System.out.println("2- Register");
            System.out.println("0- Sistemi Kapat");
            Integer secim;
            try {
                System.out.println("seçim giriniz");
                secim = scanner.nextInt(); scanner.nextLine();
            }catch (InputMismatchException e){
                throw new RuntimeException("boş giremezsiniz");
            }
            switch (secim) {
                case 1:
                    Optional<User> loggedUser = userService.login();
                    if (loggedUser.isPresent())
                    {
                        userService.userMenu();
                        break;
                    }
                    break;
                case 2:
                    userService.register();
                    break;

                case 0:
                    System.exit(0);
                    break;
            }
        }
    }
}
