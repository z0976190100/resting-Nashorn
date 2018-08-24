package com.z0976190100.restingnashorn.service;

import com.z0976190100.restingnashorn.persistence.entity.Processor;
import com.z0976190100.restingnashorn.persistence.entity.ProcessorState;
import org.springframework.stereotype.Service;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static com.z0976190100.restingnashorn.util.PseudoDB.processorsList;
import static java.lang.Thread.sleep;

// TODO: is getResult() good enough?

@Service
public class ResultService {

    public ProcessorState getResult(int id) {
        return postOnResultPending(id);
    }

    public ProcessorState postOnResultPending(int id) {

        if (!processorsList.isEmpty()) {
            for (Processor processor : processorsList)
                if (processor.getId() == id) {
                    Future f = processor.getTask();
                    try {
                        while (!f.isCancelled() && !f.isDone() && f.get() != null) {
                            sleep(100);
                        }
                    } catch (ExecutionException | InterruptedException | CancellationException e) {
                        e.printStackTrace();
                    }
                    return processor.getProcessorState();
                }
        }
        return null;
    }
}

