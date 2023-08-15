package com.amin.ojrat.service.impl;

import com.amin.ojrat.dto.entity.user.request.UserLoginParamDto;
import com.amin.ojrat.dto.entity.user.response.UserLoginResultDto;
import com.amin.ojrat.dto.mapper.UserMapper;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.exception.LoginAuthenticationException;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.UserService;
import jakarta.persistence.*;

import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final DaoRepositories daoRepositories;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(DaoRepositories daoRepositories, UserMapper userMapper) {
        this.daoRepositories = daoRepositories;
        this.userMapper = userMapper;
    }


    @Override
    public boolean isUserExistsByValue(String nationalCode, String email, String phoneNumber) {
        CriteriaBuilder cb = daoRepositories.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);

        List<Predicate> predicates = new ArrayList<>();

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            predicates.add(cb.equal(root.get("phoneNumber"), phoneNumber));
        }
        if (nationalCode != null && !nationalCode.isEmpty()) {
            predicates.add(cb.equal(root.get("nationalCode"), nationalCode));
        }
        if (email != null && !email.isEmpty()) {
            predicates.add(cb.equal(root.get("email"), email));
        }

        Predicate finalPredicate = cb.or(predicates.toArray(new Predicate[0]));

        cq.where(finalPredicate);

        return daoRepositories.getEntityManager().createQuery(cq).getResultList().size() > 0;
    }

    @Override
    public UserLoginResultDto login(UserLoginParamDto param) throws LoginAuthenticationException {

        CriteriaBuilder cb = daoRepositories.getEntityManager().getCriteriaBuilder();
        CriteriaQuery<User> cq = cb.createQuery(User.class);
        Root<User> root = cq.from(User.class);
        List<Predicate> predicates = new ArrayList<>();
        if (param.getUsername() != null && !param.getUsername().isEmpty()) {
            predicates.add(cb.equal(root.get("phoneNumber"), param.getUsername()));
            predicates.add(cb.equal(root.get("email"), param.getUsername()));
            predicates.add(cb.equal(root.get("nationalCode"), param.getUsername()));
        }
        Predicate finalPredicate = cb.or(predicates.toArray(new Predicate[0]));
        cq.where(finalPredicate);

        try {
            User foundUser = daoRepositories.getEntityManager().createQuery(cq).getSingleResult();
            if (!param.getPassword().trim().equals(foundUser.getPassword())) {
                throw new LoginAuthenticationException("username or password is not correct!");
            }
            return userMapper.userToUserLoginResultDto(foundUser);

        } catch (NoResultException e) {
            throw new EntityNotFoundException("user not found");
        }

    }
}
