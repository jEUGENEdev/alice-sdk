package org.jeugenedev.alice.core.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

public class Request {
    private String command, type, signature;
    private JsonNode payload;
    @JsonProperty("original_utterance")
    private String originalUtterance;
    private Markup markup;
    private NLU nlu;
    private Error error;
    @JsonProperty("purchase_request_id") private String purchaseRequestId;
    @JsonProperty("purchase_token") private String purchaseToken;
    @JsonProperty("order_id") private String orderId;
    @JsonProperty("purchase_timestamp") private long purchaseTimestamp;
    @JsonProperty("signed_data") private String signedData;
    @JsonProperty("purchase_payload") private PurchasePayload purchasePayload;

    public String getCommand() {
        return command;
    }

    public String getType() {
        return type;
    }

    public String getSignature() {
        return signature;
    }

    public String getPayload() {
        return payload.toString();
    }

    public String getOriginalUtterance() {
        return originalUtterance;
    }

    public Markup getMarkup() {
        return markup;
    }

    public NLU getNlu() {
        return nlu;
    }

    public Error getError() {
        return error;
    }

    public String getPurchaseRequestId() {
        return purchaseRequestId;
    }

    public String getPurchaseToken() {
        return purchaseToken;
    }

    public String getOrderId() {
        return orderId;
    }

    public long getPurchaseTimestamp() {
        return purchaseTimestamp;
    }

    public String getSignedData() {
        return signedData;
    }

    public PurchasePayload getPurchasePayload() {
        return purchasePayload;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", type='" + type + '\'' +
                ", payload=" + payload +
                ", originalUtterance='" + originalUtterance + '\'' +
                ", markup=" + markup +
                ", nlu=" + nlu +
                ", error=" + error +
                '}';
    }

    static class Error {
        private String message, type;

        public String getMessage() {
            return message;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return "Error{" +
                    "message='" + message + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }

    static class Markup {
        @JsonProperty("dangerous_context")
        private boolean dangerousContext;

        public Markup() {
        }

        public Markup(boolean dangerousContext) {
            this.dangerousContext = dangerousContext;
        }

        public boolean isDangerousContext() {
            return dangerousContext;
        }

        @Override
        public String toString() {
            return "Markup{" +
                    "dangerousContext=" + dangerousContext +
                    '}';
        }
    }

    static class NLU {
        private List<String> tokens;
        private List<Entity> entities;
        private JsonNode intents;

        public NLU() {
        }

        public NLU(List<String> tokens, List<Entity> entities, JsonNode intents) {
            this.tokens = tokens;
            this.entities = entities;
            this.intents = intents;
        }

        public List<String> getTokens() {
            return tokens;
        }

        public List<Entity> getEntities() {
            return entities;
        }

        public String getIntents() {
            return intents.toString();
        }

        @Override
        public String toString() {
            return "NLU{" +
                    "tokens=" + tokens +
                    ", entities=" + entities +
                    ", intents='" + intents + '\'' +
                    '}';
        }

        static class Entity {
            private String type;
            private JsonNode value;
            private Token tokens;

            public Entity() {
            }

            public Entity(String type, JsonNode value, Token tokens) {
                this.type = type;
                this.value = value;
                this.tokens = tokens;
            }

            public String getType() {
                return type;
            }

            public String getValue() {
                return value.toString();
            }

            public Token getTokens() {
                return tokens;
            }

            @Override
            public String toString() {
                return "Entity{" +
                        "type='" + type + '\'' +
                        ", value='" + value + '\'' +
                        ", tokens=" + tokens +
                        '}';
            }

            static class Token {
                private int start, end;

                public Token() {
                }

                public Token(int start, int end) {
                    this.start = start;
                    this.end = end;
                }

                public int getStart() {
                    return start;
                }

                public int getEnd() {
                    return end;
                }

                @Override
                public String toString() {
                    return "Token{" +
                            "start=" + start +
                            ", end=" + end +
                            '}';
                }
            }
        }
    }

    static class PurchasePayload {
        private JsonNode value;

        public String getValue() {
            return value.toString();
        }

        @Override
        public String toString() {
            return "PurchasePayload{" +
                    "value=" + value +
                    '}';
        }
    }
}
