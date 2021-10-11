package bookmyconsultation.ratingservice.security;

import bookmyconsultation.ratingservice.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRatingRepo userRatingRepo;

    @Override
    public UserPrincipal loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRatingRepo
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User name " + id + " does not exist"));
        return UserPrincipal.create(user);
    }
}
