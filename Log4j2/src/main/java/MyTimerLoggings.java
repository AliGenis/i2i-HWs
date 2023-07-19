import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.time.LocalTime;

public class MyTimerLoggings {
    static Logger logger = LogManager.getLogger(MyTimerLoggings.class);
    static LocalTime time = LocalTime.now();

    public static void main(String[] args) throws InterruptedException {
        while(true){
            time = LocalTime.now();

            if(time.getSecond() == 0){
                if(time.getMinute() == 0){
                    logger.error(time.toString());
                }
                else{
                    logger.info(time.toString());
                }
            }
            logger.debug(time.toString());
            Thread.sleep(1000);
        }
    }
}