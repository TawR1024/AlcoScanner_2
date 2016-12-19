package service;

import java.awt.*;
import java.net.URL;

/**
 * Created by Ilya Kulakov on 07.12.16.
 */
final class Resource {
    public static Image getImage(String filename) {
        Image image;
        try {
            final URL url = Resource.class.getResource(filename);
            image = Toolkit.getDefaultToolkit().getImage(url);
        } catch (Exception e) {
            image = Toolkit.getDefaultToolkit().getImage("resources/" + filename);
        }
        return image;
    }
}
