# java8-concurrency
Java8 Concurrency

## Deadlock
  Threads wait infinitely for locks to be be released by each other. Issue is caused by incorrect locking order / synchronization of objects.

## Interrupt
 Sleeping \ Active \ Blocked threads can be interrupted for special handling or existing of the thread.
 
## Join
  Join methods causes calling thread to wait till the other thread execution is finished. In example main call will finish before thread execution
  if join is not called
  
## ThreadLocal
  ThreadLocal object maintains different coppies fo each set thread. get and set methods can be used to obtain value for each thread.
  
## Timer
  Timer class can be used to execute threads after delay repeatedly until timer.cancel is called
  
## Wait and Notify
  Wait and Notify are used to synchronize the access of shared object
  
## CompletableFuture
  CompletableFuture uses fork-join by default but a thread pool can be passed to it. runAsync and supplyAsync (when returning something)
  builder methods are used. Callbacks are written using thenApply(function, thenAccept(consumer)to execute after
  the task is completed. get and join methods are used to obtain result or wait for finishing of execution.
  Since CompletableFuture is non-blocking it is used to return result in restcontroler or service class code for reactive programming.

## StampedLock
  Use the long stamp value returned by read/write/readOptimisticLock() methods in unlock(stamp) method. Main feature is optimistic read.  Its a non-renentrant lock.

##  Reentrant or ReentrantReadWriteLock
  Reentrant lock work like synchronized locks but provide fairness while locking

##  BlockingQueue
  Producer or Consumer wait for each other if queue full(put) or empty(take) avoiding sync issues. Non blocking methods - offer, poll, peek OR add , remove, element [do not return anything]

## SynchronousQueue
   Producer or Consumer wait for each other to process to avoid sync issues. Type of blocking queue.

## TransferQueue
   Multiple producers wait for consumers to take an element before transfer method is executed to avoid sync issues.Type of Blocking Queue

## CountDownLatch(n)
   Until the latch is countDown() by n the await() in main or calling thread will block its execution. Cannot be reset/reused. Used to complete configuration or other important activites before starting app or processing.

## CyclicBarrier(n)
   Await is called from threads or/and main to block execution of all threads. Its automatically broken when n await are called. Can be reset().

## Exchanger
   Two threads of same type wait for each each other to call exchange(T) method which exchanges the object data. Less GC due to exchanging of data.

## Phaser
   Dynamic modified form of CountDown latch. uses register() instead of hardcoded n value. 
   arriveAndAwaitAdvance() is used to wait all other threads to reach the phase. It can be called multiple times for creating multiple phases. arriveAndDeregister is used to complete thread execution or all phases.

## Semaphore(n)
   acquire() and release() methods are used to obtain n locks over an object. It works like lock if n=1

## CompletionService
   Wrapper over executor service which produces a type T e.g. ExecutorCompletionService<>(executorservice). The poll(n) returns Future<T> which can be used to get results one at a time as and when threads finish instead of waiting for all to finish.

## ExecutorService
   ExecutorService is returned by Executors.newFixedThreadPool/newScheduledThreadPool/newSingleThreadExecutor. Future<t> submit(callable<T> call) returns result in future whereas
   execute(runnable run) does not return any result. Has invokeAll/invokeAny methods which take list of callables only and return list of futures.

## ForkJoinPool
   invoke() method to call RecursiveTask<T> . T compute() method is used to create List of ForkJoinTask to hold multiple RecursiveTask created and forked and then uses join() to aggregate the result.

## ThreadFactory
   Passed to executor service to have custom priorty, thread names for threads. It is interface with newThread method returning a thread.
