package com.mbp.api.service;

import com.mbp.model.Output;
import io.grpc.stub.StreamObserver;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class OutputStreamingResponse implements StreamObserver<Output> {

    private final Map<Integer, String> map;
    private final CompletableFuture<Map<Integer, String>> completableFuture;

    public OutputStreamingResponse(Map<Integer, String> map, CompletableFuture<Map<Integer, String>> completableFuture) {
        this.map = map;
        this.completableFuture = completableFuture;
    }

    @Override
    public void onNext(Output output) {
        this.map.put(output.getNumber(), output.getResult());
    }

    @Override
    public void onError(Throwable throwable) {
        completableFuture.cancel(true);
    }

    @Override
    public void onCompleted() {
        completableFuture.complete(this.map);
    }
}
