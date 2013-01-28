/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bergercode;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.shape.Line;

/**
 *
 * @author damiank
 */
public class Computer extends Thread {

    private Computer neighbour;
    private LinkedBlockingQueue<Long> sendQ;
    private LinkedBlockingQueue<Long> recvQ;
    private Line lineToSend;
    private Slider slider;
    private Button me;
    private Random rand;
    private boolean starting = false;
    private List<Long> msgs = new LinkedList<>();
    private int num = 0;

    public Computer(LinkedBlockingQueue<Long> sendQ, LinkedBlockingQueue<Long> recvQ, Line lineToSend, Slider slider, Button me) {
        this.sendQ = sendQ;
        this.recvQ = recvQ;
        this.lineToSend = lineToSend;
        this.slider = slider;
        this.me = me;
        this.rand = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        Long msg = null;
        if (starting) {
            Long msg1 = generateMSG();
            sendMsg(msg1);
            msgs.add(msg1);
        }
        while (msg == null && MainWindowController.isRunning) {
            try {
                msg = recvQ.poll(50, TimeUnit.MILLISECONDS);
                validateMSG(msg);
                if (msgSent(msg)) {
                } else {
                    int bitsToInvert = getBitsToInvert();
                    if (bitsToInvert == 0) {
                        lineToSend.setStyle("-fx-stroke: " + MainWindowController.OK);
                        try {
                            sendQ.offer(msg, 500, TimeUnit.MILLISECONDS);
                            dream(200);
                        } finally {
                            lineToSend.setStyle("-fx-stroke: #000000;");
                        }
                    } else {
                        sendMsg(msg);
                    }
                }
                msg = null;

            } catch (InterruptedException ex) {
            }

        }

    }

    private void dream(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException ex) {
        }
    }

    private int getBitsToInvert() {
        return new Double(slider.getValue()).intValue();
    }

    private void validateMSG(Long msg) throws InterruptedException {
        if (msg == null) {
            throw new InterruptedException();
        }
        if (BergerCodeCalculator.validateMSG(msg)) {
            me.setStyle("-fx-background-color: " + MainWindowController.OK);
        } else {
            me.setStyle("-fx-background-color: " + MainWindowController.ERROR);
        }
        dream(200);
        me.setStyle("-fx-background-color: " + MainWindowController.IDLE);
    }

    public void setStarting(boolean starting) {
        this.starting = starting;
    }

    public void setNeighbour(Computer neighbour) {
        this.neighbour = neighbour;
    }

    private Long generateMSG() {
        BigInteger bi = new BigInteger(MainWindowController.bits, rand);
        Long aa = bi.longValue();
        Long bgc = BergerCodeCalculator.generateBergerCode(aa);
        Long emb = BergerCodeCalculator.embeddCode(aa, bgc);
        return emb;
    }

    private boolean msgSent(Long msg) {
        return msgs.contains(msg);
    }

    public void setNum(int num) {
        this.num = num;
    }

    private void sendMsg(Long msg1) {
        int bitsToInvert = getBitsToInvert();
        if (bitsToInvert == 0) {
            lineToSend.setStyle("-fx-stroke: " + MainWindowController.OK);
        } else {
            if (MainWindowController.isTotalWar) {
                msg1 = BergerCodeCalculator.totalWar(msg1, bitsToInvert);
            } else {
                msg1 = BergerCodeCalculator.makeOnes(msg1, bitsToInvert);
            }
            lineToSend.setStyle("-fx-stroke: " + MainWindowController.ERROR);
        }
        sendQ.offer(msg1);
        dream(200);
        lineToSend.setStyle("-fx-stroke: #000000;");
    }
}
