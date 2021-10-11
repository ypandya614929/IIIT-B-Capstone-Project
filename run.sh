#cd service-registry
#sudo docker build -t bmc/service-registry:1.0.0 .
#cd ..
#
#cd bmc-gateway
#sudo docker build -t bmc/bmc-gateway:1.0.0 .
#cd ..
#
#cd notification-service
#sudo docker build -t bmc/notification-service:1.0.0 .
#cd ..b
#
#cd doctor-service
#sudo docker build -t bmc/doctor-service:1.0.0 .
#cd ..
#
#cd user-service
#sudo docker build -t bmc/user-service:1.0.0 .
#cd ..
#
#cd appointment-service
#sudo docker build -t bmc/appointment-service:1.0.0 .
#cd ..
#
#cd payment-service
#sudo docker build -t bmc/payment-service:1.0.0 .
#cd ..
#
#cd rating-service
#sudo docker build -t bmc/rating-service:1.0.0 .
#cd ..

cd security-provider
sudo docker build -t bmc/security-provider:1.0.0 .
cd ..