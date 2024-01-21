package io.temporal.applied.patterns.requestreply.frontend.channels;

import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.spring.boot.TemporalOptionsCustomizer;
import javax.annotation.Nonnull;

import io.temporal.spring.boot.WorkerOptionsCustomizer;
import io.temporal.worker.WorkerFactoryOptions;
import io.temporal.worker.WorkerOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalOptionsConfig {
  @Bean
  public WorkerOptionsCustomizer customWorkerOptions() {
    return new WorkerOptionsCustomizer() {
      @Nonnull
      @Override
      public WorkerOptions.Builder customize(
              @Nonnull WorkerOptions.Builder optionsBuilder,
              @Nonnull String workerName,
              @Nonnull String taskQueue) {


        // For CustomizeTaskQueue (also name of worker) we set worker
        // to only handle workflow tasks and local activities
        if (taskQueue.equals("CustomizeTaskQueue")) {
          optionsBuilder.setLocalActivityWorkerOnly(true);
        }
        return optionsBuilder;
      }
    };
  }

  // WorkflowServiceStubsOptions customization
  // This is more advanced usage, where one can do things like interceptors or other rules on
  // the underlying gRPC API for the SDK
  @Bean
  public TemporalOptionsCustomizer<WorkflowServiceStubsOptions.Builder>
      customServiceStubsOptions() {
    return new TemporalOptionsCustomizer<>() {
      @Nonnull
      @Override
      public WorkflowServiceStubsOptions.Builder customize(
          @Nonnull WorkflowServiceStubsOptions.Builder optionsBuilder) {
        // set options on optionsBuilder as needed
        // ...
        return optionsBuilder;
      }
    };
  }


  // WorkflowClientOption customization
  // One might tune the client being used with things like using a Data Converter, a dynamic
  // Namespace name (instead of from the resources config)
  @Bean
  public TemporalOptionsCustomizer<WorkflowClientOptions.Builder> customClientOptions() {
    return new TemporalOptionsCustomizer<>() {
      @Nonnull
      @Override
      public WorkflowClientOptions.Builder customize(
          @Nonnull WorkflowClientOptions.Builder optionsBuilder) {
        // set options on optionsBuilder as needed
        // ...
        return optionsBuilder;
      }
    };
  }

  @Bean
  public TemporalOptionsCustomizer<WorkerFactoryOptions.Builder> customWorkerFactoryOptions() {
    return new TemporalOptionsCustomizer<WorkerFactoryOptions.Builder>() {
      @Nonnull
      @Override
      public WorkerFactoryOptions.Builder customize(
              @Nonnull WorkerFactoryOptions.Builder optionsBuilder) {
        // set options on optionsBuilder as needed
        // ...

        return optionsBuilder;
      }
    };
  }
}
