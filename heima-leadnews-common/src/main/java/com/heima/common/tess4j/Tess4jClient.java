package com.heima.common.tess4j;
/**
 * @desription : tess4j工具类
 */

import lombok.Getter;
import lombok.Setter;
import net.sourceforge.tess4j.Tesseract;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.awt.image.BufferedImage;

/**
 *@Desc: 文字OCR识别
 *@Author: lzy
 *@CreateTime: 2024-12-17  19:10
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "tess4j")
public class Tess4jClient {
    private String dataPath;
    private String language;

    public String doOCR(BufferedImage image) throws Exception{
        // 创建Tesseract对象
        Tesseract tesseract = new Tesseract();
        // 设置训练库的位置
        tesseract.setDatapath(dataPath);
        // 设置识别语言
        tesseract.setLanguage(language);
        // 识别图片
        String result = tesseract.doOCR(image);
        result = result.replaceAll("\\r|\\n", "-").replaceAll(" ", "");
        return result;
    }
}
