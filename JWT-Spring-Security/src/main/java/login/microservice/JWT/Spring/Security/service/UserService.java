package login.microservice.JWT.Spring.Security.service;

import login.microservice.JWT.Spring.Security.entity.RoleEntity;
import login.microservice.JWT.Spring.Security.entity.UserEntity;
import login.microservice.JWT.Spring.Security.repository.RoleEntityRepository;
import login.microservice.JWT.Spring.Security.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import lombok.extern.java.Log;

@Service
public class UserService {

    @Autowired
    private UserEntityRepository userEntityRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    /**
     * Complete the users' credentials with the role and save him in the database.
     *
     * @param userEntity that have to be saved
     * @return same user with role updated.
     */
    public UserEntity saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        //userEntity.setEmail();
        return userEntityRepository.save(userEntity);
    }

    /**
     * Search in the database an user by his username.
     * @param login the username
     * @return the user that was found.
     */
    public UserEntity findByLogin(String login) {

        return userEntityRepository.findByLogin(login);
    }

    /**
     * Search in the database an user based on his username and his password.
     * @param login the username
     * @param password user password
     * @return the user that was found (that matched the given credentials).
     */
    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }

    /**
     * Update the role of an user
     * @param login username
     * @param roleName e.g. admin, user, secretary
     * @return updated user
     */
    public UserEntity updateRoleUser(String login, String roleName){
        UserEntity userEntity = findByLogin(login);
        System.out.println(userEntity.getId());
        if (userEntity != null) {
            RoleEntity userRole = roleEntityRepository.findByName("ROLE_"+roleName.toUpperCase().trim());
            System.out.println(userRole.getId()+ userRole.getName());
            userEntity.setRoleEntity(userRole);
            return userEntityRepository.save(userEntity);
        }
        return null;
    }
}
