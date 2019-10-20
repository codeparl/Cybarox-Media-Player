
package mediaplayerdemo;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javax.naming.Binding;

/**
 *
 * @author HASSAN
 */
public class FXMLDocumentController implements Initializable {
    
 @FXML
 private  MediaView  mediaView;
 
    private String filePath;
    private MediaPlayer  player ;
    
    @FXML
    Slider seekSlider; 
    
    @FXML
    private Slider slider;
    @FXML
    private void handleButtonAction(ActionEvent event) {
       
    FileChooser fc  = new FileChooser();
    
    FileChooser.ExtensionFilter  filter  =  new FileChooser.ExtensionFilter("MP4 Files (.mp4)", ".mp4");
    // fc.getExtensionFilters().add(filter);
     
        filter  =  new FileChooser.ExtensionFilter("mp3 Files (.mp3)", ".mp3");
       // fc.getExtensionFilters().add(filter);
        File  file  =  fc.showOpenDialog(null);
        
        filePath =  file.toURI().toString();
        
        if(filePath !=  null){
        Media median  = new Media(filePath);
        player =  new MediaPlayer(median);
        
        mediaView.setMediaPlayer(player);
        
            DoubleProperty  width  =  mediaView.fitWidthProperty();
            DoubleProperty  height  =  mediaView.fitHeightProperty();
            
             width.bind(Bindings.selectDouble(mediaView.sceneProperty(), "width"));
            height.bind(Bindings.selectDouble(mediaView.sceneProperty(), "hight"));
            
        slider.setValue(player.getVolume() * 100);
        
        slider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                player.setVolume(slider.getValue() / 100);
                
            }
        });
        
        
        
        player.currentTimeProperty().addListener(new ChangeListener<Duration> () {
            @Override
            public void changed(ObservableValue<? extends Duration> observable, Duration oldValue, Duration newValue) {

seekSlider.setValue(newValue.toSeconds());
            }
        } );
        
        seekSlider.setOnMouseClicked( new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                
                player.seek(Duration.seconds(seekSlider.getValue()));
                
            }
        });
        
        
        
        player.play();
     }//end if 
        
        
        
    }
    
    @FXML
    private void pauseVideoEvent (ActionEvent  event){
    
    player.pause();
    }
    
    @FXML
    private void stopVideoEvent (ActionEvent  event){
    player.stop();
    
    }
    
    @FXML
    private void playVideoEvent (ActionEvent  event){
    player.play();
    player.setRate(1);
    
    }
    
    @FXML
    private void fastVideoEvent (ActionEvent  event){
    
    player.setRate(1.5);
    }
    
    @FXML
    private void fasterVideoEvent (ActionEvent  event){
    
        player.setRate(2);
    
    }
    
    
    @FXML
    private void slowVideoEvent (ActionEvent  event){
    
    player.setRate(.75);
    }
    
  @FXML
    private void slowerVideoEvent (ActionEvent  event){
    player.setRate(.5);
    
    }
    
  @FXML
    private void exitEvent (ActionEvent  event){
    //System.exit(0);
    Platform.exit();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
