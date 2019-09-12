package main;

public class Vector3 {
	float x;
	float y;
	float z;
	
	public Vector3(float i, float j, float k) {
		x = i;
		y = j;
		z = k;
	}

	public Vector3 add(Vector3 other)
	{
		return new Vector3(x+other.x,y+other.y,z+other.z);
	}
	
	public Vector3 sub(Vector3 other)
	{
		return new Vector3(x-other.x,y-other.y,z-other.z);
	}
	
	public Vector3 mult(float other)
	{
		return new Vector3(x*other,y*other,z*other);
	}
	
	public Vector3 div(float other)
	{
		return new Vector3(x/other,y/other,z/other);
	}
	
	public float dot(Vector3 other)
	{
		return x*other.x + y*other.y + z*other.z;
	}
	
	public float sqrMagnitude()
	{
		return x*x + y*y + z*z;
	}
	
	public float magnitude()
	{
		return (float) Math.sqrt(sqrMagnitude());
	}
	
	public Vector3 normalize()
	{
		return div(magnitude());
	}
	
	public Vector3 rotate(int axis, float angle)
	{
		Vector3 out = copy();
		float cos = (float) Math.cos(angle);
		float sin = (float) Math.sin(angle);
		switch(axis)
		{
		case 0: //x
			out.y = (cos*out.y - sin*out.z);
			out.z = (sin*out.y + cos*out.z);
			break;
		case 1: //y
			out.x = (cos*out.x - sin*out.z);
			out.z = (sin*out.x + cos*out.z);
			break;
		case 2: //z
			out.x = (cos*out.x - sin*out.y);
			out.y = (sin*out.x + cos*out.y);
			break;
		}
		return out;
	}

	public Vector3 copy() {
		return new Vector3(x,y,z);
	}
	
	@Override
	public String toString() {
		return "{" + x + ',' + y + ',' + z + '}';
	}
	
	
	
}
