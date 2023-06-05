package com.github.gun2.beadalbujok.service.impl;

import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.domain.Point;
import com.github.gun2.beadalbujok.domain.PointTransaction;
import com.github.gun2.beadalbujok.dto.PointDto;
import com.github.gun2.beadalbujok.exception.NotEnoughPointException;
import com.github.gun2.beadalbujok.exception.NotFoundMemberException;
import com.github.gun2.beadalbujok.mapper.PointMapper;
import com.github.gun2.beadalbujok.mapper.PointTransactionMapper;
import com.github.gun2.beadalbujok.service.MemberService;
import com.github.gun2.beadalbujok.service.PointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PointServiceImpl implements PointService {

    private final MemberService memberService;
    private final PointMapper pointMapper;
    private final PointTransactionMapper pointTransactionMapper;

    @Lazy
    public PointServiceImpl(MemberService memberService,
                            PointMapper pointMapper,
                            PointTransactionMapper pointTransactionMapper) {
        this.memberService = memberService;
        this.pointMapper = pointMapper;
        this.pointTransactionMapper = pointTransactionMapper;
    }

    @Override
    public Optional<Point> findByMemberId(Integer memberId){
        return pointMapper.findByMemberId(memberId);
    }

    @Override
    public int insert(Point point){
        return pointMapper.insert(point);
    }

    @Override
    public int initInsert(Integer memberId){
        Point point = new Point();
        point.setBalance(100000L);
        point.setMemberId(memberId);
        return pointMapper.insert(point);
    }

    @Override
    public int plusBalanceById(Long amount, Integer id){
        return pointMapper.plusBalanceById(amount, id);
    }

    /**
     * 포인트 송금
     * @param sourceMemberId 보내는 사용자 id
     * @param targetMemberId 받는 사용자 id
     * @param amount 송금량
     * @return
     */
    @Override
    @Transactional
    public void remittance(Integer sourceMemberId,
                           Integer targetMemberId,
                           Long amount){
        //송금자에게 돈이 있는지
        Point sourcePoint = pointMapper.findByMemberId(sourceMemberId).orElseThrow();
        Point targetPoint = pointMapper.findByMemberId(targetMemberId).orElseThrow();
        if(sourcePoint.getBalance() < amount){
            throw new NotEnoughPointException();
        }
        //송금자 돈 삭감
        this.plusBalanceById(-amount, sourcePoint.getId());
        pointTransactionMapper.insert(PointTransaction.builder()
                .pointId(sourcePoint.getId())
                .amount(-amount)
                .balance(sourcePoint.getBalance() - amount)
                .targetPointId(targetPoint.getId())
                .build());

        //수신자 돈 증가
        this.plusBalanceById(amount, targetPoint.getId());
        pointTransactionMapper.insert(PointTransaction.builder()
                .pointId(targetPoint.getId())
                .amount(amount)
                .balance(targetPoint.getBalance() + amount)
                .targetPointId(sourcePoint.getId())
                .build());
    }


    /**
     * 포인트 선물
     * @param authentication
     * @param presentRequest
     */
    @Override
    @Transactional
    public void present(Authentication authentication,
                        PointDto.PresentRequest presentRequest){
        Member sourceMember = memberService.findByUsername(authentication);
        Member targetMember = memberService.findByUsername(presentRequest.getUsername()).orElseThrow(NotFoundMemberException::new);
        Point point = this.findByMemberId(sourceMember.getId()).orElseThrow();
        if(presentRequest.getPoint() > point.getBalance()){
            throw new NotEnoughPointException();
        }
        this.remittance(sourceMember.getId(), targetMember.getId(), presentRequest.getPoint());
    }

    @Override
    public List<Point> findAllEager(){
        List<Point> all = pointMapper.findAll();
        Map<Integer, Member> memberMap = memberService.findAll().stream()
                .collect(Collectors.toMap(Member::getId, d -> d));
        for (Point point : all) {
            point.setMember(memberMap.getOrDefault(point.getMemberId(), new Member()));
        }
        return all;
    }
}




