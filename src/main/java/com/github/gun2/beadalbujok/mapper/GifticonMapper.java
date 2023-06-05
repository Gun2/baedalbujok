package com.github.gun2.beadalbujok.mapper;

import com.github.gun2.beadalbujok.domain.Gifticon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GifticonMapper {

    Optional<Gifticon> findBySerialNumber(String serialNumber);
    int updateUseAndUseMemberIdById(String use, Integer useMemberId, Integer id);

    List<Gifticon> findAll();

    int insert(Gifticon gifticon);

}




