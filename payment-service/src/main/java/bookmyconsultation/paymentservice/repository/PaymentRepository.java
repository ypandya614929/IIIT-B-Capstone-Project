package bookmyconsultation.paymentservice.repository;

import bookmyconsultation.paymentservice.entity.PaymentEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PaymentRepository extends MongoRepository<PaymentEntity, String> {
}