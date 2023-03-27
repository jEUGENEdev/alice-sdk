package org.jeugenedev.alice.core.entity.request;

public class State {
    private Session session;
    private User user;
    private Application application;

    public State() {
    }

    public State(Session session, User user, Application application) {
        this.session = session;
        this.user = user;
        this.application = application;
    }

    public Session getSession() {
        return session;
    }

    public User getUser() {
        return user;
    }

    public Application getApplication() {
        return application;
    }

    @Override
    public String toString() {
        return "State{" +
                "session=" + session +
                ", user=" + user +
                ", application=" + application +
                '}';
    }

    static class Session<StateType> {
        private StateType value;

        public Session() {
        }

        public Session(StateType value) {
            this.value = value;
        }

        public StateType getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Session{" +
                    "value=" + value +
                    '}';
        }
    }

    static class User<StateType> {
        private StateType value;

        public User() {
        }

        public User(StateType value) {
            this.value = value;
        }

        public StateType getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "User{" +
                    "value=" + value +
                    '}';
        }
    }

    static class Application<StateType> {
        private StateType value;

        public Application() {
        }

        public Application(StateType value) {
            this.value = value;
        }

        public StateType getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Application{" +
                    "value=" + value +
                    '}';
        }
    }
}
