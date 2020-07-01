package image;

import org.opencv.core.*;
import org.opencv.imgproc.Imgproc;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    // Compulsory
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    /** Assuming ARGS is { image filename, # rows to remove,
     *  # columns to remove), display the image in filename with the
     *  indicated numbers of rows and columns removed. */
    public static void main(String[] args) throws IOException {

        Picture inputImg = new Picture(args[0]);
        int width = inputImg.width(), height = inputImg.height();
        System.out.printf("Original image is %d columns by %d rows\n",
                width, height);


        Picture inputImg2 = show(inputImg);
        int small = Math.min(inputImg2.width(), inputImg2.height());
        DragRegion dr = new DragRegion("Drag regions you want to preserve.", inputImg2);
        dr.setVisible(true);
        dr.setRadius(small / 50);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String readString = scanner.nextLine();
            if (readString.isEmpty()) {
                break;
            } else if (readString.equals("s")) {
                dr.setRadius(small / 100);
            } else if (readString.equals("m")) {
                dr.setRadius(small / 50);
            } else if (readString.equals("l")) {
                dr.setRadius(small / 20);
            } else if (readString.equals("+")) {
                dr.setMode(1);
            } else if (readString.equals("-")) {
                dr.setMode(-1);
            }
        }
        System.out.println("Rescaling...");
        Imgproc.resize(inputImg2.getEnergyMat(), inputImg.getEnergyMat(), new Size(width, height));
        

        int w = Integer.parseInt(args[1]);
        int h = Integer.parseInt(args[2]);

        int removeColumns = Math.max(width - height * w / h, 0);
        int removeRows = height - (width - removeColumns) * h / w;


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
    }

    /** Show reshaped pictures with not destructing the original images.*/
    private static Picture show(Picture pic) throws IOException {
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
        return pic2;
    }
}
