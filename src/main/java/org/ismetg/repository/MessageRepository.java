package org.ismetg.repository;

import jakarta.persistence.TypedQuery;
import org.ismetg.entity.Ilan;
import org.ismetg.entity.Message;
import org.ismetg.entity.User;

import java.util.List;

public class MessageRepository extends RepositoryManager<Message , Long> {
    public MessageRepository() {
        super(Message.class);
    }
    public List<Object[]> mesajKutusu(User user) {
        TypedQuery<Object[]> query = getEntityManager().createQuery(
                "SELECT DISTINCT m.ilan, m.sender FROM Message m WHERE m.receiver = :user GROUP BY m.ilan, m.sender", Object[].class);
        query.setParameter("user", user);
        return query.getResultList();
    }



    public List<Message> mesajlarIlanaGoreIcerik(User receiver,User sender, Ilan ilan){

        TypedQuery<Message> query = getEntityManager().createQuery(
                "SELECT m FROM Message m  WHERE m.receiver =: receiver AND m.sender =: sender AND m.ilan =: ilan ", Message.class);
        query.setParameter("receiver",receiver);
        query.setParameter("sender",sender);
        query.setParameter("ilan",ilan);
        return query.getResultList();
    }
}
