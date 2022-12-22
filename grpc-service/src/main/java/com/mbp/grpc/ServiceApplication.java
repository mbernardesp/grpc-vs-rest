package com.mbp.grpc;

import io.grpc.netty.shaded.io.grpc.netty.NettyServerBuilder;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollEventLoopGroup;
import io.grpc.netty.shaded.io.netty.channel.epoll.EpollServerSocketChannel;
import net.devh.boot.grpc.server.serverfactory.GrpcServerConfigurer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.Executors;

@SpringBootApplication
public class ServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceApplication.class, args);
    }

    @Bean
    GrpcServerConfigurer grpcServerConfigurer() {
        return builder -> {
            ((NettyServerBuilder) builder)
                    .workerEventLoopGroup(new EpollEventLoopGroup(4))
                    .bossEventLoopGroup(new EpollEventLoopGroup(1))
                    .channelType(EpollServerSocketChannel.class)
                    .executor(Executors.newFixedThreadPool(20));
        };
    }

}
