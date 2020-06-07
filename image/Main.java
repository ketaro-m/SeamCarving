package image;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.CvType;
import org.opencv.core.Scalar;

public class Main {
    // Compulsory
    static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

    /** Assuming ARGS is { image filename, # rows to remove,
     *  # columns to remove), display the image in filename with the
     *  indicated numbers of rows and columns removed. */
    public static void main(String[] args) {
        System.out.println("Welcome to OpenCV " + Core.VERSION);
        Mat m = new Mat(5, 10, CvType.CV_8UC1, new Scalar(0));
        System.out.println("OpenCV Mat: " + m);
        Mat mr1 = m.row(1);
        mr1.setTo(new Scalar(1));
        Mat mc5 = m.col(5);
        mc5.setTo(new Scalar(5));
        System.out.println("OpenCV Mat data:\n" + m.dump());
        if (args.length != 3) {
            System.out.println("Usage:\njava image.Rescaler [image filename]"
                    + " [num rows to remove]"
                    + " [num columns to remove]");
            return;
        }

        Picture inputImg = new Picture(args[0]);
        inputImg.show();
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


        inputImg.show();
        sc.getPic().show();
    }
}
