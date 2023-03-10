package com.mbp.api.service;

import com.mbp.model.Input;
import com.mbp.model.Output;
import com.mbp.model.ServiceRpcGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
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

        //Date start
        long ds = System.currentTimeMillis();

        Input input = Input.newBuilder().setNumber(number).build();

        Map<Integer, String> map = new HashMap<>();

        for (int i = 1; i <= number ; i++) {
            Output output = this.blockingStub.findUnary(input);
            map.put(i, output.getResult());
        }

        //Date end
        long de = System.currentTimeMillis();
        System.out.println("gRPC unary," + (de-ds));

        return map;
    }

    public Object getResponseStream(int number){

        //Date start
        long ds = System.currentTimeMillis();

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

        //Date end
        long de = System.currentTimeMillis();
        System.out.println("gRPC stream," + (de-ds));

        return completableFuture;
    }

}
