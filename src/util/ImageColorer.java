package util;

import javafx.scene.Group;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ImageColorer {
    public static Group recolor(ImageView img, Color color){
        double width = img.getFitWidth();
        double height = img.getFitHeight();


        if(width==0){
            width = height*(img.getImage().getWidth()/img.getImage().getHeight());
        }
        if(height==0){
            height = width*(img.getImage().getHeight()/img.getImage().getWidth());
        }

        Rectangle colorer = new Rectangle(width,height,color);
        colorer.setBlendMode(BlendMode.SRC_ATOP);

        Group g = new Group();
        g.setBlendMode(BlendMode.SRC_OVER);
        g.getChildren().add(img);
        g.getChildren().add(colorer);

        return g;
    }
}
