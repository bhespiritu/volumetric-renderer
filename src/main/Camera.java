package main;

public class Camera {

	public Transform transform;
	
	public float baseX = .8165f*2, baseY = .8165f;
	public float depth = 1;
	
	public Camera() {
		transform = new Transform();
		transform.position = new Vector3(0,0,0);
		transform.rotation = new Vector3(0,0,0);
	}
	
	public Vector3 getDir(float x, float y)
	{
		float vectX = -baseX + 2*baseX*x;
		float vectY = baseY - 2*baseY*y;
		Vector3 dir = new Vector3(depth,vectX,vectY);
		return transform.applyRotation(dir).normalize();
	}
	
	
	
}
