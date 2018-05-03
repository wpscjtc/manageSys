package xxf.com.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xxf.com.entities.UsersEntity;
import xxf.com.service.LoginService;
import xxf.com.utils.*;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Date;

@RestController
@RequestMapping("/login")
public class LoginController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LoginService loginService;

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    /**
      *@Author:wangping
      *@Despribtion:生成验证码图片
      *@Date 2018/5/3 10:05
    */
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /**
      *@Author:wangping
      *@Despribtion:登录
      *@Date 2018/4/13 11:05
    */
    @RequestMapping("/login")
    public String login(HttpServletRequest httpServletRequest,UsersEntity user){
        logger.debug("user login id="+user.getUserID());
        if (user==null){
            return GsonUtil.setResult(0, MsgUtil.NO_PASSWORD_USERID).toString();
        }

        //判断验证码
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("vrifyCode");
        if(captchaId!=null&&!"".equals(captchaId)){
            if(parameter==null||"".equals(parameter)||!captchaId.equals(parameter)){
                return GsonUtil.setResult(0, MsgUtil.CAPTCHAID_ERROR).toString();
            }
        }
        httpServletRequest.getSession().removeAttribute("vrifyCode");
        String result=loginService.login(user);

        return result;
    }

    /**
     *@Author:wangping
     *@Despribtion:通过网龙登录
     *@Date 2018/4/13 11:05
     */
    @RequestMapping("/loginForIm")
    public String loginForIm(String key,Integer userId,Long timeStamp){
        logger.debug("user loginForIm id="+userId);
        if (XxfUtils.IsNull(key,userId,timeStamp)){
            return GsonUtil.setResult(MsgUtil.MSGSTATUS_IM_FAIL, MsgUtil.PARAMS_LOSE).toString();
        }
        Long nowTimeStamp=new Date().getTime();
        if(nowTimeStamp-timeStamp>60*1000||nowTimeStamp-timeStamp<-60*1000){
            return GsonUtil.setResult(MsgUtil.MSGSTATUS_IM_FAIL, MsgUtil.OUTTIME_KEY).toString();
        }
        //检验key
        String paramKeyForMd5=userId+""+timeStamp+Constants.SCRETKEY;
        String checkKey=MD5Util.Md5Value(paramKeyForMd5);
        if(!checkKey.equals(key.toUpperCase())){
            return GsonUtil.setResult(MsgUtil.MSGSTATUS_IM_FAIL, MsgUtil.ILLIGEL_KEY).toString();
        }

        String result=loginService.loginForIm(userId);

        return result;
    }

    /**
      *@Author:wangping
      *@Despribtion:登出
      *@Date 2018/4/19 14:47
    */
    @RequestMapping("/logout")
    public String logout(String authToken){
        return loginService.logout(authToken);
    }

}
