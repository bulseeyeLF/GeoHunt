package components;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import org.apache.log4j.Logger;

public class MapImageView extends ImageView{

    private static Logger log = Logger.getLogger(MapImageView.class);

    private WritableImage writableImage;
    private PixelWriter pixelWriter;

    public MapImageView(Image img){
        super(img);
    }





}
