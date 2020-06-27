package image;

import java.util.Arrays;
import java.awt.Color;

/**
 *  @author Josh Hug, Paul Hilfinger (style revisions).
 */

public class Rescaler {
    /** The energy arbitrarily assigned to boundary pixels. */
    static final int BORDER_ENERGY = 1_000_000;

    /** The picture this rescaler operates on. */
    private Picture pic;

    /** A rescaler that operates on PICTURE. */
    public Rescaler(Picture picture) {
        pic = new Picture(picture);
    }

    /** Return the width of my picture. */
    public int width() {
        return pic.width();
    }

    /** Return the height of my picture. */
    public int height() {
        return pic.height();
    }

    /** Return the picture this rescaler operations on. */
    public Picture getPic() {
        return pic;
    }

    /** Return the "energy" attributed to pixel at (X, Y) in my
     *  picture. */
    private double energy(int x, int y) {
        if ((x < 0) || (x >= pic.width()) || (y < 0) || (y > pic.height())) {
            return Double.POSITIVE_INFINITY;
        }

        if ((x == 0) || (x == pic.width() - 1)
            || (y == 0) || (y == pic.height() - 1)) {
            return BORDER_ENERGY;
        }

        Color
            left  = pic.get(x - 1, y),
            right = pic.get(x + 1, y),
            up    = pic.get(x, y + 1),
            down  = pic.get(x, y - 1);

        return gradiantEnergy(left, right) + gradiantEnergy(up, down);
    }

    /** Return the energy attributed to the difference between values P1 and
     *  and P2. */
    private static double gradiantEnergy(Color p1, Color p2) {
        double
            dr = p1.getRed() - p2.getRed(),
            dg = p1.getGreen() - p2.getGreen(),
            db = p1.getBlue() - p2.getBlue();

        return dr * dr + dg * dg + db * db;
    }


    /** Returns the energy matrix associated with my picture. */
    public double[][] energyMatrix() {
        double[][] energyMatrix = new double[height()][width()];
        for (int c = 0; c < width(); c += 1) {
            for (int r = 0; r < height(); r += 1) {
                energyMatrix[r][c] = energy(c, r);
            }
        }

        return energyMatrix;
    }

    /** Shrink my picture by one row. */
    public void removeRow() {
        double[][] em = energyMatrix();
        double[][] cem =
            MatrixUtils.accumulate(em, MatrixUtils.Orientation.HORIZONTAL);
        int[] hseam =
            MatrixUtils.findSeam(cem, MatrixUtils.Orientation.HORIZONTAL);
        removeHorizontalSeam(hseam);
    }

    /** Shrink my picture by one column. */
    public void removeColumn() {
        double[][] em = energyMatrix();
        double[][] cem = MatrixUtils.accumulate(em,
           MatrixUtils.Orientation.VERTICAL);
        int[] hseam = MatrixUtils.findSeam(cem,
          MatrixUtils.Orientation.VERTICAL);
        removeVerticalSeam(hseam);
    }

    /** Remove specified vertical SEAM from picture. */
    public void removeVerticalSeam(int[] seam) {
        /* Remove pixels with coordinates (seam[r], r). */
        Picture resizedPic = new Picture(width() - 1, height(), pic.getType());
        for (int r = 0; r < height(); r += 1) {
            int k = 0;
            for (int c = 0; c < width(); c += 1) {
                if (c != seam[r]) {
                    resizedPic.set(k, r, pic.get(c, r));
                    k += 1;
                }
            }
        }

        pic = resizedPic;
    }

    /** Transpose the image inside this Rescaler. */
    private void transpose() {
        Picture transposed = new Picture(pic.height(), pic.width(), pic.getType());

        for (int c = 0; c < pic.width(); c += 1) {
            for (int r = 0; r < pic.height(); r += 1) {
                transposed.set(r, c, pic.get(c, r));
            }
        }

        pic = transposed;
    }


    /** Remove horizontal seam SEAM from picture. */
    public void removeHorizontalSeam(int[] seam) {
        transpose();
        removeVerticalSeam(seam);
        transpose();
    }
}
