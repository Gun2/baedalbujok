package com.github.gun2.beadalbujok.service;

import com.github.gun2.beadalbujok.domain.Point;
import com.github.gun2.beadalbujok.dto.PointDto;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PointService {

    Optional<Point> findByMemberId(Integer memberId);

    int insert(Point point);

    int initInsert(Integer memberId);

    int plusBalanceById(Long amount, Integer id);

    @Transactional
    void remittance(Integer sourceMemberId,
                    Integer targetMemberId,
                    Long amount);

    @Transactional
    void present(Authentication authentication,
                 PointDto.PresentRequest presentRequest);

    List<Point> findAllEager();
}
