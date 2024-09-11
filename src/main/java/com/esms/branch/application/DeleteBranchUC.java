package com.esms.branch.application;

import com.esms.branch.domain.service.BranchService;

public class DeleteBranchUC {
    private BranchService branchService;

    public DeleteBranchUC(BranchService branchService) {
        this.branchService = branchService;
    }

    public void execute(int id) {
        branchService.deleteBranch(id);
    }
}
