package pl.altkom.shop;

import java.util.Date;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;
import org.quartz.Scheduler;
import org.quartz.StatefulJob;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;

public class SchedulerExecutor {
	@DisallowConcurrentExecution
	@PersistJobDataAfterExecution
	public static class Jobik implements StatefulJob {

		@Override
		public void execute(JobExecutionContext arg0) throws JobExecutionException {
			System.out.println("start");
			System.out.println(new Date());
			try {
				Thread.sleep(15000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("end");
		}
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		// and start it off
		scheduler.start();
		JobDetailImpl jobDetailImpl = new JobDetailImpl("miro", Jobik.class);
		CronTriggerImpl cronTriggerImpl = new CronTriggerImpl("jeb");
		cronTriggerImpl.setCronExpression("*/5 * * * * ?");
		scheduler.scheduleJob(jobDetailImpl, cronTriggerImpl);

		// scheduler.shutdown();

	}

}
