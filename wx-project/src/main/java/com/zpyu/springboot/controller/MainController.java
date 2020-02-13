package com.zpyu.springboot.controller;

import com.zpyu.springboot.config.Config;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import weixin.popular.api.MenuAPI;
import weixin.popular.bean.BaseResult;
import weixin.popular.bean.menu.selfmenu.CurrentSelfmenuInfo;
import weixin.popular.bean.message.EventMessage;
import weixin.popular.bean.xmlmessage.XMLImageMessage;
import weixin.popular.support.ExpireKey;
import weixin.popular.support.TokenManager;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.SignatureUtil;
import weixin.popular.util.XMLConverUtil;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @Author: zpyu521
 * @Date: 2019/8/13
 * @Description:
 * @Version: 1.0
 */
@Controller
@RequestMapping("/wx")
public class MainController {
    private static Logger logger =
            LoggerFactory.getLogger(MainController.class);
    //重复通知过滤
    private static ExpireKey expireKey =
            new DefaultExpireKey();

    @Autowired
    private Config config;

    @RequestMapping("/getMenu")
    @ResponseBody
    public Object getMenu() {
        //Menu menu = MenuAPI.menuGet(TokenManager.getDefaultToken());
        CurrentSelfmenuInfo menu = MenuAPI.get_current_selfmenu_info(TokenManager.getDefaultToken());
        return menu;
    }
    
    @RequestMapping("/createMenu")
    @ResponseBody
    public BaseResult createMenu() {
        String menuStr1 = "{\n" +
                "     \"button\":[\n" +
                "     {    \n" +
                "          \"type\":\"click\"," +
                "\n" +
                "          \"name\":\"今日歌曲\",\n" +
                "          " +
                "\"key\":\"V1001_TODAY_MUSIC" +
                "\"\n" +
                "      },\n" +
                "      {\n" +
                "           \"name\":\"菜单\",\n" +
                "           \"sub_button\":[\n" +
                "           {    \n" +
                "               " +
                "\"type\":\"view\",\n" +
                "               " +
                "\"name\":\"搜索\",\n" +
                "               " +
                "\"url\":\"https://ke.qq.com" +
                ".com/\"\n" +
                "            },\n" +
                "            {\n" +
                "                 " +
                "\"type\":\"miniprogram\",\n" +
                "                 " +
                "\"name\":\"wxa\",\n" +
                "                 " +
                "\"url\":\"http://mp.weixin.qq" +
                ".com\",\n" +
                "                 " +
                "\"appid\":\"wx286b93c14bbf93aa" +
                "\",\n" +
                "                 " +
                "\"pagepath\":\"pages/lunar" +
                "/index\"\n" +
                "             },\n" +
                "            {\n" +
                "               " +
                "\"type\":\"click\",\n" +
                "               " +
                "\"name\":\"赞一下我们\",\n" +
                "               " +
                "\"key\":\"V1001_GOOD\"\n" +
                "            }]\n" +
                "       }]\n" +
                " }";

        String menuStr2 = "{\r\n" +
                "    \"button\": [\r\n" +
                "        {\r\n" +
                "            \"name\": \"扫码\",\r\n" +
                "            \"sub_button\": [\r\n" +
                "                {\r\n" +
                "                    \"type\": \"scancode_waitmsg\",\r\n" +
                "                    \"name\": \"扫码带提示\",\r\n" +
                "                    \"key\": \"rselfmenu_0_0\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                },\r\n" +
                "                {\r\n" +
                "                    \"type\": \"scancode_push\",\r\n" +
                "                    \"name\": \"扫码推事件\",\r\n" +
                "                    \"key\": \"rselfmenu_0_1\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                }\r\n" +
                "            ]\r\n" +
                "        },\r\n" +
                "        {\r\n" +
                "            \"name\": \"发图\",\r\n" +
                "            \"sub_button\": [\r\n" +
                "                {\r\n" +
                "                    \"type\": \"pic_sysphoto\",\r\n" +
                "                    \"name\": \"系统拍照发图\",\r\n" +
                "                    \"key\": \"rselfmenu_1_0\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                },\r\n" +
                "                {\r\n" +
                "                    \"type\": \"pic_photo_or_album\",\r\n" +
                "                    \"name\": \"拍照或者相册发图\",\r\n" +
                "                    \"key\": \"rselfmenu_1_1\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                },\r\n" +
                "                {\r\n" +
                "                    \"type\": \"pic_weixin\",\r\n" +
                "                    \"name\": \"微信相册发图\",\r\n" +
                "                    \"key\": \"rselfmenu_1_2\",\r\n" +
                "                    \"sub_button\": []\r\n" +
                "                }\r\n" +
                "            ]\r\n" +
                "        },\r\n" +
                "        {\r\n" +
                "            \"name\": \"发送位置\",\r\n" +
                "            \"type\": \"location_select\",\r\n" +
                "            \"key\": \"rselfmenu_2_0\"\r\n" +
                "        }\r\n" +
                "    ]\r\n" +
                "}";
        BaseResult baseResult =
                MenuAPI.menuCreate(TokenManager.getDefaultToken(), menuStr2);

        return baseResult;
    }

    @RequestMapping("/sign")
    @ResponseBody
    public void sign(@RequestParam Map<String,
            String> param,
                     HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
        param.forEach((key, value) -> {
            logger.info(key + " : " + value);
        });

        ServletInputStream inputStream =
                request.getInputStream();
        ServletOutputStream outputStream =
                response.getOutputStream();
        String signature = param.get("signature");
        String timestamp = param.get("timestamp");
        String nonce = param.get("nonce");
        String echostr = param.get("echostr");

        // 对称加密  本地
        if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(timestamp)) {
            outputStreamWrite(outputStream,
                    "faild request");
            return;
        }
        //首次请求申请验证,返回echostr
        if (echostr != null) {
            logger.info(
                    "-----------------首次验证---------------------");
            outputStreamWrite(outputStream,
                    echostr);
            return;
        }
        if (!signature.equals(SignatureUtil.generateEventMessageSignature(config.getToken(), timestamp, nonce))) {
            logger.info("-----------------The " +
                    "request signature is " +
                    "invalid" +
                    "---------------------");
            return;
        }

        if (inputStream != null) {
            //转换XML
            EventMessage eventMessage = XMLConverUtil.convertToObject(EventMessage.class, inputStream);
            String key = eventMessage.getFromUserName() + "__"
                    + eventMessage.getToUserName() + "__"
                    + eventMessage.getMsgId() + "__"
                    + eventMessage.getCreateTime();
            logger.info("eventMessage:" + ToStringBuilder.reflectionToString(eventMessage));
            if (expireKey.exists(key)) {
                //重复通知不作处理
                logger.info("-------------------------重复通知不作处理-----------------------");
                return;
            } else {
                expireKey.add(key);
            }

            /*
             *
             * */
            //创建回复图片
            XMLImageMessage imageMessage = new XMLImageMessage(eventMessage.getFromUserName(),
                    eventMessage.getToUserName(),
                    eventMessage.getMediaId());

            logger.info("55555555555555555555555555555");
            //XMLMessage xmlTextMessage =
            //        new XMLTextMessage(
            //                eventMessage.getFromUserName(),
            //                eventMessage.getToUserName(),
            //                eventMessage.getContent());
            ////回复
            //
            //xmlTextMessage.outputStreamWrite(outputStream);

            imageMessage.outputStreamWrite(outputStream);
            return;
        }
        outputStreamWrite(outputStream,
                echostr);
        //inputStream.close();
        //outputStream.close();
    }

    private void outputStreamWrite(OutputStream outputStream, String echostr) {
        try {
            outputStream.write(echostr.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
