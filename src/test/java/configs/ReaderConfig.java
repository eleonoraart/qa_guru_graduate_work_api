package configs;

import org.aeonbits.owner.ConfigFactory;

public class ReaderConfig {

    public static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
}
