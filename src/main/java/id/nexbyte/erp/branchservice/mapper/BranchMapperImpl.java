package id.nexbyte.erp.branchservice.mapper;

import id.nexbyte.erp.branchservice.model.Branch;
import id.nexbyte.erp.core.dto.BranchDto;
import id.nexbyte.erp.core.mapper.DateMapper;
import org.springframework.stereotype.Component;

@Component
public class BranchMapperImpl implements BranchMapper {

    @Override
    public BranchDto branchToBranchDto(Branch branch) {
        if (branch == null) return null;

        return BranchDto.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phoneNumber(branch.getPhoneNumber())
                .managerId(branch.getManagerId())
                .createdAt(DateMapper.localToOffset(branch.getCreatedAt()))
                .createdBy(branch.getCreatedBy())
                .updatedAt(DateMapper.localToOffset(branch.getUpdatedAt()))
                .updatedBy(branch.getUpdatedBy())
                .build();
    }

    @Override
    public Branch branchDtoToBranch(BranchDto branchDto) {
        if (branchDto == null) return null;

        Branch branch = new Branch(
                branchDto.getName(), branchDto.getAddress(), branchDto.getPhoneNumber(), branchDto.getManagerId(),
                branchDto.getCreatedBy(), branchDto.getUpdatedBy()
        );
        branch.setId(branchDto.getId());
        branch.setCreatedBy(branchDto.getCreatedBy());
        branch.setUpdatedBy(branchDto.getUpdatedBy());

        return branch;
    }
}
