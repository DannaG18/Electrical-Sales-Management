package com.esms.branch.application;

import java.util.List;

import com.esms.branch.domain.entity.Branch;
import com.esms.branch.domain.service.BranchService;

public class FindAllBranchUC {
    private BranchService branchService;

    public FindAllBranchUC(BranchService branchService) {
        this.branchService = branchService;
    }

    public List<Branch> execute() {
        return branchService.findAllBranch();
    }
}
