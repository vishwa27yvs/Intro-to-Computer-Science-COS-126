/************************************************************************************************
 * Compilation: javac-introcs BeadFinder.java
 * Execution: java-introcs BeadFinder args[0] args[1] args[2]
 * Creates a data type BeadFinder, which takes 3 arguments- threshold for intensity, min pixels and frame.
 *************************************************************************************************/

import java.awt.Color;

public class BeadFinder {


    private Picture newPic; // frame to be considered
    private boolean[][] bead; // boolean to identify if it is a bead or not
    private boolean[][] check; // if the bead has been traversed
    private Blob temp; // groups neighboring pixels which are above a certain intensity
    private LinkedBlob blobList = new LinkedBlob(); // Linked List of all the blobs
    private int countBlobs = 0; // number of blobs in this frame(to keep check)


    /* finds all blobs in the specified picture using luminance threshold tau */
    public BeadFinder(Picture picture, double tau) {
        newPic = new Picture(picture.width(), picture.height());
        bead = new boolean[picture.width()][picture.height()];
        check = new boolean[picture.width()][picture.height()];


        double intensity;

        // traversing through each pixel
        for (int i = 0; i < newPic.width(); i++) {
            for (int j = 0; j < newPic.height(); j++) {
                Color c = picture.get(i, j);

                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();


                // calculating the intensity of each pixel
                if (r == g && r == b) {
                    intensity = r;
                }
                else {
                    intensity = 0.299 * r + 0.587 * g + 0.114 * b;
                }

                // if intensity of the pixel crosses threshold
                if (intensity > tau) {
                    bead[i][j] = true;
                }
                else {
                    bead[i][j] = false;
                }
            }
        }

        for (int i = 0; i < newPic.width(); i++) {
            for (int j = 0; j < newPic.height(); j++) {
                if (!check[i][j] && bead[i][j]) {
                    temp = new Blob();
                    dfs(i, j);
                    countBlobs += 1;  // number of blobs
                    blobList.push(temp);
                    // System.out.println(temp);
                    // System.out.println(temp.mass());
                }
            }
        }
    }

    /* @param - i,j pixel(i,j)
     * dfs to identify blobs and beads efficiently */
    private void dfs(int i, int j) {
        // return from the fiunction in case of boundary conditions
        if (i < 0 || i >= newPic.width()) return;
        if (j < 0 || j >= newPic.height()) return;
        // if the pixel is not of given intensity
        if (!bead[i][j]) return;

        // pixel has been visited
        if (check[i][j]) return;
        check[i][j] = true;

        // adding the pixel to the bead
        temp.add(i, j);
        dfs(i + 1, j);  // right
        dfs(i, j + 1);  // up
        dfs(i - 1, j);  // left
        dfs(i, j - 1);  // down
    }

    /* @param - min, min no. of pixels required to classify it as a bead
     * returns all beads (blobs with >= min pixels) */
    public Blob[] getBeads(int min) {
        int count = 0;
        LinkedBlob beadlist = new LinkedBlob(); // to store all the beads from the list of all blobs
        int countBeads = 0;

        for (int i = 0; i < countBlobs; i++) {
            Blob tempBlob = blobList.pop();
            if (tempBlob.mass() >= min) {
                beadlist.push(tempBlob);
                countBeads += 1;
            }
        }

        Blob[] gBeads = new Blob[countBeads]; // to store all the beads in an array
        for (int i = 0; i < countBeads; i++) {
            gBeads[i] = beadlist.pop();
        }


        return gBeads;
    }

    /* test client */
    public static void main(String[] args) {
        double tau = Double.parseDouble(args[1]);
        int min = Integer.parseInt(args[0]);
        Picture bead = new Picture(args[2]);
        BeadFinder frame = new BeadFinder(bead, tau);
        Blob[] arr = frame.getBeads(min);
        for (int i = 0; i < arr.length; i++) {

            System.out.println(arr[i].toString());
        }

    }
}

