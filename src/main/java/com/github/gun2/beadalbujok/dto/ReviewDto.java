package com.github.gun2.beadalbujok.dto;

import com.github.gun2.beadalbujok.domain.Review;
import lombok.*;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 음식 리뷰
 * @TableName review
 */
@Data
@NoArgsConstructor
public class ReviewDto implements DomainDto<Review> {
    /**
     * 
     */
    private Integer id;

    /**
     * 리뷰내용
     */
    @NotBlank
    private String content;

    /**
     * 작성자 member id
     */
    private Integer memberId;

    /**
     * 리뷰 음식 menu id
     */
    @NonNull
    private Integer menuId;

    /**
     * 평점
     */
    @Range(min = 0, max = 100)
    private Integer evaluation;

    /**
     * 
     */
    private Date createdDate;

    /**
     * 
     */
    private Date updatedDate;

    private String imageName;


    @Override
    public Review toVo() {
        return Review.builder()
                .id(this.id)
                .content(this.content)
                .memberId(this.memberId)
                .menuId(this.menuId)
                .evaluation(this.evaluation)
                .createdDate(this.createdDate)
                .updatedDate(this.updatedDate)
                .imageName(this.imageName)
                .build();
    }

    public ReviewDto(Review review) {
        this.id = review.getId();
        this.content = review.getContent();
        this.memberId = review.getMemberId();
        this.menuId = review.getMenuId();
        this.evaluation = review.getEvaluation();
        this.createdDate = review.getCreatedDate();
        this.updatedDate = review.getUpdatedDate();
        this.imageName = review.getImageName();
    }

    @Getter
    @Setter
    @ToString
    public static class SearchRequest{
        @NonNull
        private Integer menuId;
        private String memberName;
    }

    @Getter
    @Setter
    @ToString
    public static class SearchResponse extends ReviewDto{
        private String memberName;
    }

    @Getter
    @Setter
    @ToString
    public static class FormRequest extends ReviewDto{
        private MultipartFile imageFile;

    }
}