package org.koenighotze.chapter3;

import java.io.IOException;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import org.junit.Test;

/**
 * Created by dschmitz on 17/06/15.
 */
public class Ex3_6Test extends Ex3_5Test {

    /**
     *
      * @param in
     * @param f a function that takes a color and a T and returns a color
     * @param args the param to the function
     * @param <T>
     * @return
     */
    public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T args) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();

        WritableImage out = new WritableImage(width, height);

        IntStream.range(0, width)
            .forEach(w -> IntStream.range(0, height)
                .forEach(h -> {
                    Color newColor = f.apply(in.getPixelReader().getColor(w, h), args);
                    out.getPixelWriter().setColor(w, h, newColor);
                }));
        return out;
    }


    @Test
    public void transformation() throws IOException {
        Image image = loadImage();
        storeImage(image);
        Image transform = transform(image, (color, i) -> color.deriveColor(i, 1, i, 1), 0.3d);
        storeImage(transform);
    }


}