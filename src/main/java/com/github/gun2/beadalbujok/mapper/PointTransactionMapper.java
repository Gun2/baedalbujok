package com.github.gun2.beadalbujok.mapper;

import com.github.gun2.beadalbujok.domain.PointTransaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface PointTransactionMapper {

    int insert(PointTransaction pointTransaction);

    List<PointTransaction> findAllByPointId(Integer pointId);
}




