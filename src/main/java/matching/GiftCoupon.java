package matching;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="GiftCoupon_table")
public class GiftCoupon {

    @Id
    private Long matchId;
    private String couponStatus;

    @PostPersist
    public void onPostPersist(){
        GiftPublished giftPublished = new GiftPublished();
        BeanUtils.copyProperties(this, giftPublished);
        giftPublished.publishAfterCommit();


    }

    @PostRemove
    public void onPostRemove(){
        GiftCanceled giftCanceled = new GiftCanceled();
        BeanUtils.copyProperties(this, giftCanceled);
        giftCanceled.publishAfterCommit();


    }


    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }




}