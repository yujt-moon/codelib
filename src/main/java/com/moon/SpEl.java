package com.moon;

import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yujiangtao
 * @date 2021/12/23 上午11:18
 */
public class SpEl {

    @Test
    public void calculate() {
        // 创建解析器
        SpelExpressionParser parser = new SpelExpressionParser();
        // 创建解析器上下文
        ParserContext context = new TemplateParserContext("#{", "}");

        // 创建表达式计算上下文
        StandardEvaluationContext evaluationContext = new StandardEvaluationContext();

        Map<String, Object> map = new HashMap<>();
        map.put("a", 10);
        map.put("b", 21);

        JSONObject object = new JSONObject();
        object.put("a", 10);

        evaluationContext.setRootObject(new SpEl.Values(10, 21));
        //evaluationContext.setVariable("name", "lisi");

        Expression expression = parser.parseExpression("a+b");

        String value = expression.getValue(evaluationContext, String.class);
        System.out.println(value);
    }

    private static class Values {

        public int a;

        public int b;

        public Values(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
}
