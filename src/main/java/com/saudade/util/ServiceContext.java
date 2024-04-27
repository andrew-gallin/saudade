package com.saudade.util;

import com.google.common.io.Closer;
import io.grpc.Server;
import io.norberg.automatter.AutoMatter;

@AutoMatter
public interface ServiceContext {
    Server server();
    Closer closer();

    static ServiceContext serviceContext(Server server, Closer closer){
        return (new ServiceContextBuilder()).server(server).closer(closer).build();
    }
}
