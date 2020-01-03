package com.whoiszxl.core.entity.base;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @description: rpc请求参数实体
 * @author: whoiszxl
 * @create: 2019-08-31
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder
public class RpcParams {

    /** 方法 */
    private String method;

    /** json id */
    private Integer id = 1;

    private Object[] params;

    /**
     * 拼装返回rpc参数
     * @return
     */
    public Map get() {
        Map<String, Object> result = Maps.newHashMap();
        result.put("method", method);
        result.put("id", id);
        result.put("params", params);
        return result;
    }
}
