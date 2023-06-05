package com.github.gun2.beadalbujok.mapper;

import com.github.gun2.beadalbujok.domain.Review;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;

/**
 * 리뷰 생성기
 */
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class GenReviewMapperTest {

    @Autowired
    ReviewMapper reviewMapper;

    String[] star0 = {"최악의 경험. 주문한 음식이 완전히 망가졌어요.","전혀 맛이 없었습니다. 돈이 아까웠어요.","음식이 너무 늦게 도착했고, 완전히 차가웠어요.","서비스도 엉망이었고, 음식도 실망스러웠어요.","절대로 다시 주문하지 않을 것입니다. 최악의 경험이었습니다.","음식이 너무 맛이 없었습니다. 정말 실망했어요.","주문한 음식이 누출되어 왔어요. 매우 불쾌했습니다.","음식이 완전히 망가진 상태로 도착했어요.","주문한 음식이 전혀 맞지 않았고, 맛도 없었어요.","음식의 퀄리티가 형편없었고, 서비스도 최악이었습니다."};
    String[] star1 = {"음식이 맛이 없어서 먹기 힘들었어요.","음식이 완전히 차가웠고, 서비스도 별로였어요.","주문한 음식이 늦게 도착했고, 맛도 별로였어요.","음식이 매우 짜서 입맛에 맞지 않았습니다.","서비스 태도가 좋지 않았고, 음식도 별로였어요.","음식의 양도 적었고, 맛도 별로였어요.","음식이 싱겁고, 퀄리티도 많이 떨어졌어요.","주문한 음식이 맛이 없었고, 배달이 매우 느렸어요.","음식이 전혀 맛있지 않았고, 퀄리티도 많이 떨어졌어요.","주문한 음식이 완전히 다른 것이 왔고, 맛도 별로였어요."};
    String[] star2 = {"음식의 맛은 그저 그랬어요. 기대한 만큼의 풍부한 맛은 아니었습니다.","서비스는 평범했지만, 음식의 퀄리티가 아쉬웠어요.","주문한 음식이 그저 그랬어요. 특별한 매력을 찾기 어려웠습니다.","음식은 그럭저럭 괜찮았지만, 기대한 만큼의 만족은 얻지 못했어요.","맛은 있었지만, 음식의 양이 조금 부족했습니다. 배고픔을 완전히 해소하지 못했어요.","음식은 평범한 수준이었고, 특별한 매력을 느끼지 못했습니다.","음식의 맛은 그저 그랬어요. 특별한 인상을 남기지 않았습니다.","서비스는 별로였고, 음식의 퀄리티도 아쉬웠어요. 다른 곳에서 시도해볼 만한 가치는 없었습니다.","주문한 음식이 그저 그랬어요. 약간 실망스러웠습니다.","음식은 평범했고, 특별한 매력을 찾기 어려웠어요. 다음 번에는 다른 가게를 시도해볼 생각입니다."};
    String[] star3 = {"음식은 그럭저럭 괜찮았지만, 서비스가 아쉬웠어요.","주문한 음식이 적절한 가격에 비해 조금 아쉬웠어요.","음식은 평범했고, 배달 시간도 그럭저럭이었어요.","맛은 있었지만, 음식의 양이 조금 부족했습니다.","음식의 맛은 나쁘지 않았지만, 가격 대비 아쉬웠어요.","음식은 그럭저럭 괜찮았지만, 특별한 매력은 없었어요.","서비스는 만족스럽지 않았지만, 음식은 괜찮았어요.","주문한 음식은 그럭저럭 맛있었지만, 배달이 조금 늦었어요.","음식의 퀄리티는 평범했지만, 서비스는 별로였어요.","음식은 그럭저럭 맛있었지만, 가격이 조금 아쉬웠어요."};
    String[] star4 = {"음식이 맛있었고, 서비스도 만족스러웠어요.","주문한 음식이 신선하고, 배달도 빨리 왔어요.","음식의 맛과 양 모두 만족스러웠습니다.","서비스 태도도 좋았고, 음식도 맛있었어요.","음식의 퀄리티와 가격이 모두 만족스러웠어요.","주문한 음식이 기대 이상이었고, 배달도 빨랐어요.","음식이 신선하고 맛있었으며, 서비스도 좋았어요.","음식의 맛과 가격이 모두 만족스러웠습니다.","서비스가 친절하고, 음식도 맛있어서 좋았어요.","주문한 음식이 훌륭하고, 배달이 정확했어요."};
    String[] star5 = {"음식의 맛과 향이 정말로 탁월했어요. 매 순간 감탄을 금치 못했습니다!","서비스가 매우 친절하고, 음식의 맛도 정말로 훌륭했습니다. 완벽한 조합이었어요.","주문한 음식이 상상을 초월하는 맛이었습니다. 완전한 만족을 느낄 수 있었어요.","음식의 퀄리티와 다양한 맛의 조화가 완벽했습니다. 정말로 매우 훌륭한 경험이었어요.","최고의 음식을 경험한 것 같아요. 신선한 재료와 완벽한 조리법이 참신함을 느끼게 해줬어요.","음식이 정말로 맛있고 신선했어요. 가게의 전문성과 정성이 돋보였습니다.","음식의 맛과 퀄리티가 너무나 탁월해서 기분이 너무 좋았어요. 반복 주문 의사가 높아졌습니다.","서비스가 매우 훌륭했고, 음식의 맛도 최고 수준이었습니다. 완벽한 배달 음식 경험이었습니다.","음식이 정말로 맛있었고, 특별한 재료와 조리법이 돋보였습니다. 정말로 훌륭한 가게입니다.","음식의 맛, 퀄리티, 서비스 모두 최고였어요. 완벽한 배달 음식 경험을 선사해준 가게입니다."};
    @Test
    @Commit
    void genReview() {
        /** given */
        List<Integer> menuIdList = Arrays.asList(11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22);
        List<Integer> memberIdList = Arrays.asList(10, 11, 12, 13, 14, 15);
        List<FakeReview> fakeReviewList = Arrays.asList(
                new FakeReview(0, Arrays.stream(star0).toList()),
                new FakeReview(20, Arrays.stream(star1).toList()),
                new FakeReview(40, Arrays.stream(star2).toList()),
                new FakeReview(60, Arrays.stream(star3).toList()),
                new FakeReview(80, Arrays.stream(star4).toList()),
                new FakeReview(100, Arrays.stream(star5).toList())
        );

        /** when */
        for (int i = 0; i < 500; i++) {
            FakeReview fakeReview = getRandom(fakeReviewList);
            String content = getRandom(fakeReview.contentList);
            Integer memberId = getRandom(memberIdList);
            Integer menuId = getRandom(menuIdList);
            reviewMapper.insert(Review.builder()
                            .content(content)
                            .memberId(memberId)
                            .menuId(menuId)
                            .evaluation(fakeReview.eval)
                    .build());
        }
    }

    class FakeReview{
        int eval;
        List<String> contentList;

        public FakeReview(int eval, List<String> contentList) {
            this.eval = eval;
            this.contentList = contentList;
        }
    }

    public static <T> T getRandom(List<T> list){
        Random random = new Random();
        int nextInt = random.nextInt(list.size());
        return list.get(nextInt);
    }
}