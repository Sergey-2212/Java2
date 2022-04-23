package Lesson1;

import java.util.Random;

public class Cat implements CompetitionMember {

    private final int jumpHeight;
    private final int speed;
    private static int cat_number = 1;
    private String name;

    public Cat() {
        jumpHeight = new Random().nextInt(MAX_HEIGHT_JUMPING);
        speed = new Random().nextInt(MAX_SPEED);
        name = "Cat" + cat_number;
        cat_number++;
    }

    @Override
    public boolean jump(Wall wall) { return jumpHeight > wall.getHEIGHT();}

    @Override
    public boolean run(RunWay speedway) { return speed > speedway.getSPEED();}


    public String getName() {
        return name;
    }
}
