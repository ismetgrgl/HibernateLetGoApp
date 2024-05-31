package org.ismetg.repository;

import org.ismetg.entity.User;

public class UserRepository extends RepositoryManager<User , Long> {
    public UserRepository() {
        super(User.class);
    }
}
