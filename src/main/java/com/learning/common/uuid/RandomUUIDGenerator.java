package com.learning.common.uuid;

import java.util.UUID;

public class RandomUUIDGenerator implements UUIDGenerator{
    @Override
     public UUID generate(){
        return UUID.randomUUID();

    }
}
