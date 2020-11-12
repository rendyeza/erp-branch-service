package id.nexbyte.erp.branchservice.controller;

import id.nexbyte.erp.branchservice.service.BranchService;
import id.nexbyte.erp.core.constant.DefaultPage;
import id.nexbyte.erp.core.dto.BranchDto;
import id.nexbyte.erp.core.dto.BranchPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/branch")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping("/{branchId}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable("branchId") UUID branchId) {
        return new ResponseEntity<>(branchService.getById(branchId), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<BranchPagedList> getBranches(
            @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", required = false) Integer pageSize,
            @RequestParam(value = "name", required = false) String branchName
    ) {
        if (pageNumber == null || pageNumber < 0){
            pageNumber = DefaultPage.PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1) {
            pageSize = DefaultPage.PAGE_SIZE;
        }

        return new ResponseEntity<>(
                branchService.getList(branchName, PageRequest.of(pageNumber, pageSize)),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Object> addNewBranch(@RequestBody @Validated BranchDto branchDto) {
        UUID userId = UUID.randomUUID();
        branchDto.setCreatedBy(userId);
        branchDto.setUpdatedBy(userId);

        log.info("Incoming request: {}", branchDto);

        branchDto = branchService.create(branchDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{branchId}")
                .buildAndExpand(branchDto.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
