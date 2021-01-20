package matching;

public class GiftCanceled extends AbstractEvent {

    private Long matchId;

    public Long getId() {
        return matchId;
    }

    public void setId(Long matchId) {
        this.matchId = matchId;
    }
}