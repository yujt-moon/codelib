package com.moon.netty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yujiangtao
 * @date 2021/7/29 下午2:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseSample {

    private String code;

    private String data;

    private Long timestamp;
}
