package com.amin.ojrat.repository.impl;

import com.amin.ojrat.dto.entity.user.request.UserLoginDtoParam;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.repository.ICustomUserRepository;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomUserRepositoryImpl implements ICustomUserRepository {

    @PersistenceContext
    private  EntityManager entityManager;

    @Override
    public boolean isUserExistsByValue(String nationalCode, String email, String phoneNumber) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
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

        return entityManager.createQuery(cq).getResultList().size() > 0;
    }

    @Override
    public User login(UserLoginDtoParam param) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
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
            return entityManager.createQuery(cq).getSingleResult();

        } catch (NoResultException e) {
            throw new EntityNotFoundException("user not found");
        }

    }
}
