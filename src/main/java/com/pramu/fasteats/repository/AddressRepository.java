package com.pramu.fasteats.repository;

import com.pramu.fasteats.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
