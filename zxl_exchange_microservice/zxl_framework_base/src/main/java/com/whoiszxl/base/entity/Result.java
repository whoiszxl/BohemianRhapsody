package com.whoiszxl.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Result<T> {

    /** 是否成功 */
    private boolean flag;
    /** 返回码 */
    private Integer code;
    /** 返回信息 */
    private String message;
    /** 返回数据 */
    private T data;

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public static Result buildError() {
        Result result = new Result();
        return result.setFlag(false).setCode(StatusCode.ERROR).setMessage("error");
    }

    public static Result buildSuccess(Object data) {
        Result result = new Result();
        return result.setFlag(true).setCode(StatusCode.OK).setMessage("success").setData(data);
    }

    public static Result buildSuccess() {
        Result result = new Result();
        return result.setFlag(true).setCode(StatusCode.OK).setMessage("success");
    }
}
