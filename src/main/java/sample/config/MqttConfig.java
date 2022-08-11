package sample.config;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.UUID;

/**
 * @author bianjinyue
 * @Description
 * @date 2022-07-21 13:45
 */
@Configuration
public class MqttConfig {

    @Bean
    public MqttClient rmqxClient() throws MqttException {
        MqttClient mqttClient = new MqttClient("tcp://rmqx.iwosai.com:1883", UUID.randomUUID().toString());
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setKeepAliveInterval(60);
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setUserName("unmurphy");
        mqttConnectOptions.setPassword("123456".toCharArray());
        IMqttToken iMqttToken = mqttClient.connectWithResult(mqttConnectOptions);
        if(iMqttToken.isComplete()){
            System.out.println("success");
        }
        mqttClient.subscribe("/device/123", new IMqttMessageListener() {
            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println(s);
                System.out.println("payload = " + Arrays.toString(mqttMessage.getPayload()));
            }
        });
        return mqttClient;
    }
}
