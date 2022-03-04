package com.team1.pinterest.Controller;

import com.team1.pinterest.Entitiy.Category;
import com.team1.pinterest.Entitiy.Image;
import com.team1.pinterest.Entitiy.LikeImage;
import com.team1.pinterest.Entitiy.User;
import com.team1.pinterest.Service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Profile("local")
@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.DBInit();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final EntityManager em;
        private final LikeService likeService;

        public void DBInit() {

            User user1 = new User("user", "email", "password");
            User user2 = new User("user2", "email2", "password2");
            em.persist(user1);
            em.persist(user2);
            Image image1 = new Image("TITLE1", "content", Category.A, user1);
            em.persist(image1);

            likeService.addLike(user2.getId(), image1.getId());
        }
    }
}
