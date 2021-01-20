package matching;

import matching.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyHandler{
    @Autowired GiftCouponRepository GiftCouponRepository;
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverVisitAssigned_(@Payload VisitAssigned visitAssigned) {

        if(visitAssigned.isMe()){
            System.out.println("##### listener wheneverVisitAssigned  : " + visitAssigned.toJson());

            GiftCouponRepository.findById(visitAssigned.getMatchId()).ifPresent(GiftCoupon ->{
                GiftCoupon.setMatchId(visitAssigned.getMatchId());
                GiftCoupon.setCouponStatus("Published");
                GiftCouponRepository.save(GiftCoupon);
            });

        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverVisitCanceled_(@Payload VisitCanceled visitCanceled){

        if(visitCanceled.isMe()){
            System.out.println("##### listener  : " + visitCanceled.toJson());


            GiftCouponRepository.findById(visitCanceled.getMatchId()).ifPresent(GiftCoupon->{
                GiftCoupon.setMatchId(visitCanceled.getMatchId());
                GiftCouponRepository.delete(GiftCoupon);
            });


        }

    }

}