package com.github.gun2.beadalbujok.service;

import com.github.gun2.beadalbujok.domain.PointTransaction;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface PointTransactionService {

    List<PointTransaction> findAllByAuthentication(Authentication authentication);
}
