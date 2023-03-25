package org.jeugenedev.alice.core.entity.request;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MetaTest {
    @Test
    public void jsonShouldConvertCorrectlyToMetaPOJO() throws JsonProcessingException {
        String testJson = "{\"meta\": {\"locale\": \"ru-RU\",\"timezone\": \"Europe/Moscow\",\"client_id\": \"ru.yandex.searchplugin/7.16 (none none; android 4.4.2)\",\"interfaces\": {\"screen\": {},\"account_linking\": {},\"audio_player\": {}}}}";
        String testJsonWithoutInterface = "{\"meta\": {\"locale\": \"ru-RU\",\"timezone\": \"Africa/Accra\",\"client_id\": \"ru.yandex.searchplugin/7.16 (none none; android 4.4.2)\",\"interfaces\": {\"screen\": {},\"audio_player\": {}}}}";
        ObjectMapper objectMapper = new ObjectMapper();
        Meta meta = objectMapper.readValue(testJson, MetaWrapper.class).getMeta();
        System.out.println(meta);
        Assertions.assertArrayEquals(
                new String[] {
                        "ru-RU",
                        "Europe/Moscow"
                },
                new String[] {
                        meta.getLocale(),
                        meta.getTimezone()
                });
        meta = objectMapper.readValue(testJsonWithoutInterface, MetaWrapper.class).getMeta();
        System.out.println(meta);
        Assertions.assertArrayEquals(
                new String[] {
                        "ru-RU",
                        "Africa/Accra",
                        "ru.yandex.searchplugin/7.16 (none none; android 4.4.2)",
                        null
                },
                new String[] {
                        meta.getLocale(),
                        meta.getTimezone(),
                        meta.getClientId(),
                        meta.getInterfaces().getAccountLinking() == null ? null : ""
                });
    }

    static class MetaWrapper {
        private Meta meta;

        public MetaWrapper() {}

        public MetaWrapper(Meta meta) {
            this.meta = meta;
        }

        public Meta getMeta() {
            return meta;
        }

        public void setMeta(Meta meta) {
            this.meta = meta;
        }
    }
}
