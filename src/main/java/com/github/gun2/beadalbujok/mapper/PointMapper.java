package com.github.gun2.beadalbujok.mapper;

import com.github.gun2.beadalbujok.domain.Point;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PointMapper {

    List<Point> findAll();
    Optional<Point> findByMemberId(Integer memberId);
    Optional<Point> findById(Integer id);
    int insert(Point point);

    /**
     * point를 증가시킨다
     * @param amount 증가 량
     * @param id
     * @return
     */
    int plusBalanceById(Long amount, Integer id);


}




