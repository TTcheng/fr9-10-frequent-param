
package com.hand.common.exception;

/**
 * 自定义异常
 */
public class BackendException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private String code;
    private String msg;

    public BackendException(String code, String msg) {
        super(code);
        this.code = code;
        this.msg = msg;
    }

    public BackendException(String code, Throwable e) {
        super(code, e);
        this.code = code;
    }

    public BackendException(String code) {
        super(code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
