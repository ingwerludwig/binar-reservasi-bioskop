package org.BinarAcademy.Challenge_4.service.UserService;

import org.BinarAcademy.Challenge_4.model.users.User;
import org.BinarAcademy.Challenge_4.repository.user.UserRepository;
import org.BinarAcademy.Challenge_4.service.FilmService.FilmService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UserService {

    Logger logger = (Logger) LoggerFactory.getLogger(FilmService.class);
    @Autowired
    private final UserRepository repository;
    @Autowired
    public UserService(UserRepository repository){
        this.repository = repository;
    }

    public void addNewUser(User newUser) {
        User existUser = repository.findUserByNama(newUser.getNama());
        if(existUser != null) {
            throw new IllegalStateException("User nama has been taken ");
        }

        try{
            repository.save(newUser);
        }catch(Exception e){
            logger.log(Level.SEVERE, "an exception was thrown");
        }
    }

    public void deleteUser(String nama) {
        User existUser = repository.findUserByNama(nama);
        try{
            repository.delete(existUser);
        }catch(Exception e){
            logger.log(Level.SEVERE, "an exception was thrown");
        }
    }

    public List<User> getAllUser() {
        return repository.findAll().stream().toList();
    }
    @Transactional
    public void updateUser(int id, String nama, String password) {
        User existUser = repository.findUserById(id);

        if(existUser != null && nama != null && nama.length()>0 && password != null && password.length()>0){
            existUser.setNama(nama);
            existUser.setPassword(password);
        }
    }

    public void processOAuthPostLogin(String email, String nama) {
        User existUser = repository.findUserByEmail(email);

        if (existUser==null) {
            User newUser = new User();
            newUser.setNama(nama);
            newUser.setEmail(email);
            newUser.setProvider(Provider.GOOGLE);
            repository.save(newUser);
        }

    }
}