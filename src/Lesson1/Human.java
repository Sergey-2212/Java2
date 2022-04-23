package Lesson1;

import java.util.Random;

public class Human implements CompetitionMember {

    private final int jumpHeight;
    private final int speed;
    private static int human_number = 1;
    private String name;

    public Human() {
        jumpHeight = new Random().nextInt(MAX_HEIGHT_JUMPING);
        speed = new Random().nextInt(MAX_SPEED);
        name = "Human " + human_number;
        human_number++;
    }

    @Override
    public boolean jump(Wall wall) {return jumpHeight > wall.getHEIGHT();}

    @Override
    public boolean run(RunWay speedway) { return speed > speedway.getSPEED();}

    public String getName() {
        return name;
    }
}
