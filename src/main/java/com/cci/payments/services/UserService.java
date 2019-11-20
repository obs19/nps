package com.cci.payments.services;

import com.cci.payments.dto.PageDTOGeneric;
import com.cci.payments.dto.UserDTO;
import com.cci.payments.model.PaymentForm;
import com.cci.payments.model.User;
import com.cci.payments.model.UserActivityStatus;
import com.cci.payments.repository.UserRepository;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Mapper beanMapper;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(UserDTO dto){
        User user = beanMapper.map(dto, User.class);
        user.setStatus(UserActivityStatus.ACTIVE);
        return save(user);
    }

    private User getUser(Long id){
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Can't find User with ID = " + id));
    }

    public UserDTO getById(Long id){
        return beanMapper.map(getUser(id), UserDTO.class);
    }

    public PageDTOGeneric getAllUsers(int page, int start, int limit) {
        Page<User> userPage = userRepository.findAllByDeleted(PageRequest.of(page-1, limit, Sort.by("id").descending()), false);
        List<UserDTO> dtoList = new ArrayList<>();
        userPage.getContent().forEach(u -> {
            dtoList.add(beanMapper.map(u, UserDTO.class));
        });
        return new PageDTOGeneric(true, userPage.getTotalElements(), dtoList);
    }

    public Long deleteById(Long id) {
        User user = getUser(id);
        user.setDeleted(true);
        return userRepository.save(user).getId();
    }

    public Long update(UserDTO dto) {
        User user = getUser(dto.getId());
        user.setLogin(dto.getLogin());
        user.setPass(dto.getPass());
        user.setFullName(dto.getFullName());
        return userRepository.save(user).getId();
    }

    public void changeUserStatus(Long id) {
        User user = getUser(id);
        String userStatus = user.getStatus().getName();
        if (userStatus.equalsIgnoreCase(UserActivityStatus.BLOCKED.getName())){
            user.setStatus(UserActivityStatus.ACTIVE);
        } else {
            user.setStatus(UserActivityStatus.BLOCKED);
        }
        userRepository.save(user);
    }




    //    *************************** DELETE ********************





    public User findDummyTestUser(){
        Iterable<User> all = userRepository.findAll();
        return all.iterator().next();
    }

    public Long findDummyTestUserId(){
        Iterable<User> all = userRepository.findAll();
        return all.iterator().next().getId();
    }

}
