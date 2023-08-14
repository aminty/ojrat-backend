package com.amin.ojrat.repository;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import jakarta.persistence.*;


@Repository
@EnableJpaRepositories
@EntityScan("com.amin.ojrat.entity")
public class DaoRepositories {

    @Autowired
    public DaoRepositories(IAdminRepository adminRepository,
                           IExpertRepository expertRepository,
                           IMessageRepository messageRepository,
                           IOrderRepository orderRepository,
                           IProductRepository productRepository,
                           IServiceRepository serviceRepository,
                           IUserRepository userRepository,
                           IBranchRepository branchRepository) {
        this.adminRepository = adminRepository;
        this.expertRepository = expertRepository;
        this.messageRepository = messageRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.serviceRepository = serviceRepository;
        this.userRepository = userRepository;
        this.branchRepository = branchRepository;
    }



    @PersistenceContext
    private EntityManager entityManager;

    private IAdminRepository adminRepository;

    private IExpertRepository expertRepository;

    private IMessageRepository messageRepository;

    private IOrderRepository orderRepository;

    private IProductRepository productRepository;

    private IServiceRepository serviceRepository;

    private IUserRepository userRepository;

    private IBranchRepository branchRepository;

    public IAdminRepository getAdminRepository() {
        return adminRepository;
    }

    public IExpertRepository getExpertRepository() {
        return expertRepository;
    }

    public IMessageRepository getMessageRepository() {
        return messageRepository;
    }

    public IOrderRepository getOrderRepository() {
        return orderRepository;
    }

    public IProductRepository getProductRepository() {
        return productRepository;
    }

    public IServiceRepository getServiceRepository() {
        return serviceRepository;
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public IBranchRepository getBranchRepository() {
        return branchRepository;
    }
    public EntityManager getEntityManager() {
        return entityManager;
    }
}
