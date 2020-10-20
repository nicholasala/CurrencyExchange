package currencyexchange.db;

import currencyexchange.model.Role;
import currencyexchange.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

@Service
public class ExchangeUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository ur;
    @Autowired
    private RoleRepository rr;
    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostConstruct
    private void init(){
        rr.save(new Role("manager"));
        rr.save(new Role("user"));

        ur.save(new User("nicholas", encoder.encode("exmachina"), new Role("manager")));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) {
        User user = ur.findByUsername(username);

        if (user == null) throw new UsernameNotFoundException(username);

        return user;
    }
}