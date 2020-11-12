package id.nexbyte.erp.branchservice.service;

import id.nexbyte.erp.branchservice.mapper.BranchMapper;
import id.nexbyte.erp.branchservice.model.Branch;
import id.nexbyte.erp.branchservice.repository.BranchRepository;
import id.nexbyte.erp.core.dto.BranchDto;
import id.nexbyte.erp.core.dto.BranchPagedList;
import id.nexbyte.erp.core.exception.AlreadyExistsException;
import id.nexbyte.erp.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;
    private final BranchMapper mapper;

    @Override
    public BranchPagedList getList(String branchName, PageRequest pageRequest) {
        BranchPagedList pagedList;
        Page<Branch> page;

        if (StringUtils.isNotEmpty(branchName)) {
            page = branchRepository.findAllByName(branchName, pageRequest);
        }
        else {
            page = branchRepository.findAll(pageRequest);
        }

        pagedList = new BranchPagedList(
                page.getContent()
                .stream()
                .map(mapper::branchToBranchDto)
                .collect(Collectors.toList()),
                PageRequest.of(page.getPageable().getPageNumber(), page.getPageable().getPageSize()),
                page.getTotalElements()
        );

        return pagedList;
    }

    @Override
    public BranchDto getById(UUID branchId) {
        return mapper.branchToBranchDto(
                branchRepository.findById(branchId).orElseThrow(NotFoundException::new)
        );
    }

    @Override
    public BranchDto create(BranchDto branchDto) {
        boolean isExist = branchRepository.existsBranchByName(branchDto.getName());
        if (isExist) {
            throw new AlreadyExistsException();
        }

        Branch branch = branchRepository.save(mapper.branchDtoToBranch(branchDto));
        return mapper.branchToBranchDto(branch);
    }

    @Override
    public BranchDto update(UUID branchId, BranchDto branchDto) {
        return null;
    }
}
