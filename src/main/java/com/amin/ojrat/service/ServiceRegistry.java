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

    private final ExpertBranchRequestService expertBranchService;

    private final ExpertDiscountService expertDiscountService;

    private final MessageService messageService ;

    @Autowired
    public ServiceRegistry(AdminService adminService,
                           ExpertService expertService,
                           CodeValidationService codeValidationService,
                           UserService userService,
                           BranchService branchService, ExpertBranchRequestService expertBranchService, ExpertDiscountService expertDiscountService, MessageService messageService) {
        this.adminService = adminService;
        this.expertService = expertService;
        this.codeValidationService = codeValidationService;
        this.userService = userService;
        this.branchService = branchService;
        this.expertBranchService = expertBranchService;
        this.expertDiscountService = expertDiscountService;
        this.messageService = messageService;
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

    public ExpertBranchRequestService getExpertBranchService() {
        return expertBranchService;
    }

    public ExpertDiscountService getExpertDiscountService() {
        return expertDiscountService;
    }

    public MessageService getMessageService() {
        return messageService;
    }
}
