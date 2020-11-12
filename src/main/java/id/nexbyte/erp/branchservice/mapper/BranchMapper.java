package id.nexbyte.erp.branchservice.mapper;

import id.nexbyte.erp.branchservice.model.Branch;
import id.nexbyte.erp.core.dto.BranchDto;

public interface BranchMapper {
    BranchDto branchToBranchDto(Branch branch);

    Branch branchDtoToBranch(BranchDto branchDto);
}
