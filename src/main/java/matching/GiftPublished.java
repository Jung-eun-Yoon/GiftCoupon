package matching;

public class GiftPublished extends AbstractEvent {

    private Long matchId;
    private String couponStatus;

    public Long getId() {
        return matchId;
    }

    public void setId(Long matchId) {
        this.matchId = matchId;
    }
    public String getCouponStatus() {
        return couponStatus;
    }

    public void setCouponStatus(String couponStatus) {
        this.couponStatus = couponStatus;
    }
}