package processing.test.digitalclock;
        
import processing.android.PWatchFaceCanvas;
import processing.core.PApplet;
        
public class MainService extends PWatchFaceCanvas {
  @Override
  public PApplet createSketch() {
    PApplet sketch = new digitalclock();
    
    return sketch;
  }
}
