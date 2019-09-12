package main;

import java.awt.Color;

public class Sphere {

	public Transform transform;
	
	public Sphere() {
		transform = new Transform();
	}
	
	public Color color = Color.BLUE;
	
	public float radius;
	
	public float sdf(Vector3 point)
	{
		return transform.position.sub(point).magnitude() - radius;
	}
	
}
