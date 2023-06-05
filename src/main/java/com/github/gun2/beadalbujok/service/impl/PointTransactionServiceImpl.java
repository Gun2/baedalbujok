package com.github.gun2.beadalbujok.service.impl;

import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.domain.Point;
import com.github.gun2.beadalbujok.domain.PointTransaction;
import com.github.gun2.beadalbujok.mapper.PointTransactionMapper;
import com.github.gun2.beadalbujok.service.MemberService;
import com.github.gun2.beadalbujok.service.PointService;
import com.github.gun2.beadalbujok.service.PointTransactionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PointTransactionServiceImpl implements PointTransactionService {

    private final MemberService memberService;
    private final PointTransactionMapper pointTransactionMapper;
    private final PointService pointService;

    @Override
    public List<PointTransaction> findAllByAuthentication(Authentication authentication){
        Member member = memberService.findByUsername(authentication);

        Point point = pointService.findByMemberId(member.getId()).orElseThrow();
        List<PointTransaction> allByPointId = pointTransactionMapper.findAllByPointId(point.getId());
        Map<Integer, Point> pointMap = pointService.findAllEager().stream()
                .collect(Collectors.toMap(Point::getId, d -> d));

        for (PointTransaction pointTransaction : allByPointId) {
            pointTransaction.setTargetPoint(
                    pointMap.getOrDefault(pointTransaction.getTargetPointId(), new Point())
            );
        }
        return allByPointId;
    }


}




