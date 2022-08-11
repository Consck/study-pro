package sample.application;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author bianjinyue
 * @Description
 * @date 2022-07-21 14:06
 */
@Service
public class MqttManager {
    @Autowired
    private MqttClient mqttClient;


    public void subScript() throws MqttException {
        IMqttToken iMqttToken = mqttClient.subscribeWithResponse("/device/123");
        String payload = Arrays.toString(iMqttToken.getResponse().getPayload());
        System.out.println(payload);

        mqttClient.subscribe("/device/123", new IMqttMessageListener() {
            @Override
            public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                System.out.println(s);
                System.out.println("payload = " + Arrays.toString(mqttMessage.getPayload()));
            }
        });
    }
}
