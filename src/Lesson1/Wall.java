package Lesson1;

import java.util.Random;

public class Wall implements Barrier {

    private final int HEIGHT;

    public Wall() {
        HEIGHT = new Random().nextInt(2, 7);
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    @Override
    public boolean gotIt(CompetitionMember member) {
        if (member.jump(this)) {
            System.out.printf("%s passed the wall with height %d.\n",member.getName(), this.HEIGHT);
            return true;
        } else {
            System.out.printf("%s has not passed the wall with height %d and finished.\n",member.getName(), this.HEIGHT);
            return false;
        }
    }
}
