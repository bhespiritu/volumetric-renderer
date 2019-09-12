package main;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RenderPane extends JPanel{

	public int resX = 200, resY = 100;
	
	public int ppm = 2;
	
	public Vector3 lightDir = new Vector3(1,1,-1).normalize();
	public Vector3 lightDirb = new Vector3(1,1,-1).normalize();
	
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Volumetric");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		RenderPane rp = new RenderPane();
		frame.setContentPane(rp);
		frame.setVisible(true);
		for(;;)
		{
			rp.timeStep();
			rp.repaint();
			Thread.sleep(1000/24);
		}
	}
	
	

	Camera c;
	
	public float time = 0;
	public float dt = 0.1f;
	
	public void timeStep() {
		time += dt;
		s.transform.rotation.x = (float) (3*Math.sin(time/10));
		//s.transform.rotation.z = (float) -(Math.cos(time));
		lightDir = s.transform.applyRotation(lightDirb);
	}
	
	Sphere s;
	
	public RenderPane() {
		c = new Camera();
		s = new Sphere();
		s.transform.position.x += 2;
		s.radius = 0.5f;
	}
	
	public Color cast(Vector3 dir)
	{
		Vector3 position = c.transform.position;
		Vector3 step = dir.mult(0.01f);
		for(int i = 0; i < 640; i++)
		{
			position = position.add(step);
			float dist = s.sdf(position);
			float distFloor = position.z + 1;
			if(distFloor < dist && distFloor < 0) 
			{
				int pos = (int) (position.x)+(int)(position.y);
				if(pos % 2 == 0) return Color.LIGHT_GRAY;
				else return Color.DARK_GRAY;
				//return new Color(dot,0,0);
				
			}
			if(dist <= 0.2f) 
			{
				Vector3 normal = position.sub(s.transform.position).normalize();
				float dot = normal.dot(lightDir);
				if(dot > 1) dot = 1;
				if(dot < -1) dot = -1;
				dot = (dot+1)/2;
				
				Vector3 h = lightDir.sub(dir).div(2);
				float s = (float) (Math.pow(normal.dot(h), 2)*1);
				float out = (1-dot)+s;
				if(out > 1) out = 1;
				return new Color(0,out,0);
				//return Color.red;
				
			}
			
		}
		return Color.WHITE;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int x = 0; x < resX; x++)
		{
			for(int y = 0; y < resY; y++)
			{
				Vector3 dir = c.getDir((float)x/resX, (float)y/resY);
				
				Color c = cast(dir);
				//System.out.println(c);
				g.setColor(c);
				g.fillRect(x*ppm, y*ppm, ppm, ppm);
			}
		}
	}
	
}
