package vtc.xueqing.flower.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomStringDeserializer extends StdDeserializer<String> {
    public CustomStringDeserializer() {
        super(String.class);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        // 如果是空字符串，返回 null；否则返回原值
        return value != null && value.trim().isEmpty() ? null : value;
    }
}
