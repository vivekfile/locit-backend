package com.vaya.locit.api.repository;

import com.vaya.locit.api.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository
        extends JpaRepository<Message, Integer> {

    List<Message> findByBuyerUserIdAndShopShopIdOrderBySentAtAsc(
            int buyerId,
            int shopId
    );

    List<Message> findBySenderUserId(int senderId);

    List<Message> findByShopShopIdOrderBySentAtAsc(
            int shopId
    );
}