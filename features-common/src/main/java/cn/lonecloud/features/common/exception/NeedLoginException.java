package cn.lonecloud.features.common.exception;

/**
 * TODO
 *
 * @author lonecloud <lonecloud@aliyun.com>
 * @date 2020/5/24 9:26
 * @since v1.0
 */
public class NeedLoginException extends BusinessException {
    public NeedLoginException(String message) {
        super(message);
    }
}
