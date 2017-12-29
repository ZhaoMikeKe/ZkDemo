package com.architecture.bocom.sdk.mqtt;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author Su
 * @version [版本号，2016/12/29 16:33]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class MqttMessageBean {
    private String topic;
    private String message;

    public MqttMessageBean(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MqttMessageBean{" +
                "topic='" + topic + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
