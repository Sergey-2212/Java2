package Lesson1;

public class Action {
    public static void main(String[] args) {
        CompetitionMember[] members = {
                new Cat(),
                new Cat(),
                new Human(),
                new Robot(),
                new Robot(),
                new Human()};

        Barrier[] track = {
                new RunWay(),
                new Wall(),
                new RunWay(),
                new Wall()};

        for (CompetitionMember member : members) {
            for (Barrier barrier : track) {
               if(!barrier.gotIt(member)) break;
            }
        }
    }
}
