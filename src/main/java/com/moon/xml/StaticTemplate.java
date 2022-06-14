package com.moon.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "request")
public class StaticTemplate {
    /**
     *  模板名称
     */
    @JacksonXmlProperty(localName = "templateName")
    private String templateName;

    /**
     * 模板内容类型 1-文本，2-多媒体，3-卡片
     */
    @JacksonXmlProperty(localName = "contentType")
    private int contentType;

    /**
     * 模板内容
     */
    @JacksonXmlProperty(localName = "bodyTextTemplate")
    @JacksonXmlCData
    private String bodyTextTemplate;

    /**
     * 回落up1.0内容
     */
    @JacksonXmlProperty(localName = "fallbackTemplate")
    private String fallbackTemplate;

    /**
     * 短信回落内容
     */
    @JacksonXmlProperty(localName = "smsTemplate")
    private String smsTemplate;

    /**
     * 是否提交审核，缺省false
     */
    @JacksonXmlProperty(localName = "submit")
    private boolean submit = true;

    /**
     * 模板Id
     */
    @JacksonXmlProperty(localName = "templateID")
    private String templateID;
}
