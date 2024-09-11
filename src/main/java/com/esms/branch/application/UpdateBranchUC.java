package com.esms.branch.application;

import com.esms.branch.domain.entity.Branch;
import com.esms.branch.domain.service.BranchService;

public class UpdateBranchUC {
    private BranchService branchService;

    public UpdateBranchUC(BranchService branchService) {
        this.branchService = branchService;
    }

    public void execute(Branch branch){ 
        branchService.updateBranch(branch);
    }
}
