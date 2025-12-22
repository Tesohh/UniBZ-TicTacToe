// class that all players inherit from to get default behaviour for the Player interface.
// this avoids having to paste setter and getter everywhere...
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
