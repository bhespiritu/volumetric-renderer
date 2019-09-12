package main;

public class Transform {

	public Vector3 position = new Vector3(0,0,0);
	public Vector3 rotation = new Vector3(0,0,0);
	
	public Vector3 applyRotation(Vector3 in)
	{
		Vector3 out = in.copy();
		
		out = out.rotate(0, rotation.x);
		out = out.rotate(1, rotation.y);
		out = out.rotate(2, rotation.z);
		
		return out;
	}
	
}
