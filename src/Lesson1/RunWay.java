package Lesson1;

import java.util.Random;

public class RunWay implements Barrier {

    private final int SPEED;

    public RunWay() {
        SPEED = new Random().nextInt(20);
    }

    public int getSPEED() {
        return SPEED;
    }

    public boolean gotIt(CompetitionMember member) {
        if (member.run(this)) {
            System.out.printf("%s passed the track with speed %d.\n",member.getName(), this.SPEED);
            return true;
        } else {
            System.out.printf("%s has not passed the track with speed %d and finished.\n",member.getName(), this.SPEED);
            return false;
        }
    }


}
