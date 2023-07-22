package com.amin.ojrat.service.impl;

import com.amin.ojrat.entity.Admin;
import com.amin.ojrat.entity.User;
import com.amin.ojrat.repository.DaoRepositories;
import com.amin.ojrat.service.UserService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private DaoRepositories daoRepositories;

    @Autowired
    public UserServiceImpl(DaoRepositories daoRepositories) {
        this.daoRepositories = daoRepositories;
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

        return daoRepositories.getEntityManager().createQuery(cq).getResultList().size()>0;
    }
}
