package wyh.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class HystrixHelloWorld extends HystrixCommand<String> {
	private final String name;

	protected HystrixHelloWorld(String name) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))
				.andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ExampleGroup-pool"))//可选,默认 使用 this.getClass().getSimpleName();
				.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(4))
				.andCommandPropertiesDefaults(HystrixCommandProperties.Setter()
						.withCircuitBreakerEnabled(true)
						.withExecutionIsolationThreadTimeoutInMilliseconds(3000)
						.withCircuitBreakerErrorThresholdPercentage(30)// 错误率百分之30
		                .withCircuitBreakerRequestVolumeThreshold(10)// 10s以内调用次数10次，同时满足(1)(2)熔断器打开
		                .withCircuitBreakerSleepWindowInMilliseconds(120000)// 熔断器打开2分钟后关闭
		                .withExecutionIsolationStrategy(ExecutionIsolationStrategy.SEMAPHORE)// 隔离策略为信号量
		                .withExecutionIsolationSemaphoreMaxConcurrentRequests(15)// 信号量最大的并发数
		                )
		                
		                
				
                );
				 // 必须
		this.name = name;
	}

	@Override
	protected String run() throws Exception {
		// 调用业务代码
		System.out.println(Thread.currentThread().getName());
		Thread.sleep(2000);
		
		return "hello  " + name + " !!!";
	}
	
	@Override
	protected String getFallback() {
		//熔断之后会执行此方法
		return "FALLBACK NAME";
	}
	
	

}
