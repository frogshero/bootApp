package com.example.logDemo.vo;

import lombok.Data;

@Data
public class PicWordsIdentifyRequest {
    //图像数据：base64编码，要求base64编码后大小不超过4M，最短边至少15px，最长边最大4096px，支持jpg/png/bmp格式，和url参数只能同时存在一个
    private String img;
    //图像url地址：图片完整URL，URL长度不超过1024字节，URL对应的图片base64编码后大小不超过4M，最短边至少15px，最长边最大4096px，支持jpg/png/bmp格式，和img参数只能同时存在一个
    private String url;
    //是否需要识别结果中每一行的置信度，默认不需要。 true：需要 false：不需要
    private boolean prob = false;
}
