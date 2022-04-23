package Lesson1;

import java.util.Random;

public class Robot implements CompetitionMember {

    private final int jumpHeight;
    private final int speed;
    private static int robot_number = 1;
    private String name;

    public Robot() {
        jumpHeight = new Random().nextInt(MAX_HEIGHT_JUMPING);
        speed = new Random().nextInt(MAX_SPEED);
        name = "Robot " + robot_number;
        robot_number++;
    }

    @Override
    public boolean jump(Wall wall) { return jumpHeight > wall.getHEIGHT();}

    @Override
    public boolean run(RunWay speedway) { return speed > speedway.getSPEED();}

    public String getName() {
        return name;
    }
}
