package com.moon.mybatis.type;

import com.moon.util.StringUtils;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author yujiangtao
 * @date 2020/9/6 下午9:26
 */
public class TypeAliasRegistry {

    private final Map<String, Class<?>> TYPE_ALIASES = new HashMap<>();

    public TypeAliasRegistry() {
        registerAlias("string", String.class);

        registerAlias("byte", Byte.class);
        registerAlias("long", Long.class);
        registerAlias("short", Short.class);
        registerAlias("int", Integer.class);
        registerAlias("integer", Integer.class);
        registerAlias("double", Double.class);
        registerAlias("float", Float.class);
        registerAlias("boolean", Boolean.class);
    }

    public void registerAlias(String alias, Class<?> value) {
        if(StringUtils.isBlank(alias)) {
            throw new RuntimeException("The parameter alias cannot be null");
        }
        String key = alias.toLowerCase(Locale.US);
        if(TYPE_ALIASES.containsKey(key) && TYPE_ALIASES.get(key) != null && !TYPE_ALIASES.get(key).equals(value)) {
            throw new RuntimeException("The alias '" + alias + "' is already mapped to the value '"
                    + TYPE_ALIASES.get(key).getName() + "'.");
        }
        TYPE_ALIASES.put(alias, value);
    }

    public Class<?> get(String name) {
        return TYPE_ALIASES.get(name);
    }
}
