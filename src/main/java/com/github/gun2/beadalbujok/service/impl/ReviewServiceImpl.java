package com.github.gun2.beadalbujok.service.impl;

import com.github.gun2.beadalbujok.app.AppInfo;
import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.dto.ReviewDto;
import com.github.gun2.beadalbujok.mapper.ReviewMapper;
import com.github.gun2.beadalbujok.service.MemberService;
import com.github.gun2.beadalbujok.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final MemberService memberService;
    private final AppInfo appInfo;

    @Override
    public List<ReviewDto.SearchResponse> search(ReviewDto.SearchRequest searchRequest){
        return reviewMapper.search(searchRequest);
    }

    @Override
    @Transactional
    public void insert(Authentication authentication, ReviewDto.FormRequest formRequest)
            throws IOException {
        Member member = memberService.findByUsername(authentication);
        formRequest.setMemberId(member.getId());
        MultipartFile imageFile = formRequest.getImageFile();
        if (imageFile != null){
            //이미지 파일 이름 가져오기
            String filename = imageFile.getOriginalFilename();
            //이미지 저장
            imageFile.transferTo(new File(appInfo.getUploadDir() + "/" + filename));
            formRequest.setImageName(filename);
        }
        reviewMapper.insert(formRequest.toVo());
    }
}




