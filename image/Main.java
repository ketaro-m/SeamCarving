package image;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.opencv.imgcodecs.Imgcodecs.imwrite;

public class Main {
    // Compulsory
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    /** Assuming ARGS is { image filename, # rows to remove,
     *  # columns to remove), display the image in filename with the
     *  indicated numbers of rows and columns removed. */
    public static void main(String[] args) throws IOException {

        Picture inputImg = new Picture(args[0]);

        Picture raw = new Picture(inputImg);
        show(raw);

        int removeColumns = Integer.parseInt(args[1]);
        int removeRows = Integer.parseInt(args[2]);

        System.out.printf("Original image is %d columns by %d rows\n",
                inputImg.width(), inputImg.height());

        Rescaler sc = new Rescaler(inputImg);

        for (int i = 0; i < removeColumns; i += 1) {
            sc.removeColumn();
        }

        for (int i = 0; i < removeRows; i += 1) {
            sc.removeRow();
        }

        System.out.printf("New image size is %d columns by %d rows\n",
                sc.width(), sc.height());

        show(sc.getPic());

        sc.getPic().save("example/output.jpg");
    }

    /** Show reshaped pictures with not destructing the original images.*/
    private static void show(Picture pic) throws IOException {
        final int W = 1000;
        final int H = 600;
        Picture pic2 = new Picture(pic);
        int width = pic2.width();
        int height = pic2.height();
        if (width > W || height > H) {
            if (width > W) {
                height = height * W / width;
                width = W;
            }
            if (height > H) {
                width = width * H / height;
                height = H;
            }
            pic2.reshape(width, height);
        }
        pic2.show();
    }
}
