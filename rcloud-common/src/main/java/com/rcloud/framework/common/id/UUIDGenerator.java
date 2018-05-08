package com.rcloud.framework.common.id;

import com.fasterxml.uuid.Generators;

public interface UUIDGenerator {
    static String generate() {
        return Generators.timeBasedGenerator().generate().toString().replaceAll("-", "");
    };
}
