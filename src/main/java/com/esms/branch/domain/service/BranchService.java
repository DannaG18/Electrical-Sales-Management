package com.esms.branch.domain.service;

import java.util.List;
import java.util.Optional;

import com.esms.branch.domain.entity.Branch;

public interface BranchService {
    void createBranch (Branch branch);
    void deleteBranch (int id);
    Optional <Branch> findBranch (int id);
    void updateBranch (Branch branch);
    List <Branch> findAllBranch();
}
