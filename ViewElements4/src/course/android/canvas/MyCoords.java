package course.android.canvas;

public class MyCoords {
	
	float x;
	float y;
	
	public MyCoords(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	synchronized MyCoords move( MyCoords c){
		return new MyCoords(x + c.x, y + c.y);
	}

	synchronized MyCoords getCoords(){
		return new MyCoords(x, y); 
	} 

	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	
	
}