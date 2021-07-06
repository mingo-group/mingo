package com.mingo.demo.daos;

import com.mingo.demo.models.Message;
import com.mingo.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List <Message> findAllBySenderEqualsOrReceiverEqualsOrderBySentDesc(User sender, User receiver);
    List<Message> findMessagesBySenderEqualsOrReceiverEqualsOrderBySentDesc(User sender, User receiver);
    List<Message> findMessagesBySenderEqualsOrderBySentDesc(User sender);
}
