package com.architecture.bocom.sdk;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author Su
 * @version [版本号，2017/2/16 20:13]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */

public interface OnCallbackListener {
    void success(Object response);

    void failure(Throwable t);
}
