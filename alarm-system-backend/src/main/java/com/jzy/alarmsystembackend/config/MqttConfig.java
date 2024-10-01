package com.jzy.alarmsystembackend.config;

import cn.hutool.json.JSONUtil;
import com.jzy.alarmsystembackend.mapper.alarm.AlarmParticularsMapper;
import com.jzy.alarmsystembackend.pojo.DO.alarm.AlarmParticulars;
import com.jzy.alarmsystembackend.pojo.VO.mqtt.MqttParamVO1;
import com.jzy.alarmsystembackend.service.alarm.particulars.AlarmParticularsService;
import com.jzy.alarmsystembackend.service.impl.alarm.particulars.AlarmProcessingService;
import com.jzy.alarmsystembackend.util.AbstractSimpleBufferedConsumer;
import com.jzy.alarmsystembackend.util.AlarmParticularsBufferedConsumer;
import com.jzy.alarmsystembackend.util.RedisCache;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class MqttConfig {

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{"tcp://broker.emqx.io:1883"});
        options.setUserName("user1");
        options.setPassword("123456".toCharArray());
        options.setConnectionTimeout(30); // 设置连接超时
        options.setAutomaticReconnect(true); // 自动重连
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter("mqttx_15d0a079", mqttClientFactory(),
                        "alarm");
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(0); // 根据MQS设置QoS为0
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Autowired
    private AlarmParticularsService alarmParticularsService;

    @Autowired
    private AlarmParticularsMapper alarmParticularsMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private AlarmProcessingService alarmProcessingService;

    @Autowired
    private AbstractSimpleBufferedConsumer abstractSimpleBufferedConsumer;

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
            String payload = message.getPayload().toString();
            System.out.println("Received message: " + payload + " from topic: " + topic);
            MqttParamVO1 param = JSONUtil.toBean(payload, MqttParamVO1.class);

            int min = 0;
            int max = 100;
            int confirm = 50;
            int recover = 25;

            int randomNumber = (int)(Math.random() * (max - min + 1)) + min;

            Boolean flag1 = Boolean.FALSE;
            Boolean flag2 = Boolean.FALSE;
            Timestamp conf = null;
            Timestamp reco = null;

            if (randomNumber < confirm) {
                flag1 = Boolean.TRUE;
                conf = new Timestamp(param.getOccurTime().getTime() + 10 * 1000);

                if (randomNumber < recover) {
                    flag2 = Boolean.TRUE;
                    reco = new Timestamp(param.getOccurTime().getTime() + 20 * 1000);
                }
            }

            AlarmParticulars alarmParticulars = new AlarmParticulars(null, param.getId(), param.getSource(), param.getType(), param.getLevel(), param.getOccurTime(), conf, reco, flag1, flag2, null);
//            try {
//                alarmParticularsMapper.insert(alarmParticulars);
//                log.info("Inserted new alarm: {}", alarmParticulars);
//
//                // 将报警数据缓存到 Redis
//                String redisKey = "alarm:" + alarmParticulars.getId();
//                redisCache.setCacheObject(redisKey, alarmParticulars);
//                // 可选：设置过期时间
//                redisCache.expire(redisKey, 10, TimeUnit.SECONDS);
//                log.info(redisKey + "保存成功");
//            } catch (Exception e) {
//                log.error("Failed to insert alarm: {}", e.getMessage(), e);
//            }

            AlarmParticularsBufferedConsumer.getAlarmParticularsBufferedConsumer().setMaxSize(10);
            abstractSimpleBufferedConsumer.add(alarmParticulars);
        };
    }
}
