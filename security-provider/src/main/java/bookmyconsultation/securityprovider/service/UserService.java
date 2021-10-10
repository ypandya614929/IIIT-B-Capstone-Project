package bookmyconsultation.securityprovider.service;

import bookmyconsultation.securityprovider.entity.User;

import java.util.List;

public interface UserService {

    /**
     * Get all the users in db.
     * @return
     */
    List<User> getAllUsers();
}
