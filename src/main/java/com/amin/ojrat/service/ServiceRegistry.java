package com.amin.ojrat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceRegistry {

    private final AdminService adminService;

    private final ExpertService expertService;

    private final CodeValidationService codeValidationService;

    private final UserService userService;

    private final BranchService branchService;

    @Autowired
    public ServiceRegistry(AdminService adminService,
                           ExpertService expertService,
                           CodeValidationService codeValidationService,
                           UserService userService,
                           BranchService branchService) {
        this.adminService = adminService;
        this.expertService = expertService;
        this.codeValidationService = codeValidationService;
        this.userService = userService;
        this.branchService = branchService;
    }

    public AdminService getAdminService() {
        return adminService;
    }

    public ExpertService getExpertService() {
        return expertService;
    }

    public CodeValidationService getCodeValidationService() {
        return codeValidationService;
    }

    public UserService getUserService() {
        return userService;
    }

    public BranchService getBranchService(){
        return branchService;
    }
}
