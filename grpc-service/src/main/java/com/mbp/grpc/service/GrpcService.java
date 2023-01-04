package com.mbp.grpc.service;


import com.mbp.model.Input;
import com.mbp.model.Output;
import com.mbp.model.ServiceRpcGrpc;
import io.grpc.stub.StreamObserver;

@net.devh.boot.grpc.server.service.GrpcService
public class GrpcService extends ServiceRpcGrpc.ServiceRpcImplBase {

    @Override
    public void findUnary(Input request, StreamObserver<Output> responseObserver) {

        //1KB
        StringBuilder sb = new StringBuilder();
        sb.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sit amet efficitur orci. Cras porta eros quis gravida varius. ");
        sb.append("Etiam pulvinar venenatis auctor. Duis pharetra nunc mattis nunc blandit, a tempus orci porta. Morbi in pulvinar mauris. Sed vel ");
        sb.append("elit sed felis condimentum egestas eget sed mauris. In non nisi libero. Vivamus congue lacus egestas malesuada tristique. Praesent ");
        sb.append("at suscipit nulla. Aenean bibendum vel turpis quis finibus. In sed iaculis libero, ac imperdiet est. Duis sollicitudin, nunc vitae ");
        sb.append("scelerisque commodo, mi velit semper nibh, sit amet euismod est tellus quis dolor. Morbi porta commodo eros in scelerisque. ");
        sb.append("Fusce non ex purus. Vestibulum scelerisque orci eu tortor facilisis, sed posuere ex porta. Nunc aliquet nisl sed mauris congue imperdiet. ");
        sb.append("Pellentesque varius elementum lectus non semper. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. ");
        sb.append("Aenean cursus, quam in egestas pulvinar, mi sapien ultricies sem, at volutpat mi vivamus.");

        sb.append(request.getNumber() * request.getNumber());

        responseObserver.onNext(
                Output.newBuilder().setResult(sb.toString()).build()
        );
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<Input> findBiStream(StreamObserver<Output> responseObserver) {

        //1KB
        StringBuilder sb = new StringBuilder();
        sb.append("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras sit amet efficitur orci. Cras porta eros quis gravida varius. ");
        sb.append("Etiam pulvinar venenatis auctor. Duis pharetra nunc mattis nunc blandit, a tempus orci porta. Morbi in pulvinar mauris. Sed vel ");
        sb.append("elit sed felis condimentum egestas eget sed mauris. In non nisi libero. Vivamus congue lacus egestas malesuada tristique. Praesent ");
        sb.append("at suscipit nulla. Aenean bibendum vel turpis quis finibus. In sed iaculis libero, ac imperdiet est. Duis sollicitudin, nunc vitae ");
        sb.append("scelerisque commodo, mi velit semper nibh, sit amet euismod est tellus quis dolor. Morbi porta commodo eros in scelerisque. ");
        sb.append("Fusce non ex purus. Vestibulum scelerisque orci eu tortor facilisis, sed posuere ex porta. Nunc aliquet nisl sed mauris congue imperdiet. ");
        sb.append("Pellentesque varius elementum lectus non semper. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. ");
        sb.append("Aenean cursus, quam in egestas pulvinar, mi sapien ultricies sem, at volutpat mi vivamus.");

        return new StreamObserver<>() {
            @Override
            public void onNext(Input input) {
                var number = input.getNumber();
                Output output = Output.newBuilder()
                        .setNumber(number)
                        .setResult(sb.toString()).build();
                responseObserver.onNext(output);
            }

            @Override
            public void onError(Throwable throwable) {
                responseObserver.onCompleted();
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

}
