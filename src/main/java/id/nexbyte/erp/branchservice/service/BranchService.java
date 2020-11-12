package id.nexbyte.erp.branchservice.service;

import id.nexbyte.erp.core.dto.BranchDto;
import id.nexbyte.erp.core.dto.BranchPagedList;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BranchService {
    BranchPagedList getList(String branchName, PageRequest pageRequest);

    BranchDto getById(UUID branchId);

    BranchDto create(BranchDto branchDto);

    BranchDto update(UUID branchId, BranchDto branchDto);


}
