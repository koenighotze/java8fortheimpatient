package org.koenighotze.chapter3;

import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.WHITE;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.koenighotze.chapter3.ImageHelper.loadImage;
import static org.koenighotze.chapter3.ImageHelper.transform;

import java.io.*;

import javafx.scene.image.*;
import javafx.scene.paint.*;
import org.junit.*;

/**
 * @author dschmitz
 */
public class Ex3_8Test {

    public static ColorTransformer withFrame(ColorTransformer transformer, Color framecolor, int imageHeight, int imageWidth, int thickness) {
        return (x, y, intColor) -> {
            if (x < thickness || x > imageWidth - thickness) {
                return framecolor;
            }

            if (y < thickness || y > imageHeight - thickness) {
                return framecolor;
            }

            return transformer.transform(x, y, intColor);
        };
    }

    @Test
    public void transformer_with_border() throws IOException {
        Image image = loadImage();

        Image newImage = transform(image, withFrame((x, y, c) -> {
            if (x % 30 == 0) return WHITE;
            else return null;
        }, RED, (int) image.getHeight(), (int) image.getWidth(), 3));

        assertThat("Image must not be null", newImage, is(not(nullValue())));
        // todo check border
        // todo check smarter implementation :(
//        storeImage(newImage);
    }
}
