package org.ismetg.service;

import org.ismetg.entity.Ilan;
import org.ismetg.entity.Message;
import org.ismetg.entity.User;
import org.ismetg.entity.enums.Status;
import org.ismetg.repository.MessageRepository;
import org.ismetg.utility.SessionContext;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MessageService {
    MessageRepository messageRepository;
    Scanner scanner = new Scanner(System.in);
    public MessageService()
    {
        this.messageRepository = new MessageRepository();
    }

    public void mesajAt(Ilan ilan)
    {
        System.out.println("Kullanıcıya göndermek istediğiniz mesajı giriniz.");
        String mesaj = scanner.nextLine();
        messageRepository.save(Message.builder().text(mesaj).ilan(ilan).sender(SessionContext.loggedUser).receiver(ilan.getUser()).send_date(LocalDate.now()).status(Status.ACTIVE).build());

    }

    public void mesajlarimiGoruntule()
    {
        List<Object[]> ilanVeSenderlar = messageRepository.mesajKutusu(SessionContext.loggedUser);
        for (Object[] sonuc : ilanVeSenderlar) {
            // Dizinin ilk elemanı ilan olmalı
            Ilan ilan = (Ilan) sonuc[0];
            User sender = (User) sonuc[1];
            System.out.println(ilan.getId()+" nolu ilanınızda " + sender.getUsername() + " adlı kullanıcıdan mesaj var");
        }
        System.out.println("Hangi ilan ile ilgili mesajı görmek istiyorsunuz.");
        Long secim = scanner.nextLong(); scanner.nextLine();
        System.out.println("Hangi kullanıcı ile ilgili ilgili mesajı görmek istiyorsunuz.");
        String kullanici = scanner.nextLine();

        Optional<Ilan> ilan = LetGoServices.ilanService.ilanRepository.findById(secim);
        List<User> Gonderici = LetGoServices.userService.userRepository.findByColumnAndValue("username", kullanici);
        if (ilan.isEmpty())
        {
            System.out.println("Bu ilanda mesaj yok");
            return;
        }
        messageRepository.mesajlarIlanaGoreIcerik(SessionContext.loggedUser,Gonderici.getFirst(),ilan.get()).forEach(message -> System.out.println(message.getSender().getUsername() + " adlı kullanıcı tarafından mesajınız: " + message.getText() + " gonderim tarihi: " + message.getSend_date()));
        messageRepository.mesajlarIlanaGoreIcerik(Gonderici.getFirst(),SessionContext.loggedUser,ilan.get()).forEach(message -> System.out.println(message.getSender().getUsername() + " adlı kullanıcı tarafından mesajınız: " + message.getText() + " gonderim tarihi: " + message.getSend_date()));

        System.out.println("Mesaja cevap vermek istiyorsanız 1 tuşuna basınız. Istemıyorsanız 0 tuşuna basın");
        int secim2 = scanner.nextInt(); scanner.nextLine();
        if (secim2 == 1)
        {
            mesajaCevapVer(ilan.get(),Gonderici.getFirst());
        }

    }

    public void mesajaCevapVer(Ilan ilan, User receiver){
        System.out.println("Cevabınızı Giriniz.");
        String mesaj = scanner.nextLine();
        messageRepository.save(Message.builder().text(mesaj).receiver(receiver).sender(SessionContext.loggedUser).status(Status.ACTIVE).ilan(ilan).send_date(LocalDate.now()).build());
    }
}
