package bookmyconsultation.paymentservice.repository;

import bookmyconsultation.paymentservice.entity.PaymentServiceEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentServiceRepository extends MongoRepository<PaymentServiceEntity, String> {
}