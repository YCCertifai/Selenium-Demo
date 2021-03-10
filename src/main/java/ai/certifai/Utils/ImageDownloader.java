package ai.certifai.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.util.Base64;
import java.util.List;

public class ImageDownloader {

    public static boolean saveImage(String src, int counter, String path)
    {

        File outputFolder = new File(path);
        if (!outputFolder.exists()){
            outputFolder.mkdir();
        }
        String outputPath = path + "img_" + String.format("%04d", counter) + ".png";

        //url
        if (src.startsWith("http"))
        {
            try {
                URL imageURL = new URL(src);
                BufferedImage saveImage = ImageIO.read(imageURL.openStream());
                ImageIO.write(saveImage, "png", new File(outputPath));
            }
            catch (Exception e){
                return false;
            }
        }
        //base64
        else {
            String data = src.replaceFirst("^data:image/[^;]*;base64,?","");

            byte[] imageBytes = Base64.getDecoder().decode(data);;
            try {
                BufferedImage saveImage = ImageIO.read(new ByteArrayInputStream(imageBytes));

                ImageIO.write(saveImage, "png", new File(outputPath));
            }
            catch (Exception e){
                return false;
            }

        }
        return true;
    }

    public static void saveImages(List<String> imageSourceList, String path){
        int counter = 1;
        for (String imgSrc : imageSourceList)
        {
            if (saveImage(imgSrc, counter, path))
            {
                counter += 1;
            }
        }
    }
}
