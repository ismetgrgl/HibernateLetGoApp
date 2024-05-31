package org.ismetg.service;

import org.ismetg.entity.User;
import org.ismetg.entity.enums.Status;
import org.ismetg.repository.FavouriteIlanRepository;
import org.ismetg.repository.IlanRepository;
import org.ismetg.repository.UserRepository;
import org.ismetg.utility.InputUtil;
import org.ismetg.utility.SessionContext;

import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserService {
    UserRepository userRepository;
    IlanService ilanService;
    IlanRepository ilanRepository;
    FavouriteIlanRepository favouriteIlanRepository;
    MessageService messageService;
    CategoryService categoryService;
    Scanner scanner = new Scanner(System.in);
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public UserService() {
        this.ilanService = new IlanService();
        this.userRepository = new UserRepository();
        this.ilanRepository = new IlanRepository();
        this.favouriteIlanRepository = new FavouriteIlanRepository();
        this.messageService = new MessageService();
        this.categoryService = new CategoryService();
    }

    public Optional<User> login() {
        String kullaniciadi = InputUtil.getStringValue("Kullanıcı adı giriniz.");
        String sifre = InputUtil.getStringValue("şifre giriniz.");

        List<User> kullaniciAdiListesi = userRepository.findByColumnAndValue("username", kullaniciadi);

        if (!kullaniciAdiListesi.isEmpty()) {
            if (kullaniciAdiListesi.getFirst().getPassword().equals(sifre)) {
                SessionContext.loggedUser = (kullaniciAdiListesi.getFirst());
                return Optional.ofNullable(kullaniciAdiListesi.getFirst());
            } else {
                System.out.println("Kullanici adi veya sifre hatali.");
                return Optional.empty();
            }

        }
        System.out.println("Kullanici adi veya sifre hatali.");
        return Optional.empty();
    }

    public boolean register() {

        String kullaniciadi;
        while (true) {
            kullaniciadi = InputUtil.getStringValue("Kullanıcı adı giriniz.");

            List<User> userList = userRepository.findByColumnAndValue("username", kullaniciadi);
            if (!userList.isEmpty()) {
                System.out.println("Kullanici adi daha once alinmistir.");
            } else {
                break;
            }
        }
        String sifre = InputUtil.getStringValue("şifre giriniz.");

        String email;
        while (true) {
            email = InputUtil.getStringValue("email giriniz.");
            List<User> emailList = userRepository.findByColumnAndValue("email", email);
            if (!isValidEmail(email)) {
                System.out.println("hatalı email formatı");
            }
            else if (!emailList.isEmpty()) {
                System.out.println("Email daha once alinmistir.");

            } else {
                break;
            }
        }

        String telefon = InputUtil.getStringValue("telefon giriniz.");
        String profileImage = InputUtil.getStringValue("profil Url giriniz.");
        String konum = InputUtil.getStringValue("Konum giriniz.");

        userRepository.save(User.builder().username(kullaniciadi).password(sifre).email(email).tel(telefon).profileimageurl(profileImage)
                .konum(konum).createat(LocalDate.now()).status(Status.ACTIVE).build());
        return true;
    }

    public static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public void searchByCategory(){
        categoryService.categoryRepository.getAllParentCategories().forEach(System.out::println);
        String secilenParentCategory = InputUtil.getStringValue("Bir parent kategory giriniz.");
        categoryService.categoryRepository.getCategoriesByParentName(secilenParentCategory).forEach(System.out::println);
        String childCategory = InputUtil.getStringValue("Bir child kategori seciniz.");
        ilanService.ilanlariGosterDetaysizKategoriyeGore(secilenParentCategory,childCategory);
    }

    public void searchByLocation(){
        String konum = InputUtil.getStringValue("Konum Giriniz");
        ilanService.ilanlariGosterDetaysizKonumaGore(konum);
    }
    
    public void userMenu()
    {
        while (true)
        {
            System.out.println("1- Ilan ver");
            System.out.println("2- Ilanları Detaysiz Listele");
            System.out.println("3- Ilanları Detayli Listele");
            System.out.println("4- Favori İlan Seçme");
            System.out.println("5- Mesajları Goruntule");
            System.out.println("6- Kategoriye göre arama");
            System.out.println("7- Konuma göre arama");
            System.out.println("0- Üst Menü");
            Integer secim;
            try
            {
                System.out.println("seçim giriniz");
                secim = scanner.nextInt(); scanner.nextLine();
            } catch (InputMismatchException e)
            {
                throw new RuntimeException("boş giremezsiniz");
            }
            switch (secim)
            {
                case 1:
                    ilanService.ilanVer();
                    break;
                case 2:
                    ilanService.ilanlariGosterDetaysiz();
                    break;
                case 3:
                    ilanService.ilanlariListeleDetayli();
                    break;
                case 4:
                    ilanService.favoriIlanSecme();
                    break;
                case 5:
                    messageService.mesajlarimiGoruntule();
                    break;
                case 6:
                    searchByCategory();
                    break;
                case 7:
                    searchByLocation();
                    break;
                case 0:
                    return;

            }
        }
    }

}
