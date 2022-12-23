package com.mbp.api.service;

import com.mbp.model.Input;
import com.mbp.model.Output;
import com.mbp.model.ServiceRpcGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

@Service
public class GrpcService {

    @GrpcClient("mbp")
    private ServiceRpcGrpc.ServiceRpcBlockingStub blockingStub;

    @GrpcClient("mbp")
    private ServiceRpcGrpc.ServiceRpcStub asyncStub;

    public Object getResponseUnary(int number){

        Input input = Input.newBuilder().setNumber(number).build();

        Map<Integer, String> map = new HashMap<>();

        for (int i = 1; i <= number ; i++) {
            Output output = this.blockingStub.findUnary(input);
            map.put(i, output.getResult());
        }
        return map;
    }

    public Object getResponseStream(int number){
        CompletableFuture<Map<Integer, String>> completableFuture = new CompletableFuture<>();
        OutputStreamingResponse outputStreamingResponse = new OutputStreamingResponse(
                new HashMap<>(),
                completableFuture
        );

        StreamObserver<Input> squareBiStream = this.asyncStub.findBiStream(outputStreamingResponse);

        IntStream.rangeClosed(1, number)
                .mapToObj(i -> Input.newBuilder().setNumber(i).build())
                .forEach(squareBiStream::onNext);
        squareBiStream.onCompleted();
        return completableFuture;
    }

}
