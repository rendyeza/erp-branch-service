package id.nexbyte.erp.branchservice.repository;

import id.nexbyte.erp.branchservice.model.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BranchRepository extends JpaRepository<Branch, UUID> {
    boolean existsBranchByName(String name);
    Page<Branch> findAllByName(String name, Pageable pageable);
}
