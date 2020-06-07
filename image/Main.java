package image;

public class Main {

    /** Assuming ARGS is { image filename, # rows to remove,
     *  # columns to remove), display the image in filename with the
     *  indicated numbers of rows and columns removed. */
    public static void main(String[] args) {
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
