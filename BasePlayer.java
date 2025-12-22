// class that players can inherit from to get default behaviour for the Player interface.
// you technically don't need to use this for writing a new Player implementer.
// this only avoids having to paste setter and getter everywhere...
abstract class BasePlayer implements Player {
    Mark mark;

    @Override
    public void setMark(Mark mark) {
        this.mark = mark;
    }

    @Override
    public Mark getMark() {
        return mark;
    }
}
