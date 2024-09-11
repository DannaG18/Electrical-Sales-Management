package com.esms.branch.application;

import com.esms.branch.domain.entity.Branch;
import com.esms.branch.domain.service.BranchService;

public class CreateBranchUC {
    private BranchService branchService;

    public CreateBranchUC(BranchService branchService) {
        this.branchService = branchService;
    }

    public void execute(Branch branch) {
        branchService.createBranch(branch);
    }
}
