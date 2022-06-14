package com.moon.mybatis.config;

import com.moon.util.StringUtils;

import java.util.List;

/**
 * 对应于 <resultMap></resultMap>
 *
 * @author yujiangtao
 * @date 2020/9/9 下午9:04
 */
public class ResultMap {

    private Configuration configuration;

    private String id;

    /**
     * 结果类型（类的权限定名）
     */
    private Class<?> resultType;

    /**
     * 类型的别名
     */
    private String type;

    private List<ResultMapping> resultMappings;

    private ResultMap() {}

    public static class Builder {

        private ResultMap resultMap = new ResultMap();

        public Builder(String id, Class<?> resultType, List<ResultMapping> resultMappings, Configuration configuration) {
            assert StringUtils.isNotBlank(id);
            resultMap.id = id;
            resultMap.resultType = resultType;
            resultMap.resultMappings = resultMappings;
            resultMap.configuration = configuration;
        }

        public ResultMap build() {
            return resultMap;
        }
    }
}
