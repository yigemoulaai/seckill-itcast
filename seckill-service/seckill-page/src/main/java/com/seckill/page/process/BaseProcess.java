package com.seckill.page.process;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/*****
 * @Author: lichuang
 * @Project: seckillproject
 * @Description: com.seckill.page.process.BaseProcess
 * 通用生成静态页流程-Configuration
 ****/
public class BaseProcess {

    @Autowired
    private Configuration configuration;

    /****
     * 生成静态页
     * 1.模板名称   templateName
     * 2.数据模型-填充模板-------->Map<String,Object>
     * 3.生成的静态页存储的路径 path
     * 4.生成的文件名字    name
     */
    public void writerPage(Map<String,Object> dataMap) throws Exception {
        //模板名称
        String templateName = dataMap.get("templateName").toString();
        //生成的静态页存储的路径
        String path = dataMap.get("path").toString();
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

        //生成的文件名字
        String name = dataMap.get("name").toString();
        //根据模板名字获取模板对象
        Template template = configuration.getTemplate(templateName);
        //生成文件，并转成字符串
        String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, dataMap);
        //输出到磁盘
        FileUtils.writeStringToFile(new File(path,name),content);
    }
}
