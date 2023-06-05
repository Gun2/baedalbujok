package com.github.gun2.beadalbujok.service.impl;

import com.github.gun2.beadalbujok.app.AppInfo;
import com.github.gun2.beadalbujok.domain.Member;
import com.github.gun2.beadalbujok.domain.Menu;
import com.github.gun2.beadalbujok.dto.MenuDto;
import com.github.gun2.beadalbujok.mapper.MenuMapper;
import com.github.gun2.beadalbujok.service.MemberService;
import com.github.gun2.beadalbujok.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;
    private final AppInfo appInfo;
    private final MemberService memberService;

    @Override
    public int insert(MenuDto.FormRequest formRequest,
                      Authentication authentication) throws IOException {
        Member member = memberService.findByUsername(authentication);
        formRequest.setMemberId(member.getId());

        MultipartFile menuImageFile = formRequest.getMenuImageFile();
        if(menuImageFile != null){
            String filename = menuImageFile.getOriginalFilename();
            menuImageFile.transferTo(
                    new File(appInfo.getUploadDir() + "/" + filename)
            );
            formRequest.setImageName(filename);
        }
        return menuMapper.insert(formRequest.toVo());
    }

    @Override
    public int update(MenuDto.FormRequest formRequest,
                      Authentication authentication) throws IOException {
        Member member = memberService.findByUsername(authentication);
        formRequest.setMemberId(member.getId());

        MultipartFile menuImageFile = formRequest.getMenuImageFile();
        if(menuImageFile != null){
            String filename = menuImageFile.getOriginalFilename();
            menuImageFile.transferTo(
                    new File(appInfo.getUploadDir() + "/" + filename)
            );
            formRequest.setImageName(filename);
        }
        return menuMapper.update(formRequest.toVo());
    }

    @Override
    public void delete(Integer id){
        menuMapper.softDelete(id);
    }


    /**
     * 카드 타입의 메뉴 정보 모두 가져오기
     * @return
     */
    @Override
    public List<MenuDto.CardResponse> findAllCardType(){
        return menuMapper.findAllCardType();
    }

    @Override
    public Optional<Menu> findById(Integer id){
        return menuMapper.findById(id);
    }
}




