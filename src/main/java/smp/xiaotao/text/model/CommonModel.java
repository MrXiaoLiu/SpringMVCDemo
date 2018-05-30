package smp.xiaotao.text.model;

import smp.xiaotao.text.utils.ConstantUtils;

public class CommonModel {

    private int code;
    private String msg;
    private Object data;


    public void setSuccess(){
        setCode(ConstantUtils.CODE_SUCCESS);
        setMsg(ConstantUtils.MSG_SUCCESS);
    }
    public void setFail(){
        setCode(ConstantUtils.CODE_FAIL);
        setMsg(ConstantUtils.MSG_FAIL);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
