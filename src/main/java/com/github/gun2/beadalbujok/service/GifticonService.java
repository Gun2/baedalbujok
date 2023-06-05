package com.github.gun2.beadalbujok.service;

import com.github.gun2.beadalbujok.domain.Gifticon;
import com.github.gun2.beadalbujok.dto.GifticonDto;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface GifticonService {

    Optional<Gifticon> findBySerialNumber(String serialNumber);

    @Transactional
    Gifticon use(Authentication authentication,
                 GifticonDto.UseRequest useRequest);

    List<GifticonDto> findAllEager();

    void createGificon();
}
