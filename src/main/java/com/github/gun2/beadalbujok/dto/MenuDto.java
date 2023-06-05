package com.github.gun2.beadalbujok.dto;

import com.github.gun2.beadalbujok.domain.Menu;
import com.github.gun2.beadalbujok.validation.UpdateValidation;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class MenuDto implements DomainDto<Menu> {
    /**
     *
     */
    @NotNull(groups = UpdateValidation.class)
    private Integer id;

    /**
     * 음식명
     */
    @NotBlank
    private String name;

    /**
     * 가격
     */
    @NotNull()
    @Min(0)
    private Long price;

    /**
     * 메뉴 설명
     */
    private String desc;

    /**
     *
     */
    private Date createdDate;

    /**
     *
     */
    private Date updatedDate;

    /**
     * image 파일 이름
     */
    private String imageName;

    /**
     * 메뉴를 생성한 member id
     */
    private Integer memberId;


    public MenuDto(Menu menu) {
        this.id = menu.getId();
        this.name = menu.getName();
        this.price = menu.getPrice();
        this.desc = menu.getDesc();
        this.createdDate = menu.getCreatedDate();
        this.updatedDate = menu.getUpdatedDate();
        this.imageName = menu.getImageName();
        this.memberId = menu.getMemberId();
    }

    @Override
    public Menu toVo() {
        return Menu.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price)
                .desc(this.desc)
                .createdDate(this.createdDate)
                .updatedDate(this.updatedDate)
                .imageName(this.imageName)
                .memberId(this.memberId)
                .build();
    }

    @Getter
    @Setter
    @ToString
    public static class FormRequest extends MenuDto {
        private MultipartFile menuImageFile;

        public FormRequest(Menu menu) {
            super(menu);
        }
        public FormRequest(){
            super(new Menu());
        }
    }

    @Getter
    @Setter
    public static class CardResponse extends MenuDto{
        private Integer reviewCnt;
        private Integer evaluationSum;

        public CardResponse(Menu menu) {
            super(menu);
        }
        public CardResponse(){
            super(new Menu());
        }
    }

}
