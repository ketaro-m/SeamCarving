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
        show(inputImg);

        int width = inputImg.width(), height = inputImg.height();

        int w = Integer.parseInt(args[1]);
        int h = Integer.parseInt(args[2]);

        int removeColumns = Math.max(width - height * w / h, 0);
        int removeRows = height - (width - removeColumns) * h / w;

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

        String extension = args[0].substring(args[0].lastIndexOf("."));
        String fname = args[0].substring(0, args[0].lastIndexOf("."));
        fname = fname + "-" + args[1] + "-by-" + args[2];
        sc.getPic().save(fname + extension);
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
