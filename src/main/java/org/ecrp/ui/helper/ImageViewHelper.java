package org.ecrp.ui.helper;

import javafx.scene.image.ImageView;

/**
 * description <br>
 *
 * @author bicg
 * @date 2021-12-16 16:16
 */
public class ImageViewHelper {

    public static ImageView imageView(String path, double size) {
        return imageView(path, size, size);
    }

    public static ImageView imageView(String path, double width, double height) {
        ImageView imageView = new ImageView(ImageHelper.image(path));
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        return imageView;
    }

}
