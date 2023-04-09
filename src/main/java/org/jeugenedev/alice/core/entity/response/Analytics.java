package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Analytics {
    private List<Event> events;

    public Analytics() {
    }

    public Analytics(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    @Override
    public String toString() {
        return "Analytics{" +
                "events=" + events +
                '}';
    }

    static class Event {
        private String name;
        private JsonNode value;

        public Event() {
        }

        public Event(String name, JsonNode value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value.toString();
        }

        @Override
        public String toString() {
            return "Event{" +
                    "name='" + name + '\'' +
                    ", value=" + value +
                    '}';
        }
    }
}
