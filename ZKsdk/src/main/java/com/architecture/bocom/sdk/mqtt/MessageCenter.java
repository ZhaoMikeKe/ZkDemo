package com.architecture.bocom.sdk.mqtt;

import java.util.Observable;

/**
 * 被观察者
 * 消息中心
 *
 * @author Su
 * @version [版本号，2016/12/30 9:28]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public class MessageCenter extends Observable {
    private static MessageCenter instance = null;

    public static MessageCenter getInstance() {
        if (null == instance) {
            instance = new MessageCenter();
        }
        return instance;
    }

    public void notifyDataChange(MqttMessageBean messageBean) {
        //被观察者通知观察者数据改变了
        setChanged();
        notifyObservers(messageBean);
    }
}
