package com.github.gun2.beadalbujok.dto;

import com.github.gun2.beadalbujok.domain.Gifticon;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@ToString
public class GifticonDto implements DomainDto<Gifticon> {

    /**
     *
     */
    private Integer id;

    /**
     * 리워드 포인트
     */
    private Long amount;

    /**
     * 사용유무
     */
    private Boolean use;

    /**
     * 사용자 member id
     */
    private Integer useMemberId;
    private MemberDto useMember;

    /**
     * 기프티콘 번호
     */
    private String serialNumber;

    /**
     *
     */
    private Date createdDate;

    /**
     *
     */
    private Date updatedDate;


    public GifticonDto(Gifticon gifticon) {
        this.id = gifticon.getId();
        this.amount = gifticon.getAmount();
        this.use = "1".equals(gifticon.getUse());
        this.useMemberId = gifticon.getUseMemberId();
        if(gifticon.getUseMember() != null){
            this.useMember = new MemberDto(gifticon.getUseMember());
        }
        this.serialNumber = gifticon.getSerialNumber();
        this.createdDate = gifticon.getCreatedDate();
        this.updatedDate = gifticon.getUpdatedDate();
    }

    @Override
    public Gifticon toVo() {
        return Gifticon.builder()
                .id(this.id)
                .amount(this.amount)
                .use(this.use ? "1" : "0")
                .useMemberId(this.useMemberId)
                .serialNumber(this.serialNumber)
                .createdDate(this.createdDate)
                .updatedDate(this.updatedDate)
                .build();
    }

    @Getter
    @Setter
    @ToString
    public static class UseRequest{
        @NotBlank
        private String serialNumber;
    }
}
