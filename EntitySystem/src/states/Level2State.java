package states;

import helpers.Point;
import engine.GLEngine;
import framework.Entity;
import framework.Layer;
import framework.State;
import framework.World;
import framework.components.Button;
import framework.components.Message;
import framework.components.Obstacle;
import framework.components.Pathfinder;
import framework.components.Polygon;
import framework.components.Position;
import framework.components.Tex;
import framework.components.Timer;
import framework.systems.CameraSystem;
import framework.systems.CollisionSystem;
import framework.systems.EmitterSystem;
import framework.systems.FollowerSystem;
import framework.systems.LightSystem;
import framework.systems.PathSystem;
import framework.systems.PhysicsSystem;
import framework.systems.TimerSystem;
import framework.systems.input.KeyInputSystem;
import framework.systems.input.MouseInputSystem;
import framework.systems.input.PlayerInputSystem;
import framework.systems.render.RenderSystem;
import framework.systems.render.TextRenderSystem;

public class Level2State {
	public static void init(World world)
	{
		//Systems
		world.addSystem(new PlayerInputSystem(world), State.LEVEL2);
		world.addSystem(new CameraSystem(world), State.LEVEL2);
		world.addSystem(new PathSystem(world), State.LEVEL2);
		world.addSystem(new FollowerSystem(world), State.LEVEL2);
		world.addSystem(new PhysicsSystem(world), State.LEVEL2);
		world.addSystem(new CollisionSystem(world), State.LEVEL2);
		world.addSystem(new LightSystem(world), State.LEVEL2);

		world.addSystem(new RenderSystem(world), State.LEVEL2);
		world.addSystem(new TextRenderSystem(world), State.LEVEL2);
		world.addSystem(new EmitterSystem(world), State.LEVEL2);
		world.addSystem(new MouseInputSystem(world), State.LEVEL2);
		world.addSystem(new KeyInputSystem(world), State.LEVEL2);
		world.addSystem(new TimerSystem(world), State.LEVEL2);

		Entity camera = new Entity();
		camera.name = "camera";
		camera.components.add(new Position( new Point()));
		camera.components.add(new Polygon( new Point(0, 0), new Point(0, 0)));
		world.addEntity(camera, State.LEVEL2);
		world.registerID(camera, State.LEVEL2);
		
		Entity path = new Entity();
		path.name = "path";
		path.components.add(new Pathfinder(new Point(0, 0), new Point(GLEngine.WIDTH, GLEngine.HEIGHT), 10));
		world.addEntity(path, State.LEVEL2);
		world.registerID(path, State.LEVEL2);

		Entity light = new Entity();
		light.name = "light";
		light.layer = Layer.LIGHT;
		light.components.add(new Position( new Point(), true));
		light.components.add(Polygon.rectangle(new Point(GLEngine.WIDTH, GLEngine.HEIGHT)));
		light.components.add(new Tex("lightTex"));
		world.addEntity(light, State.LEVEL2);

		Entity ground = new Entity();
		ground.name = "ground";
		ground.layer = Layer.GROUND;
		ground.components.add(new Position( new Point()));
		ground.components.add(Polygon.rectangle(new Point(GLEngine.WIDTH*2, GLEngine.HEIGHT)));
		ground.components.add(new Tex("bush.png", new Point(128, 128)));
		world.addEntity(ground, State.LEVEL2);
		world.registerID(ground, State.LEVEL2);

		Entity border = new Entity();
		border.name = "border";
		border.components.add(Polygon.invertedRectangle(new Point(GLEngine.WIDTH, GLEngine.HEIGHT)));
		border.components.add(new Position(new Point(0, 0)));
		border.components.add(new Obstacle());
		world.addEntity(border, State.LEVEL2);
		
		Entity msg = new Entity();
		msg.components.add(new Message("BONUS LEVEL"));
		msg.components.add(new Timer(5000));
		world.addEntity(msg, State.LEVEL2);
		
		createButtons(world);
	}

	private static void createButtons(World world)
	{
		Entity exitButton2 = new Entity();
		exitButton2.name = "exitButton";
		exitButton2.layer = Layer.HUD;
		exitButton2.components.add(Polygon.rectangle(new Point(100, 50)));
		exitButton2.components.add(new Position(new Point(25, 650), true));
		exitButton2.components.add(new Button("Exit"));
		exitButton2.components.add(new Tex("button.png"));
		world.addEntity(exitButton2, State.LEVEL2);

		Entity menuButton = new Entity();
		menuButton.name = "button";
		menuButton.layer = Layer.HUD;
		menuButton.components.add(Polygon.rectangle(new Point(100, 50)));
		menuButton.components.add(new Position(new Point(150, 650), true));
		menuButton.components.add(new Button("Menu"));
		menuButton.components.add(new Tex("button.png"));
		world.addEntity(menuButton, State.LEVEL2);

		Entity zombieButton = new Entity();
		zombieButton.name = "zombieButton";
		zombieButton.layer = Layer.HUD;
		zombieButton.components.add(Polygon.rectangle(new Point(100, 50)));
		zombieButton.components.add(new Position(new Point(275, 650), true));
		zombieButton.components.add(new Button("Zombies"));
		zombieButton.components.add(new Tex("button.png"));
		world.addEntity(zombieButton, State.LEVEL2);

		Entity screenButton = new Entity();
		screenButton.name = "screenButton";
		screenButton.layer = Layer.HUD;
		screenButton.components.add(Polygon.rectangle(new Point(100, 50)));
		screenButton.components.add(new Position(new Point(400, 650), true));
		screenButton.components.add(new Button("Screen"));
		screenButton.components.add(new Tex("button.png"));
		world.addEntity(screenButton, State.LEVEL2);

		Entity restartButton = new Entity();
		restartButton.name = "restartButton";
		restartButton.layer = Layer.HUD;
		restartButton.components.add(Polygon.rectangle(new Point(100, 50)));
		restartButton.components.add(new Position(new Point(525, 650), true));
		restartButton.components.add(new Button("Restart"));
		restartButton.components.add(new Tex("button.png"));
		world.addEntity(restartButton, State.LEVEL2);
	}
}
