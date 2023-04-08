package org.jeugenedev.alice.core.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Directive {
    @JsonProperty("start_account_linking") private Object startAccountLinking;
    @JsonProperty("audio_player") private AudioPlayer audioPlayer;

    public Directive() {
    }

    public Directive(Object startAccountLinking, AudioPlayer audioPlayer) {
        this.startAccountLinking = startAccountLinking;
        this.audioPlayer = audioPlayer;
    }

    public Object getStartAccountLinking() {
        return startAccountLinking;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    @Override
    public String toString() {
        return "Directive{" +
                "startAccountLinking=" + startAccountLinking +
                ", audioPlayer=" + audioPlayer +
                '}';
    }

    static class AudioPlayer {
        private String action;
        private Item item;

        public AudioPlayer() {
        }

        public AudioPlayer(String action, Item item) {
            this.action = action;
            this.item = item;
        }

        public String getAction() {
            return action;
        }

        public Item getItem() {
            return item;
        }

        @Override
        public String toString() {
            return "AudioPlayer{" +
                    "action='" + action + '\'' +
                    ", item=" + item +
                    '}';
        }

        static class Item {
            private Stream stream;
            private Metadata metadata;

            public Item() {
            }

            public Item(Stream stream, Metadata metadata) {
                this.stream = stream;
                this.metadata = metadata;
            }

            public Stream getStream() {
                return stream;
            }

            public Metadata getMetadata() {
                return metadata;
            }

            @Override
            public String toString() {
                return "Item{" +
                        "stream=" + stream +
                        ", metadata=" + metadata +
                        '}';
            }

            static class Stream {
                private String url, token;
                @JsonProperty("offset_ms") private long offsetMS;

                public Stream() {
                }

                public Stream(String url, String token, long offsetMS) {
                    this.url = url;
                    this.token = token;
                    this.offsetMS = offsetMS;
                }

                public String getUrl() {
                    return url;
                }

                public String getToken() {
                    return token;
                }

                public long getOffsetMS() {
                    return offsetMS;
                }

                @Override
                public String toString() {
                    return "Stream{" +
                            "url='" + url + '\'' +
                            ", token='" + token + '\'' +
                            ", offsetMS=" + offsetMS +
                            '}';
                }
            }

            static class Metadata {
                private String title;
                @JsonProperty("sub_title") private String subtitle;
                private Image art;
                @JsonProperty("background_image") private Image backgroundImage;

                public Metadata() {
                }

                public Metadata(String title, String subtitle, Image art, Image backgroundImage) {
                    this.title = title;
                    this.subtitle = subtitle;
                    this.art = art;
                    this.backgroundImage = backgroundImage;
                }

                public String getTitle() {
                    return title;
                }

                public String getSubtitle() {
                    return subtitle;
                }

                public Image getArt() {
                    return art;
                }

                public Image getBackgroundImage() {
                    return backgroundImage;
                }

                @Override
                public String toString() {
                    return "Metadata{" +
                            "title='" + title + '\'' +
                            ", subtitle='" + subtitle + '\'' +
                            ", art=" + art +
                            ", backgroundImage=" + backgroundImage +
                            '}';
                }

                static class Image {
                    private String url;

                    public Image() {
                    }

                    public Image(String url) {
                        this.url = url;
                    }

                    public String getUrl() {
                        return url;
                    }

                    @Override
                    public String toString() {
                        return "Image{" +
                                "url='" + url + '\'' +
                                '}';
                    }
                }
            }
        }
    }
}
