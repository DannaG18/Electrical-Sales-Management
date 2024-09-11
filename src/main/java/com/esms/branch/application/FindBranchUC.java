package com.esms.branch.application;

import java.util.Optional;

import com.esms.branch.domain.entity.Branch;
import com.esms.branch.domain.service.BranchService;

public class FindBranchUC {
    private BranchService branchService;

    public FindBranchUC(BranchService branchService) {
        this.branchService = branchService;
    }

    public Optional<Branch> execute(int id) {
        return branchService.findBranch(id);
    }
}
