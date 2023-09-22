package com.github.gun2.beadalbujok.service.impl;

import com.github.gun2.beadalbujok.domain.Gifticon;
import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.domain.Point;
import com.github.gun2.beadalbujok.dto.GifticonDto;
import com.github.gun2.beadalbujok.exception.NotFoundGifticonException;
import com.github.gun2.beadalbujok.mapper.GifticonMapper;
import com.github.gun2.beadalbujok.service.GifticonService;
import com.github.gun2.beadalbujok.service.MemberService;
import com.github.gun2.beadalbujok.service.PointService;
import com.github.gun2.beadalbujok.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GifticonServiceImpl implements GifticonService {
    private final GifticonMapper gifticonMapper;
    private final MemberService memberService;
    private final PointService pointService;


    @Override
    public Optional<Gifticon> findBySerialNumber(String serialNumber){
        Optional<Gifticon> bySerialNumber = gifticonMapper.findBySerialNumber(serialNumber);
        return bySerialNumber;
    }

    @Transactional
    @Override
    public Gifticon use(Authentication authentication,
                        GifticonDto.UseRequest useRequest){
        Member member = memberService.findByUsername(authentication);
        Gifticon gifticon = gifticonMapper.findBySerialNumber(useRequest.getSerialNumber().toUpperCase(Locale.ROOT))
                .orElseThrow(NotFoundGifticonException::new);
        if("1".equals(gifticon.getUse())){
            throw new NotFoundGifticonException();
        }
        Long amount = gifticon.getAmount();
        Point point = pointService.findByMemberId(member.getId()).get();
        pointService.plusBalanceById(amount, point.getId());
        gifticonMapper.updateUseAndUseMemberIdById("1", member.getId(), gifticon.getId());
        return gifticon;
    }


    @Override
    public List<GifticonDto> findAllEager(){
        List<Gifticon> all = gifticonMapper.findAll();
        Map<Integer, Member> memberMap = memberService.findAll().stream()
                .collect(Collectors.toMap(Member::getId, d -> d));
        for (Gifticon gifticon : all) {
            gifticon.setUseMember(memberMap.getOrDefault(gifticon.getUseMemberId(), new Member()));
        }
        return all.stream().map(GifticonDto::new).toList();
    }

    /**
     * 기프티콘을 생성한다.
     */
    @Override
    public void createGificon(){
        gifticonMapper.insert(Gifticon.builder()
                        .amount(50000L)
                        .serialNumber(StringUtil.createUUID12())
                .build());
    }
}




