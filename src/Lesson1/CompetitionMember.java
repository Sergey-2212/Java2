package Lesson1;

public interface CompetitionMember {

    int MAX_HEIGHT_JUMPING = 6;
    int MAX_SPEED = 20;

    boolean jump(Wall wall);

    boolean run(RunWay speedway);

    String getName();
}
