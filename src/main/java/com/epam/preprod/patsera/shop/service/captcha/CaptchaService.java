package com.epam.preprod.patsera.shop.service.captcha;

import com.epam.preprod.patsera.shop.bean.Captcha;
import com.epam.preprod.patsera.shop.repository.captcha.CaptchaRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Random;

public class CaptchaService {

    private static final int TOTAL_LENGTH = 6;
    private static final Random RANDOM = new Random();
    private final CaptchaRepository captchaRepository;
    private static final int CAPTCHA_HEIGHT = 40;
    private static final int CAPTCHA_WIDTH = 150;
    private final Font font = new Font("Arial", Font.BOLD, 30);
    private final long captchaLiveTime;

    public CaptchaService(CaptchaRepository captchaRepository, long captchaLiveTime) {
        this.captchaRepository = captchaRepository;
        this.captchaLiveTime = captchaLiveTime;
    }

    public void add(String id, Captcha captcha) {
        captchaRepository.add(id, captcha);
    }

    public Captcha get(String id) {
        return captchaRepository.get(id);
    }

    public boolean isContains(String id) {
        return captchaRepository.contains(id);
    }

    public List<Captcha> getAll() {
        return captchaRepository.getAll();
    }

    public Captcha generateAndWriteCaptchaToOutputStream(OutputStream outputStream, String captchaId) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(CAPTCHA_WIDTH, CAPTCHA_HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D image = (Graphics2D) bufferedImage.getGraphics();
        String captchaString = generateCaptchaString();

        image.fillRect(0, 0, CAPTCHA_WIDTH, CAPTCHA_HEIGHT);
        blurImage(image);
        image.setFont(font);
        printStringOnImage(image, captchaString);
        ImageIO.write(bufferedImage, "jpeg", outputStream);
        image.dispose();

        return new Captcha(captchaString, captchaId, captchaLiveTime, bufferedImage);
    }

    private String generateCaptchaString() {
        return (Long.toString(Math.abs(RANDOM.nextLong()), 36)).substring(0, TOTAL_LENGTH);
    }

    private void blurImage(Graphics2D image) {
        int circleNumber = 15;
        for (int i = 0; i < circleNumber; i++) {
            image.setColor(new Color(RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255)));
            int radius = (int) (Math.random() * CAPTCHA_HEIGHT / 2.0);
            int x = (int) (Math.random() * CAPTCHA_WIDTH - radius);
            int y = (int) (Math.random() * CAPTCHA_HEIGHT - radius);
            image.fillRoundRect(x, y, radius * 2, radius * 2, 100, 100);
        }
    }

    private void printStringOnImage(Graphics2D image, String captchaString) {
        for (int i = 0; i < TOTAL_LENGTH; i++) {
            image.setColor(new Color(RANDOM.nextInt(255), RANDOM.nextInt(255), RANDOM.nextInt(255)));
            int charYPosition;
            if (i % 2 == 0) {
                charYPosition = 24;
            } else {
                charYPosition = 35;
            }
            image.drawString(captchaString.substring(i, i + 1), 25 * i, charYPosition);
        }
    }
}
