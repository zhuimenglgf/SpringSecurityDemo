package com.zhuimeng.controller.imagecode;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Administrator on 2018/8/13.
 */
@RestController
public class ValidateCodeController {
    public final static String SESSION_KEY="SESSION_KEY_IMAGECODE";
    SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @RequestMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode=createImageCode(request);
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"JPEG",response.getOutputStream());
    }

    private ImageCode createImageCode(HttpServletRequest request) {
        int weight=67;
        int height=23;
        BufferedImage bufferedImage=new BufferedImage(weight,height,BufferedImage.TYPE_INT_RGB);
        Graphics g=bufferedImage.getGraphics();
        Random random=new Random();
        g.setColor(getRandomColor(200,250));
        g.fillRect(0,0,weight,height);
        g.setColor(getRandomColor(160,200));
        for (int i=0;i<155;i++){
            int x=random.nextInt(weight);
            int y=random.nextInt(height);
            int xl=random.nextInt(12);
            int yl=random.nextInt(12);
            g.drawLine(x,y,x+xl,y+yl);
        }
        String sRand = "";
        for (int i = 0; i < 4; i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(bufferedImage,sRand,60);
    }

    private Color getRandomColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
