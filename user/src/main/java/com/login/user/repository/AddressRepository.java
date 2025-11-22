package com.login.user.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.login.user.domain.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}